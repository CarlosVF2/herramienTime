package android.com.herramientime.modules.herramientas.interactor.impl;

import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.herramientime.modules.herramientas.interactor.HerramientaDetalleFragmentInteractor;
import android.com.herramientime.modules.herramientas.repository.HerramientaDetalleFragmentRepository;

import com.seidor.core.task.executor.future.ResponseFuture;
import com.seidor.core.task.executor.interactor.TaskInteractorImpl;
import com.seidor.core.utils.scheduler.UiScheduler;

import java.util.concurrent.Callable;

public class HerramientaDetalleFragmentInteractorImpl extends TaskInteractorImpl implements HerramientaDetalleFragmentInteractor {

    private final HerramientaDetalleFragmentRepository herramientaDetalleFragmentRepository;

    public HerramientaDetalleFragmentInteractorImpl(UiScheduler scheduler, HerramientaDetalleFragmentRepository herramientaDetalleFragmentRepository) {
        super(scheduler);
        this.herramientaDetalleFragmentRepository = herramientaDetalleFragmentRepository;
    }

    @Override
    public ResponseFuture<Herramienta> getHerramientaById(final String idHerramienta) {
        return prepare(new Callable<Herramienta>() {
            @Override
            public Herramienta call() throws Exception {
                return herramientaDetalleFragmentRepository.getHerramientaById(idHerramienta);
            }
        });
    }
}
