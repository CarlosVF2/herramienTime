package android.com.herramientime.modules.experiencias.interactor.impl;

import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.herramientime.modules.experiencias.interactor.ExperienciaFragmentInteractor;
import android.com.herramientime.modules.experiencias.repository.ExperienciasFragmentRepository;
import android.com.herramientime.modules.herramientas.entities.FiltrosExperiencia;

import com.seidor.core.task.executor.future.ResponseFuture;
import com.seidor.core.task.executor.interactor.TaskInteractorImpl;
import com.seidor.core.utils.scheduler.UiScheduler;

import java.util.List;
import java.util.concurrent.Callable;

public class ExperienciaFragmentInteractorImpl extends TaskInteractorImpl implements ExperienciaFragmentInteractor {

    private final ExperienciasFragmentRepository experienciasFragmentRepository;

    public ExperienciaFragmentInteractorImpl(UiScheduler scheduler, ExperienciasFragmentRepository experienciasFragmentRepository) {
        super(scheduler);
        this.experienciasFragmentRepository = experienciasFragmentRepository;
    }

    @Override
    public ResponseFuture<List<Experiencia>> getExperiencias(final FiltrosExperiencia filtrosExperiencia) {
        return prepare(new Callable<List<Experiencia>>() {
            @Override
            public List<Experiencia> call() throws Exception {
                return experienciasFragmentRepository.getExperiencias(filtrosExperiencia);
            }
        });
    }

    @Override
    public ResponseFuture<Boolean> checkUpload() {
        return prepare(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return experienciasFragmentRepository.checkUpload();
            }
        });
    }
}
