package android.com.herramientime.modules.domain.interactor;

import android.com.herramientime.modules.usuarios.entities.Usuario;

import com.seidor.core.task.executor.future.ResponseFuture;

public interface MainActivityInteractor {

    ResponseFuture<Usuario> getLoggedUser();
}
