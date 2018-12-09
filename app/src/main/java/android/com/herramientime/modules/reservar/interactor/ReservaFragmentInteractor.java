package android.com.herramientime.modules.reservar.interactor;


import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.herramientime.modules.herramientas.entities.Herramienta;

import com.seidor.core.task.executor.future.ResponseFuture;

import java.util.Date;

public interface ReservaFragmentInteractor {

    ResponseFuture<Experiencia> getExperienciaById(String idExperiencia);

    ResponseFuture<Herramienta> getHerramientaById(String idHerramienta);

    ResponseFuture<Boolean> save(Experiencia experiencia, Herramienta herramienta, Date fechaInicial, Date fechaFinal);
}
