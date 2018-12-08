package android.com.herramientime.modules.experiencias.interactor.impl;

import android.com.herramientime.modules.experiencias.entities.AlquilerExperiencia;
import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.herramientime.modules.experiencias.interactor.AlquilerExperienciaFragmentInteractor;
import android.com.herramientime.modules.experiencias.repository.AlquilerExperienciaFragmentRepository;

import com.seidor.core.task.executor.future.ResponseFuture;
import com.seidor.core.task.executor.interactor.TaskInteractorImpl;
import com.seidor.core.utils.scheduler.UiScheduler;

import java.util.concurrent.Callable;

public class AlquilerExperienciaFragmentInteractorImpl extends TaskInteractorImpl implements AlquilerExperienciaFragmentInteractor {

    private final AlquilerExperienciaFragmentRepository alquilerExperienciaFragmentRepository;

    public AlquilerExperienciaFragmentInteractorImpl(UiScheduler scheduler, AlquilerExperienciaFragmentRepository alquilerHerramientaFragmentRepository) {
        super(scheduler);
        this.alquilerExperienciaFragmentRepository = alquilerHerramientaFragmentRepository;
    }

    @Override
    public ResponseFuture<Experiencia> saveExperiencia(final AlquilerExperiencia alquilerExperiencia) {
        return prepare(new Callable<Experiencia>() {
            @Override
            public Experiencia call() throws Exception {
                return alquilerExperienciaFragmentRepository.saveExperiencia(alquilerExperiencia);
            }
        });
    }
}
