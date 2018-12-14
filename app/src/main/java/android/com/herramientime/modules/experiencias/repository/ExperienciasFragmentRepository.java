package android.com.herramientime.modules.experiencias.repository;

import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.herramientime.modules.herramientas.entities.FiltrosExperiencia;
import android.com.rest.entities.InternetException;

import java.util.List;

public interface ExperienciasFragmentRepository {

    List<Experiencia> getExperiencias(FiltrosExperiencia filtrosExperiencia) throws Exception;

    Boolean checkUpload() throws InternetException, LocalException;
}
