package android.com.herramientime.modules.herramientas.interactor;

import android.com.herramientime.modules.herramientas.entities.Herramienta;

import com.seidor.core.task.executor.future.ResponseFuture;

public interface HerramientaDetalleFragmentInteractor {

    ResponseFuture<Herramienta> getHerramientaById(String idHerramienta);

    ResponseFuture<Boolean> checkReservar();
}
