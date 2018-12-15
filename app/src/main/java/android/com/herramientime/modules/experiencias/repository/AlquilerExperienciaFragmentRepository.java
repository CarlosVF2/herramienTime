package android.com.herramientime.modules.experiencias.repository;

import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.domain.entities.Moneda;
import android.com.herramientime.modules.domain.entities.UsuarioException;
import android.com.herramientime.modules.experiencias.entities.AlquilerExperiencia;
import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.rest.entities.InternetException;

import java.util.List;

public interface AlquilerExperienciaFragmentRepository {

    Experiencia saveExperiencia(AlquilerExperiencia alquilerExperiencia) throws InternetException, LocalException, UsuarioException;

    List<Moneda> getMonedas() throws InternetException;
}
