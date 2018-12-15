package android.com.herramientime.modules.map.interactor;

import android.com.herramientime.modules.map.entities.MapType;
import android.com.herramientime.modules.map.entities.Point;

import com.google.android.gms.maps.model.LatLngBounds;
import com.seidor.core.task.executor.future.ResponseFuture;

import java.util.List;

public interface MapFragmentInteractor {
    ResponseFuture<List<Point>> startLoadData(MapType mapType, LatLngBounds currentCameraBounds);
}
