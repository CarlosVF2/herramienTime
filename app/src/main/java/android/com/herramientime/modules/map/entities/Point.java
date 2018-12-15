package android.com.herramientime.modules.map.entities;

import com.google.android.gms.maps.model.LatLng;

public class Point {

    private double latitud;
    private double logintud;
    private String title;
    private String id;

    public Point(double latitud, double logintud) {
        this.latitud = latitud;
        this.logintud = logintud;
    }

    public LatLng getLatLng() {
        return new LatLng(latitud, logintud);
    }

    //region GET


    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLogintud() {
        return logintud;
    }

    //endregion GET

    //region SET


    public Point setTitle(String title) {
        this.title = title;
        return this;
    }

    public Point setId(String id) {
        this.id = id;
        return this;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public void setLogintud(double logintud) {
        this.logintud = logintud;
    }

    //endregion SET

}
