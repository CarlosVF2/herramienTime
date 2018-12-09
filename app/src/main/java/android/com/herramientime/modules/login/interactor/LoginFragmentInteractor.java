package android.com.herramientime.modules.login.interactor;


import android.com.herramientime.modules.login.entities.Login;
import android.com.herramientime.modules.usuarios.entities.Usuario;

import com.seidor.core.task.executor.future.ResponseFuture;

public interface LoginFragmentInteractor {

    ResponseFuture<Usuario> iniciarSesion(Login login);

    ResponseFuture<Usuario> registrar(Login login);
}
