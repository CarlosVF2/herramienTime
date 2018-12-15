package android.com.herramientime.modules.herramientas.repository;

import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.domain.entities.UsuarioException;
import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.rest.entities.InternetException;

public interface HerramientaDetalleFragmentRepository {

    Herramienta getHerramientaById(String idHerramienta) throws LocalException, InternetException;

    Boolean checkReservar() throws LocalException, InternetException, UsuarioException;
}
