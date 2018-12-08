package android.com.herramientime.modules.experiencias.repository;

import android.com.herramientime.modules.experiencias.entities.Experiencia;

import java.util.List;

public interface ExperienciasFragmentRepository {

    List<Experiencia> getExperiencias() throws Exception;

}
