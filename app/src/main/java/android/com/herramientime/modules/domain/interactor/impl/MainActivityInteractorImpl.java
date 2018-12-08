package android.com.herramientime.modules.domain.interactor.impl;

import android.com.herramientime.modules.domain.interactor.MainActivityInteractor;
import android.com.herramientime.modules.domain.repository.MainActivityRepository;
import android.com.herramientime.modules.usuarios.entities.Usuario;

import com.seidor.core.task.executor.future.ResponseFuture;
import com.seidor.core.task.executor.interactor.TaskInteractorImpl;
import com.seidor.core.utils.scheduler.UiScheduler;

import java.util.concurrent.Callable;

public class MainActivityInteractorImpl extends TaskInteractorImpl implements MainActivityInteractor {

    private MainActivityRepository mainActivityRepository;

    public MainActivityInteractorImpl(UiScheduler uiScheduler, MainActivityRepository mainActivityRepository) {
        super(uiScheduler);
        this.mainActivityRepository = mainActivityRepository;
    }

    @Override
    public ResponseFuture<Usuario> getLoggedUser() {
        return prepare(new Callable<Usuario>() {
            @Override
            public Usuario call() throws Exception {
                return mainActivityRepository.getLoggedUser();
            }
        });
    }
}
