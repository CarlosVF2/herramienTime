package android.com.herramientime.modules.experiencias.interactor.impl;

import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.herramientime.modules.experiencias.interactor.ExperienciaDetalleFragmentInteractor;
import android.com.herramientime.modules.experiencias.repository.ExperienciaDetalleFragmentRepository;

import com.seidor.core.task.executor.future.ResponseFuture;
import com.seidor.core.task.executor.interactor.TaskInteractorImpl;
import com.seidor.core.utils.scheduler.UiScheduler;

import java.util.concurrent.Callable;

public class ExperienciaDetalleFragmentInteractorImpl extends TaskInteractorImpl implements ExperienciaDetalleFragmentInteractor {

    private final ExperienciaDetalleFragmentRepository experienciaDetalleFragmentRepository;

    public ExperienciaDetalleFragmentInteractorImpl(UiScheduler scheduler, ExperienciaDetalleFragmentRepository experienciaDetalleFragmentRepository) {
        super(scheduler);
        this.experienciaDetalleFragmentRepository = experienciaDetalleFragmentRepository;
    }

    @Override
    public ResponseFuture<Experiencia> getExperienciaById(final String idExperiencia) {
        return prepare(new Callable<Experiencia>() {
            @Override
            public Experiencia call() throws Exception {
                return experienciaDetalleFragmentRepository.getExperienciaById(idExperiencia);
            }
        });
    }

    @Override
    public ResponseFuture<Boolean> checkReservar() {
        return prepare(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return experienciaDetalleFragmentRepository.checkReservar();
            }
        });
    }
}
