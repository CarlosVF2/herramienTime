package android.com.herramientime.modules.reservar.repository;

import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.rest.entities.InternetException;

import java.util.Date;

public interface ReservaFragmentRepository {

    Experiencia getExperienciaById(String idExperiencia) throws InternetException;

    Herramienta getHerramientaById(String idHerramienta) throws InternetException;

    Boolean reservar(Experiencia experiencia, Herramienta herramienta, Date fechaInicial, Date fechaFinal) throws InternetException;
}
