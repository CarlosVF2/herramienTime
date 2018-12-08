package android.com.herramientime.modules.experiencias.interactor;

import android.com.herramientime.modules.experiencias.entities.AlquilerExperiencia;
import android.com.herramientime.modules.experiencias.entities.Experiencia;

import com.seidor.core.task.executor.future.ResponseFuture;

public interface AlquilerExperienciaFragmentInteractor {

    ResponseFuture<Experiencia> saveExperiencia(AlquilerExperiencia alquilerExperiencia);
}
