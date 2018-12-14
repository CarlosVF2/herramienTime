package android.com.herramientime.modules.herramientas.interactor.impl;

import android.com.herramientime.modules.herramientas.entities.FiltrosHerramientas;
import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.herramientime.modules.herramientas.interactor.HerramientasFragmentInteractor;
import android.com.herramientime.modules.herramientas.repository.HerramientasFragmentRepository;

import com.seidor.core.task.executor.future.ResponseFuture;
import com.seidor.core.task.executor.interactor.TaskInteractorImpl;
import com.seidor.core.utils.scheduler.UiScheduler;

import java.util.List;
import java.util.concurrent.Callable;

public class HerramientasFragmentInteractorImpl extends TaskInteractorImpl implements HerramientasFragmentInteractor {

    private final HerramientasFragmentRepository herramientasFragmentRepository;

    public HerramientasFragmentInteractorImpl(UiScheduler scheduler, HerramientasFragmentRepository herramientasFragmentRepository) {
        super(scheduler);
        this.herramientasFragmentRepository = herramientasFragmentRepository;
    }

    @Override
    public ResponseFuture<List<Herramienta>> getHerramientas(final FiltrosHerramientas filtrosHerramientas) {
        return prepare(new Callable<List<Herramienta>>() {
            @Override
            public List<Herramienta> call() throws Exception {
                return herramientasFragmentRepository.getHerramientas(filtrosHerramientas);
            }
        });
    }

    @Override
    public ResponseFuture<Boolean> checkUpload() {
        return prepare(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return herramientasFragmentRepository.checkUpload();
            }
        });
    }
}
