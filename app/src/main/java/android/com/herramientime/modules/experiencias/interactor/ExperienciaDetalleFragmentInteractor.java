package android.com.herramientime.modules.experiencias.interactor;

import android.com.herramientime.modules.experiencias.entities.Experiencia;

import com.seidor.core.task.executor.future.ResponseFuture;

public interface ExperienciaDetalleFragmentInteractor {

    ResponseFuture<Experiencia> getExperienciaById(String idExperiencia);
}
