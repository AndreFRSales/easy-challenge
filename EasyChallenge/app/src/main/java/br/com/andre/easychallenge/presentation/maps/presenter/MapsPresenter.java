package br.com.andre.easychallenge.presentation.maps.presenter;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Objects;

import br.com.andre.easychallenge.R;
import br.com.andre.easychallenge.data.bookmarks.repository.BookmarksRepository;
import br.com.andre.easychallenge.domain.bookmarks.models.Bookmark;
import br.com.andre.easychallenge.domain.bookmarks.usecase.GetBookmarksUsecase;
import br.com.andre.easychallenge.domain.bookmarks.usecase.SaveBookmarkUsecase;
import br.com.andre.easychallenge.domain.map.models.Address;
import br.com.andre.easychallenge.domain.map.models.CurrentPosition;
import br.com.andre.easychallenge.domain.map.repository.MapsRepository;
import br.com.andre.easychallenge.domain.map.usecases.FindAddressUsecase;
import br.com.andre.easychallenge.domain.map.usecases.GetCurrentPositionUsecase;
import br.com.andre.easychallenge.presentation.bookmarks.mappers.BookmarksPresentationMapper;
import br.com.andre.easychallenge.presentation.maps.MapsView;
import br.com.andre.easychallenge.presentation.permission.PermissionPresenter;
import io.reactivex.disposables.Disposable;

/**
 * Created by andre on 15/11/17.
 */

public class MapsPresenter implements MapsPresenterContract {

    private final int REQUEST_LOCATION = 1;
    private MapsView view;
    private PermissionPresenter permissionPresenter;
    private boolean permissionGranted;
    private CurrentPosition lastKnownLocation;
    private int DEFAULT_ZOOM = 18;
    GetCurrentPositionUsecase getCurrentPositionUsecase;
    FindAddressUsecase findAddressUsecase;
    SaveBookmarkUsecase saveBookmarkUsecase;
    ArrayList<Disposable> disposables;

    public MapsPresenter(MapsView view, PermissionPresenter permissionPresenter, MapsRepository repository,
                         BookmarksRepository bookmarksRepository) {
        this.view = view;
        this.permissionPresenter = permissionPresenter;
        setupUsecases(repository, bookmarksRepository);
        disposables = new ArrayList<>();
    }

    private void setupUsecases(MapsRepository mapsRepository, BookmarksRepository bookmarksRepository) {
        getCurrentPositionUsecase = new GetCurrentPositionUsecase(mapsRepository);
        findAddressUsecase = new FindAddressUsecase(mapsRepository);
        saveBookmarkUsecase = new SaveBookmarkUsecase(bookmarksRepository);
    }

    @Override
    public void start() {
        view.requestLocationPermission(REQUEST_LOCATION);
        view.setToolbar();
        if(lastKnownLocation == null) {
            permissionPresenter.requestPermission();
        } else {
            view.enableMapPropertiesLocation();
            view.updateMap(createLatLng(lastKnownLocation), DEFAULT_ZOOM);
        }
    }

    @Override
    public void destroy() {
        for (Disposable disposable: disposables) {
            if(disposable != null && !disposable.isDisposed()) {
                disposable.dispose();
            }
        }
    }

    @Override
    public void saveState(Bundle outState) {
        MapsBundler mapsBundler = new MapsBundler(outState);
        mapsBundler.setCurrentPosition(lastKnownLocation);
    }

    @Override
    public void restoreState(Bundle savedInstanceState) {
        MapsBundleReader reader = new MapsBundleReader(savedInstanceState);
        lastKnownLocation = reader.getCurrentPostion();
    }

    @Override
    public void checkPermission(int[] grantResults, int requestCode) {
        if(Objects.equals(PermissionPresenter.FINE_LOCATION_REQUEST_CODE, requestCode)) {
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    permissionPresenter.rejected();
                } else {
                    permissionGranted = true;
                    permissionPresenter.accepted();
                }
            }
        }
    }

    @Override
    public void findAddress(String query, String key) {
        view.showLoadingOverlay();
        disposables.add(findAddressUsecase.execute(new FindAddressUsecase.Params(query, key))
        .subscribe(
                address -> {
                    view.hideLoadingOverlay();
                    view.hideKeyboard();
                    view.updateMap(createLatLng(address), DEFAULT_ZOOM);
                },
                error -> {
                    view.hideLoadingOverlay();
                    view.hideKeyboard();
                    view.showSnackBar(R.string.maps_menu_search_didnt_found);
                }
        ));
    }

    @Override
    public void saveBookmark(String dialogResult) {
        view.showLoadingOverlay();
        disposables.add(saveBookmarkUsecase.execute(BookmarksPresentationMapper.mapToSaveBookmarkDomainModel(dialogResult, lastKnownLocation))
        .subscribe(
                aVoid -> {},
                error -> {
                    view.hideLoadingOverlay();
                    view.showSnackBar(R.string.save_bookmark_usecase_message_error); },
                () ->  {
                    view.hideLoadingOverlay();
                    view.showSnackBar(R.string.save_bookmark_usecase_message_success); }));
    }

    @SuppressLint("MissingPermission")
    @Override
    public void setupAcceptedMap(FusedLocationProviderClient fusedLocationProviderClient) {
        permissionGranted = true;
        view.enableMapPropertiesLocation();
        getDeviceLocation(fusedLocationProviderClient);
    }


    @SuppressLint("MissingPermission")
    @Override
    public void setupRejectedMap() {
        view.disableMapPropertiesLocation();
        lastKnownLocation = null;
    }

    @Override
    public void updateLastPosition(LatLng latLng) {
        lastKnownLocation = new CurrentPosition(latLng.latitude, latLng.longitude);
        view.focusOnLatLng(latLng, DEFAULT_ZOOM);
    }

    @SuppressLint("MissingPermission")
    private void getDeviceLocation(FusedLocationProviderClient fusedLocationProviderClient) {
        view.showLoadingOverlay();
        if(permissionGranted) {
            disposables.add(getCurrentPositionUsecase.execute(fusedLocationProviderClient)
            .subscribe(currentPosition -> {
                view.hideLoadingOverlay();
                lastKnownLocation = currentPosition;
                view.updateMap(createLatLng(lastKnownLocation), DEFAULT_ZOOM);
            }, error -> {
                view.hideLoadingOverlay();
                view.disableMapPropertiesLocation();
                view.showSnackBar(R.string.maps_error_current_position);
            }));
        }
    }

    private LatLng createLatLng(CurrentPosition currentPosition) {
        return new LatLng(currentPosition.getLatitude(), currentPosition.getLongitude());
    }

    private LatLng createLatLng(Address address) {
        return new LatLng(address.getLatitude(), address.getLongitude());
    }


}
