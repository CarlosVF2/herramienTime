package android.com.herramientime.modules.experiencias.interactor;

import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.herramientime.modules.usuarios.entities.Usuario;

import com.seidor.core.task.executor.future.OnData;
import com.seidor.core.task.executor.future.ResponseFuture;

public interface ExperienciaDetalleFragmentInteractor {

    ResponseFuture<Experiencia> getExperienciaById(String idExperiencia);

    ResponseFuture<Boolean> checkReservar();

    ResponseFuture<Usuario> getUsuario(String idUsuario);
}
