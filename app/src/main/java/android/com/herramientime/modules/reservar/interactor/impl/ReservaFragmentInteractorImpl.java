package android.com.herramientime.modules.reservar.interactor.impl;

import android.com.herramientime.modules.reservar.interactor.ReservaFragmentInteractor;
import android.com.herramientime.modules.reservar.repository.ReservaFragmentRepository;

import com.seidor.core.task.executor.interactor.TaskInteractorImpl;
import com.seidor.core.utils.scheduler.UiScheduler;

public class ReservaFragmentInteractorImpl extends TaskInteractorImpl implements ReservaFragmentInteractor {

    private final ReservaFragmentRepository reservaFragmentRepository;

    public ReservaFragmentInteractorImpl(UiScheduler scheduler, ReservaFragmentRepository reservaFragmentRepository) {
        super(scheduler);
        this.reservaFragmentRepository = reservaFragmentRepository;
    }
}
