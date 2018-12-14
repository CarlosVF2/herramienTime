package android.com.herramientime.modules.experiencias.interactor;

import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.herramientime.modules.herramientas.entities.FiltrosExperiencia;

import com.seidor.core.task.executor.future.ResponseFuture;

import java.util.List;

public interface ExperienciaFragmentInteractor {

    ResponseFuture<List<Experiencia>> getExperiencias(FiltrosExperiencia filtrosExperiencia);

    ResponseFuture<Boolean> checkUpload();
}
