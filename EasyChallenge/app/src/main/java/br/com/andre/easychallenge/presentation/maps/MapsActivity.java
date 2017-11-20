package br.com.andre.easychallenge.presentation.maps;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.andre.easychallenge.R;
import br.com.andre.easychallenge.presentation.maps.presenter.MapsPresenter;
import br.com.andre.easychallenge.presentation.permission.PermissionManager;
import br.com.andre.easychallenge.presentation.permission.PermissionManagerContract;
import br.com.andre.easychallenge.presentation.permission.PermissionPresenter;
import br.com.andre.easychallenge.presentation.permission.PermissionView;
import butterknife.BindView;
import butterknife.ButterKnife;

import static br.com.andre.easychallenge.presentation.permission.PermissionPresenter.ACCESS_FINE_LOCATION_PERMISSION;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, MapsView, SearchView.OnQueryTextListener,
        PermissionView {

    @BindView(R.id.maps_toolbar)
    Toolbar toolbar;

    @BindView(R.id.maps_current_fab)
    FloatingActionButton currentPositionFab;

    private GoogleMap map;
    MapsPresenter presenter;
    PermissionPresenter permissionPresenter;
    FusedLocationProviderClient fusedLocation;


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
        presenter = new MapsPresenter(this, permissionPresenter);
        fusedLocation = LocationServices.getFusedLocationProviderClient(this);
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
    public void updateMap(double latitude, double longitude, int zoom) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(latitude,
                        longitude), zoom));
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
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

}
