package android.com.herramientime.modules.map.interactor.impl;

import android.com.herramientime.modules.map.entities.MapType;
import android.com.herramientime.modules.map.entities.Point;
import android.com.herramientime.modules.map.interactor.MapFragmentInteractor;
import android.com.herramientime.modules.map.repository.MapFragmentRepository;

import com.google.android.gms.maps.model.LatLngBounds;
import com.seidor.core.task.executor.future.ResponseFuture;
import com.seidor.core.task.executor.interactor.TaskInteractorImpl;
import com.seidor.core.utils.scheduler.UiScheduler;

import java.util.List;
import java.util.concurrent.Callable;

public class MapFragmentInteractorImpl extends TaskInteractorImpl implements MapFragmentInteractor {

    private MapFragmentRepository mapFragmentRepository;

    public MapFragmentInteractorImpl(UiScheduler scheduler, MapFragmentRepository mapFragmentRepository) {
        super(scheduler);
        this.mapFragmentRepository = mapFragmentRepository;
    }


    @Override
    public ResponseFuture<List<Point>> startLoadData(final MapType mapType, final LatLngBounds currentCameraBounds) {
        return prepare(new Callable<List<Point>>() {
            @Override
            public List<Point> call() throws Exception {
                return mapFragmentRepository.startLoadData(mapType, currentCameraBounds);
            }
        });
    }
}