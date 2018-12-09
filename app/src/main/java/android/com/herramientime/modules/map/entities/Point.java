package android.com.herramientime.modules.map.entities;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Point implements Serializable {

    @SerializedName("status")
    public String status;
    @SerializedName("results")
    private List<Results> results;

    public static class Address_components implements Serializable {
        @SerializedName("types")
        private List<String> types;
        @SerializedName("short_name")
        private String short_name;
        @SerializedName("long_name")
        private String long_name;
    }

    public static class Location implements Serializable {
        @SerializedName("lng")
        private double lng;
        @SerializedName("lat")
        private double lat;
    }

    public static class Geometry implements Serializable {
        @SerializedName("location_type")
        private String location_type;
        @SerializedName("location")
        private Location location;

    }

    public static class Results implements Serializable {
        @SerializedName("types")
        private List<String> types;
        @SerializedName("place_id")
        private String place_id;
        @SerializedName("geometry")
        private Geometry geometry;
        @SerializedName("formatted_address")
        private String formatted_address;
        @SerializedName("address_components")
        private List<Address_components> address_components;
    }

    private String id;
    private String addressToSearch;

    public LatLng getLatLng() {
        return new LatLng(results.get(0).geometry.location.lat, results.get(0).geometry.location.lng);
    }

    public String getTitle() {
        return results.get(0).formatted_address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddressToSearch() {
        return addressToSearch;
    }

    public void setAddressToSearch(String addressToSearch) {
        this.addressToSearch = addressToSearch;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Point)) {
            return false;
        }

        Point point = (Point) o;

        return point.getLatLng() == getLatLng();
    }

    //Idea from effective Java : Item 9
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + getLatLng().hashCode();
        return result;
    }
}
