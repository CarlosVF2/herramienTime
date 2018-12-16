package android.com.herramientime.modules.map.view.impl;

import android.annotation.SuppressLint;
import android.com.herramientime.R;
import android.com.herramientime.core.view.impl.MvpFragmentImpl;
import android.com.herramientime.modules.map.entities.Point;
import android.com.herramientime.modules.map.presenter.MapFragmentPresenter;
import android.com.herramientime.modules.map.view.MapFragment;
import android.content.DialogInterface;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import pub.devrel.easypermissions.EasyPermissions;

public class MapFragmentImpl<PRESENTER extends MapFragmentPresenter> extends MvpFragmentImpl<PRESENTER>
        implements MapFragment, OnMapReadyCallback, GoogleMap.OnCameraMoveStartedListener, GoogleMap.OnInfoWindowClickListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener, GoogleMap.OnCameraIdleListener {

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private LocationRequest mLocationRequest;
    private static final int RC_LOCATION_PERM = 0x1;

    private LatLngBounds.Builder builder = new LatLngBounds.Builder();
    private int width;
    private int height;
    private int padding;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleClient;
    private MapView mapView;
    private View view;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapsInitializer.initialize(getContext());

        mGoogleClient = new GoogleApiClient.Builder(requireContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        //creating locationRequest object
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                .setInterval(60 * 1000)       //10 seconds
                .setFastestInterval(1000);//1 second
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        // Mapview
        mapView = v.findViewById(R.id.mapview);
        mapView.onCreate(mapViewBundle);

        mapView.getMapAsync(this);

    }

    @SuppressLint("MissingPermission")
    private void startMapClient() {
        // Have permission, do the thing!
        if (mMap != null) {
            mMap.setMyLocationEnabled(true);
            mGoogleClient.connect();
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //listeners
        mMap.setOnCameraMoveStartedListener(this);
        mMap.setOnInfoWindowClickListener(this);
        mMap.setOnCameraIdleListener(this);

        width = getResources().getDisplayMetrics().widthPixels;
        height = getResources().getDisplayMetrics().heightPixels;
        padding = (int) (width * 0.30); // offset from edges of the map 10% of screen

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    public void addMarker(Point point) {
        mMap.addMarker(new MarkerOptions().position(point.getLatLng()).title(point.getTitle()).snippet(point.getId()));
        builder.include(point.getLatLng());
        LatLngBounds bounds = builder.build();
        moveCameraToPoints(bounds);

    }

    @Override
    public void moveCameraToPoints(LatLngBounds bounds) {
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);
        mMap.animateCamera(cameraUpdate);
    }

    public void clearMap() {
        if (mMap != null) {
            mMap.clear();
        }
    }

    @Override
    public void showAlertDialogMessage(String s) {
        new android.support.v7.app.AlertDialog.Builder(getContext())
                .setTitle(getString(R.string.title_dialog_atencion))
                .setMessage(s)
                .setPositiveButton(getString(android.R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                    }
                })
                .setNegativeButton(getString(android.R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .create().show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }


    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onStart() {
        mapView.onStart();
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
        if (mGoogleClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleClient, this);
            mGoogleClient.disconnect();
        }
    }

    @Override
    public void onDestroy() {
        if (mapView != null) {
            mapView.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();

    }

    @Override
    public void onCameraMoveStarted(int reason) {
        if (reason == GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE) {
            //The user gestured on the map.

        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        //TODO: Click en la información del marker
    }


    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(location);
    }


    @SuppressLint("MissingPermission")
    @Override
    public void onConnected(@Nullable Bundle bundle) {

        @SuppressLint("MissingPermission") Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleClient);

        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleClient, mLocationRequest, this);

        } else {
            handleNewLocation(location);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(getActivity(), CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleNewLocation(Location location) {
        mMap.clear();
        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();

        LatLng latLng = new LatLng(currentLatitude, currentLongitude);

        MarkerOptions options = new MarkerOptions()
                .position(latLng)
                .title("Yo");

        mMap.addMarker(options);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    @Override
    public void onCameraIdle() {
        LatLng target = mMap.getCameraPosition().target;
        LatLngBounds latLngBounds = mMap.getProjection().getVisibleRegion().latLngBounds;
        getMvpPresenter().onCameraPositionChanged(target.latitude, target.longitude);
        getMvpPresenter().onCameraBoundsChanged(latLngBounds);
    }

    @Override
    public void onInitLoading() {

    }

    @Override
    public void onLoaded() {

    }
}