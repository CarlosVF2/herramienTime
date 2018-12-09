package android.com.herramientime.modules.map.view;

import android.com.herramientime.core.view.MvpFragment;
import android.com.herramientime.modules.map.entities.Point;

import com.google.android.gms.maps.model.LatLngBounds;

public interface MapFragment extends MvpFragment {

    void addMarker(Point point);

    void moveCameraToPoints(LatLngBounds bounds);

    void clearMap();
}