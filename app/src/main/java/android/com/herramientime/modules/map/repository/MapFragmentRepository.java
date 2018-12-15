package android.com.herramientime.modules.map.repository;

import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.map.entities.MapType;
import android.com.herramientime.modules.map.entities.Point;
import android.com.rest.entities.InternetException;

import com.google.android.gms.maps.model.LatLngBounds;

import java.util.List;

public interface MapFragmentRepository {
    List<Point> startLoadData(MapType mapType, LatLngBounds currentCameraBounds) throws LocalException, InternetException;
}
