package android.com.herramientime.modules.herramientas.interactor.impl;

import android.com.herramientime.modules.herramientas.entities.AlquilerHerramienta;
import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.herramientime.modules.herramientas.interactor.AlquilerHerramientaFragmentInteractor;
import android.com.herramientime.modules.herramientas.repository.AlquilerHerramientaFragmentRepository;

import com.seidor.core.task.executor.future.ResponseFuture;
import com.seidor.core.task.executor.interactor.TaskInteractorImpl;
import com.seidor.core.utils.scheduler.UiScheduler;

import java.util.concurrent.Callable;

public class AlquilerHerramientaFragmentInteractorImpl extends TaskInteractorImpl implements AlquilerHerramientaFragmentInteractor {

    private final AlquilerHerramientaFragmentRepository alquilerHerramientaFragmentRepository;

    public AlquilerHerramientaFragmentInteractorImpl(UiScheduler scheduler, AlquilerHerramientaFragmentRepository alquilerHerramientaFragmentRepository) {
        super(scheduler);
        this.alquilerHerramientaFragmentRepository = alquilerHerramientaFragmentRepository;
    }

    @Override
    public ResponseFuture<Herramienta> saveHerramienta(final AlquilerHerramienta alquilerHerramienta) {
        return prepare(new Callable<Herramienta>() {
            @Override
            public Herramienta call() throws Exception {
                return alquilerHerramientaFragmentRepository.saveHerramienta(alquilerHerramienta);
            }
        });
    }
}
