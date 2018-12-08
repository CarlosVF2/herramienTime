package android.com.herramientime.modules.experiencias.repository;

import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.rest.entities.InternetException;

public interface ExperienciaDetalleFragmentRepository {

    Experiencia getExperienciaById(String idExperiencia) throws LocalException, InternetException;
}
