package android.com.herramientime.modules.herramientas.interactor;

import android.com.herramientime.modules.herramientas.entities.FiltrosHerramientas;
import android.com.herramientime.modules.herramientas.entities.Herramienta;

import com.seidor.core.task.executor.future.ResponseFuture;

import java.util.List;

public interface HerramientasFragmentInteractor {

    ResponseFuture<List<Herramienta>> getHerramientas(FiltrosHerramientas filtrosHerramientas);

    ResponseFuture<Boolean> checkUpload();
}
