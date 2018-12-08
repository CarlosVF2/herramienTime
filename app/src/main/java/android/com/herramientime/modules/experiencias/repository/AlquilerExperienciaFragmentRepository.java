package android.com.herramientime.modules.experiencias.repository;

import android.com.herramientime.modules.experiencias.entities.AlquilerExperiencia;
import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.rest.entities.InternetException;

public interface AlquilerExperienciaFragmentRepository {

    Experiencia saveExperiencia(AlquilerExperiencia alquilerExperiencia) throws InternetException;
}
