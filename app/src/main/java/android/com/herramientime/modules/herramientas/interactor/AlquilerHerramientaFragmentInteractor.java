package android.com.herramientime.modules.herramientas.interactor;

import android.com.herramientime.modules.herramientas.entities.AlquilerHerramienta;
import android.com.herramientime.modules.herramientas.entities.Herramienta;

import com.seidor.core.task.executor.future.ResponseFuture;

public interface AlquilerHerramientaFragmentInteractor {

    ResponseFuture<Herramienta> saveHerramienta(AlquilerHerramienta alquilerHerramienta);
}
