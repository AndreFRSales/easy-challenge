package br.com.andre.easychallenge.presentation.maps;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.andre.easychallenge.R;
import br.com.andre.easychallenge.data.bookmarks.repository.BookmarkLocalDataSource;
import br.com.andre.easychallenge.data.bookmarks.repository.BookmarkLocalDataSourceImp;
import br.com.andre.easychallenge.data.bookmarks.repository.BookmarkRemoteDataSource;
import br.com.andre.easychallenge.data.bookmarks.repository.BookmarkRemoteDataSourceImp;
import br.com.andre.easychallenge.data.bookmarks.repository.BookmarksRepository;
import br.com.andre.easychallenge.data.bookmarks.repository.BookmarksRepositoryImp;
import br.com.andre.easychallenge.data.map.repository.MapsDataRepositoryImp;
import br.com.andre.easychallenge.data.map.repository.MapsRemoteDataSource;
import br.com.andre.easychallenge.data.map.repository.MapsRemoteDataSourceImp;
import br.com.andre.easychallenge.domain.bookmarks.usecase.GetBookmarksUsecase;
import br.com.andre.easychallenge.domain.map.repository.MapsRepository;
import br.com.andre.easychallenge.presentation.maps.presenter.MapsPresenter;
import br.com.andre.easychallenge.presentation.maps.presenter.MapsPresenterContract;
import br.com.andre.easychallenge.presentation.permission.PermissionManager;
import br.com.andre.easychallenge.presentation.permission.PermissionManagerContract;
import br.com.andre.easychallenge.presentation.permission.PermissionPresenter;
import br.com.andre.easychallenge.presentation.permission.PermissionView;
import br.com.andre.easychallenge.presentation.utils.DialogUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static br.com.andre.easychallenge.presentation.permission.PermissionPresenter.ACCESS_FINE_LOCATION_PERMISSION;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, MapsView, SearchView.OnQueryTextListener,
        PermissionView, GoogleMap.OnMarkerDragListener, DialogUtils.OnDialogClicked {

    @BindView(R.id.maps_container)
    CoordinatorLayout container;

    @BindView(R.id.maps_toolbar)
    Toolbar toolbar;

    @BindView(R.id.maps_current_fab)
    FloatingActionButton currentPositionFab;

    @BindView(R.id.maps_loading_indicator)
    FrameLayout loadingOverlayContainer;

    private GoogleMap map;
    MapsPresenterContract presenter;
    PermissionPresenter permissionPresenter;
    FusedLocationProviderClient fusedLocation;
    DialogUtils bookmarkDescriptionDialog;

    //TODO REMOVER ISSO AQUI!!
    BookmarkLocalDataSourceImp bookmarkLocalDataSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        setupProperties();
    }

    private void setupProperties() {
        PermissionManagerContract permissionManagerContract = new PermissionManager(this);
        permissionPresenter = new PermissionPresenter(permissionManagerContract, this);
        MapsRepository mapsRepository = setupMapsRepository();
        BookmarksRepository bookmarksRepository = setupBookmarksRepository();
        presenter = new MapsPresenter(this, permissionPresenter, mapsRepository, bookmarksRepository);
        fusedLocation = LocationServices.getFusedLocationProviderClient(this);

    }

    private BookmarksRepository setupBookmarksRepository() {
        BookmarkLocalDataSource bookmarkLocalDataSource = new BookmarkLocalDataSourceImp(this);
        BookmarkRemoteDataSource bookmarkRemoteDataSource = new BookmarkRemoteDataSourceImp();
        return new BookmarksRepositoryImp(bookmarkLocalDataSource, bookmarkRemoteDataSource);
    }

    @NonNull
    private MapsRepository setupMapsRepository() {
        MapsRemoteDataSource remoteDataSource = new MapsRemoteDataSourceImp();
        return new MapsDataRepositoryImp(remoteDataSource);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        presenter.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_maps, menu);
        MenuItem myActionMenuItem = menu.findItem( R.id.menu_maps_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(myActionMenuItem);
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint(getString(R.string.search_hint));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void setToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.title_activity_maps);
    }

    @Override
    public void requestLocationPermission(int permissionId) {
        if (ContextCompat.checkSelfPermission(this,
                ACCESS_FINE_LOCATION_PERMISSION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    permissionId);
        }
    }


    @Override
    public void updateMap(LatLng latLng, int zoom) {
        focusOnLatLng(latLng, zoom);
        map.setOnMarkerDragListener(this);
        map.addMarker(new MarkerOptions().position(latLng).draggable(true));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        presenter.saveState(outState);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        presenter.restoreState(savedInstanceState);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        presenter.findAddress(query, getString(R.string.google_maps_key));
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void permissionAccepted() {
        presenter.setupAcceptedMap(fusedLocation);
    }

    @Override
    public void permissionRejected() {
        presenter.setupRejectedMap();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PermissionPresenter.FINE_LOCATION_REQUEST_CODE:
                presenter.checkPermission(grantResults, requestCode);
        }
    }

    @Override
    @SuppressLint("MissingPermission")
    public void disableMapPropertiesLocation() {
        map.setMyLocationEnabled(false);
        map.getUiSettings().setMyLocationButtonEnabled(false);
    }

    @Override
    @SuppressLint("MissingPermission")
    public void enableMapPropertiesLocation() {
        map.setMyLocationEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(true);
    }

    @Override
    public void focusOnLatLng(LatLng latLng, int zoom) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                latLng, zoom));
    }

    @Override
    public void showSnackBar(int message) {
        Snackbar.make(container, getString(message), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onMarkerDragStart(Marker marker) {}

    @Override
    public void onMarkerDrag(Marker marker) {}

    @Override
    public void onMarkerDragEnd(Marker marker) {
        presenter.updateLastPosition(marker.getPosition());
    }

    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void showLoadingOverlay() {
        loadingOverlayContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingOverlay() {
        loadingOverlayContainer.setVisibility(View.GONE);
    }

    private void createBookmarkDialog() {
        bookmarkDescriptionDialog = new DialogUtils(this);
        bookmarkDescriptionDialog.createBookmarkDialog(R.string.dialog_bookmark_title, R.string.dialog_bookmark_positive_button, R.string.dialog_bookmark_negative_button);
    }

    @OnClick(R.id.maps_current_fab)
    public void bookmarkClicked() {
        createBookmarkDialog();
    }

    @Override
    public void onDialogButtonPositiveClicked(String dialogResult) {
        presenter.saveBookmark(dialogResult);
        bookmarkDescriptionDialog = null;
    }

    @Override
    public void onDialogButtonNegativeClicked() {
        bookmarkDescriptionDialog = null;
    }

}
