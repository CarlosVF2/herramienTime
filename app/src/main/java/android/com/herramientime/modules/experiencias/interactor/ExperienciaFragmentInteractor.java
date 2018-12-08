package android.com.herramientime.modules.experiencias.interactor;

import android.com.herramientime.modules.experiencias.entities.Experiencia;

import com.seidor.core.task.executor.future.ResponseFuture;

import java.util.List;

public interface ExperienciaFragmentInteractor {

    ResponseFuture<List<Experiencia>> getExperiencias();

}
