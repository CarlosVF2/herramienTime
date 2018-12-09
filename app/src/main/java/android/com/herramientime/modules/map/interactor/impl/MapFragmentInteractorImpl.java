package android.com.herramientime.modules.map.interactor.impl;

import android.com.herramientime.modules.map.interactor.MapFragmentInteractor;
import android.com.herramientime.modules.map.repository.MapFragmentRepository;

import com.seidor.core.task.executor.interactor.TaskInteractorImpl;
import com.seidor.core.utils.scheduler.UiScheduler;

public class MapFragmentInteractorImpl extends TaskInteractorImpl implements MapFragmentInteractor {

    private MapFragmentRepository mapFragmentRepository;

    public MapFragmentInteractorImpl(UiScheduler scheduler, MapFragmentRepository mapFragmentRepository) {
        super(scheduler);
        this.mapFragmentRepository = mapFragmentRepository;
    }


}