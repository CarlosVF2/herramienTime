package android.com.herramientime.modules.reservar.repository;

import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.rest.entities.InternetException;

public interface ReservaFragmentRepository {

    Experiencia getExperienciaById(String idExperiencia) throws InternetException;

    Herramienta getHerramientaById(String idHerramienta) throws InternetException;
}
