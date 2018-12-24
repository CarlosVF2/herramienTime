package android.com.herramientime.modules.herramientas.interactor;

import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.herramientime.modules.usuarios.entities.Usuario;

import com.seidor.core.task.executor.future.ResponseFuture;

public interface HerramientaDetalleFragmentInteractor {

    ResponseFuture<Herramienta> getHerramientaById(String idHerramienta);

    ResponseFuture<Boolean> checkReservar();

    ResponseFuture<Usuario> getUsuario(String idUsuario);
}
