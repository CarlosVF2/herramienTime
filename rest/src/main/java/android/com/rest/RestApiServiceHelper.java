package android.com.rest;


import android.com.rest.entities.ExperienciaRest;
import android.com.rest.entities.HerramientaRest;
import android.com.rest.entities.InternetException;
import android.com.rest.entities.UsuariosRest;

import java.util.List;

/**
 * Created by cvegaf on 19/11/2018.
 * <p>
 * Encargado de los USER CASE ( devolver objetos que entienda el Repository, entrada de valores ... )
 */

public interface RestApiServiceHelper {

    List<HerramientaRest> getHerramientas() throws InternetException;

    List<ExperienciaRest> getExperiencias() throws InternetException;

    List<UsuariosRest> getUsuarios() throws InternetException;

    void postHerramienta(List<HerramientaRest> herramientaResponse) throws InternetException;

    void postExperiencia(List<ExperienciaRest> experienciaRests) throws InternetException;
}
