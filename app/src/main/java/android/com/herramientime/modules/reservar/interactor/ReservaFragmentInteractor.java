package android.com.herramientime.modules.reservar.interactor;


import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.herramientime.modules.herramientas.entities.Herramienta;

import com.seidor.core.task.executor.future.ResponseFuture;

public interface ReservaFragmentInteractor {

    ResponseFuture<Experiencia> getExperienciaById(String idExperiencia);

    ResponseFuture<Herramienta> getHerramientaById(String idHerramienta);
}
