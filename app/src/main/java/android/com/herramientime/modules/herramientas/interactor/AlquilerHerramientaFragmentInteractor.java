package android.com.herramientime.modules.herramientas.interactor;

import android.com.herramientime.modules.domain.entities.Categoria;
import android.com.herramientime.modules.domain.entities.Moneda;
import android.com.herramientime.modules.herramientas.entities.AlquilerHerramienta;
import android.com.herramientime.modules.herramientas.entities.Herramienta;

import com.seidor.core.task.executor.future.ResponseFuture;

import java.util.List;

public interface AlquilerHerramientaFragmentInteractor {

    ResponseFuture<Herramienta> saveHerramienta(AlquilerHerramienta alquilerHerramienta);

    ResponseFuture<List<Categoria>> getCategorias();

    ResponseFuture<List<Moneda>> getMonedas();
}
