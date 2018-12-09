package android.com.herramientime.modules.reservar.interactor.impl;

import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.herramientime.modules.reservar.interactor.ReservaFragmentInteractor;
import android.com.herramientime.modules.reservar.repository.ReservaFragmentRepository;

import com.seidor.core.task.executor.future.ResponseFuture;
import com.seidor.core.task.executor.interactor.TaskInteractorImpl;
import com.seidor.core.utils.scheduler.UiScheduler;

import java.util.concurrent.Callable;

public class ReservaFragmentInteractorImpl extends TaskInteractorImpl implements ReservaFragmentInteractor {

    private final ReservaFragmentRepository reservaFragmentRepository;

    public ReservaFragmentInteractorImpl(UiScheduler scheduler, ReservaFragmentRepository reservaFragmentRepository) {
        super(scheduler);
        this.reservaFragmentRepository = reservaFragmentRepository;
    }

    @Override
    public ResponseFuture<Experiencia> getExperienciaById(final String idExperiencia) {
        return prepare(new Callable<Experiencia>() {
            @Override
            public Experiencia call() throws Exception {
                return reservaFragmentRepository.getExperienciaById(idExperiencia);
            }
        });
    }

    @Override
    public ResponseFuture<Herramienta> getHerramientaById(final String idHerramienta) {
        return prepare(new Callable<Herramienta>() {
            @Override
            public Herramienta call() throws Exception {
                return reservaFragmentRepository.getHerramientaById(idHerramienta);
            }
        });
    }
}
