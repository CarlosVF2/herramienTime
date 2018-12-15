package android.com.herramientime.modules.map.entities;

import android.com.herramientime.core.entities.BasePresenterStatus;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;
import java.util.List;

public class MapPresenterStatus extends BasePresenterStatus {

    private static final String EXTRA__CAMERA_LATITUDE = "EXTRA__CAMERA_LATITUDE";
    private static final String EXTRA__CAMERA_LONGITUDE = "EXTRA__CAMERA_LONGITUDE";
    private static final String EXTRA__CAMERA_BOUND_NORTH = "EXTRA__CAMERA_BOUND_NORTH";
    private static final String EXTRA__CAMERA_BOUND_EAST = "EXTRA__CAMERA_BOUND_EAST";
    private static final String EXTRA__CAMERA_BOUND_SOUTH = "EXTRA__CAMERA_BOUND_SOUTH";
    private static final String EXTRA__CAMERA_BOUND_WEST = "EXTRA__CAMERA_BOUND_WEST";

    private double cameraLatitude;
    private double cameraLongitude;
    private double northCameraBound;
    private double eastCameraBound;
    private double southCameraBound;
    private double westCameraBound;
    private List<Point> puntos = new ArrayList<>();
    private Exception error;

    @Override
    public void saveInstance(Bundle saveInstance) {
        saveInstance.putDouble(EXTRA__CAMERA_LATITUDE, getCameraLatitude());
        saveInstance.putDouble(EXTRA__CAMERA_LONGITUDE, getCameraLongitude());
        saveInstance.putDouble(EXTRA__CAMERA_BOUND_NORTH, getNorthCameraBound());
        saveInstance.putDouble(EXTRA__CAMERA_BOUND_EAST, getEastCameraBound());
        saveInstance.putDouble(EXTRA__CAMERA_BOUND_SOUTH, getSouthCameraBound());
        saveInstance.putDouble(EXTRA__CAMERA_BOUND_WEST, getWestCameraBound());
    }

    @Override
    public void restoreInstance(Bundle restoreInstance) {
        if (restoreInstance.containsKey(EXTRA__CAMERA_LATITUDE))
            setCameraLatitude(restoreInstance.getDouble(EXTRA__CAMERA_LATITUDE));
        if (restoreInstance.containsKey(EXTRA__CAMERA_LONGITUDE))
            setCameraLongitude(restoreInstance.getDouble(EXTRA__CAMERA_LONGITUDE));
        if (restoreInstance.containsKey(EXTRA__CAMERA_BOUND_NORTH))
            setNorthCameraBound(restoreInstance.getDouble(EXTRA__CAMERA_BOUND_NORTH));
        if (restoreInstance.containsKey(EXTRA__CAMERA_BOUND_EAST))
            setEastCameraBound(restoreInstance.getDouble(EXTRA__CAMERA_BOUND_EAST));
        if (restoreInstance.containsKey(EXTRA__CAMERA_BOUND_SOUTH))
            setSouthCameraBound(restoreInstance.getDouble(EXTRA__CAMERA_BOUND_SOUTH));
        if (restoreInstance.containsKey(EXTRA__CAMERA_BOUND_WEST))
            setWestCameraBound(restoreInstance.getDouble(EXTRA__CAMERA_BOUND_WEST));

    }

    public void setCurrentCameraPosition(double latitude, double longitude) {
        this.cameraLatitude = latitude;
        this.cameraLongitude = longitude;
    }

    public LatLng getCurrentCameraPosition() {
        return new LatLng(getCameraLatitude(), getCameraLongitude());
    }

    private void setCameraLatitude(double latitude) {
        this.cameraLatitude = latitude;
    }

    private void setCameraLongitude(double longitude) {
        this.cameraLongitude = longitude;
    }

    private double getCameraLatitude() {
        return cameraLatitude;
    }

    private double getCameraLongitude() {
        return cameraLongitude;
    }

    public void setCurrentCameraBounds(LatLngBounds currentCameraBounds) {
        this.northCameraBound = currentCameraBounds.northeast.latitude;
        this.eastCameraBound = currentCameraBounds.northeast.longitude;
        this.southCameraBound = currentCameraBounds.southwest.latitude;
        this.westCameraBound = currentCameraBounds.southwest.longitude;
    }

    public LatLngBounds getCurrentCameraBounds() {
        return LatLngBounds.builder()
                .include(new LatLng(northCameraBound, eastCameraBound))
                .include(new LatLng(southCameraBound, westCameraBound))
                .build();
    }

    private double getNorthCameraBound() {
        return northCameraBound;
    }

    private double getEastCameraBound() {
        return eastCameraBound;
    }

    private double getSouthCameraBound() {
        return southCameraBound;
    }

    private double getWestCameraBound() {
        return westCameraBound;
    }

    private void setNorthCameraBound(double northCameraBound) {
        this.northCameraBound = northCameraBound;
    }

    private void setEastCameraBound(double eastCameraBound) {
        this.eastCameraBound = eastCameraBound;
    }

    private void setSouthCameraBound(double southCameraBound) {
        this.southCameraBound = southCameraBound;
    }

    private void setWestCameraBound(double westCameraBound) {
        this.westCameraBound = westCameraBound;
    }

    public void setPuntos(List<Point> puntos) {
        this.puntos = puntos;
    }

    public List<Point> getPuntos() {
        return puntos;
    }

    public void setError(Exception error) {
        this.error = error;
    }

    public Exception getError() {
        return error;
    }
}
