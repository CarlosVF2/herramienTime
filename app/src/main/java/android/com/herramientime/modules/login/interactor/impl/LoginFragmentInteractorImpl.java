package android.com.herramientime.modules.login.interactor.impl;

import android.com.herramientime.modules.login.entities.Login;
import android.com.herramientime.modules.login.interactor.LoginFragmentInteractor;
import android.com.herramientime.modules.login.repository.LoginFragmentRepository;
import android.com.herramientime.modules.usuarios.entities.Usuario;

import com.seidor.core.task.executor.future.ResponseFuture;
import com.seidor.core.task.executor.interactor.TaskInteractorImpl;
import com.seidor.core.utils.scheduler.UiScheduler;

import java.util.concurrent.Callable;

public class LoginFragmentInteractorImpl extends TaskInteractorImpl implements LoginFragmentInteractor {

    private final LoginFragmentRepository loginFragmentRepository;

    public LoginFragmentInteractorImpl(UiScheduler scheduler, LoginFragmentRepository loginFragmentRepository) {
        super(scheduler);
        this.loginFragmentRepository = loginFragmentRepository;
    }

    @Override
    public ResponseFuture<Usuario> iniciarSesion(final Login login) {
        return prepare(new Callable<Usuario>() {
            @Override
            public Usuario call() throws Exception {
                return loginFragmentRepository.iniciarSesion(login);
            }
        });
    }

    @Override
    public ResponseFuture<Usuario> registrar(final Login login) {
        return prepare(new Callable<Usuario>() {
            @Override
            public Usuario call() throws Exception {
                return loginFragmentRepository.registrar(login);
            }
        });
    }
}
