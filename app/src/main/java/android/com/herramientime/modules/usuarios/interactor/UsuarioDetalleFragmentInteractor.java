package android.com.herramientime.modules.usuarios.interactor;


import android.com.herramientime.modules.usuarios.entities.Usuario;

import com.seidor.core.task.executor.future.ResponseFuture;

public interface UsuarioDetalleFragmentInteractor {

    ResponseFuture<Usuario> getUsuario(String idUsuario);

    ResponseFuture<Boolean> cerrarSesion();
}
