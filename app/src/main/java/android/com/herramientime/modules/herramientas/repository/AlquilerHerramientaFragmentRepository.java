package android.com.herramientime.modules.herramientas.repository;

import android.com.herramientime.modules.domain.entities.Categoria;
import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.domain.entities.Moneda;
import android.com.herramientime.modules.herramientas.entities.AlquilerHerramienta;
import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.rest.entities.InternetException;

import java.util.List;

public interface AlquilerHerramientaFragmentRepository {

    Herramienta saveHerramienta(AlquilerHerramienta alquilerHerramienta) throws InternetException, LocalException;

    List<Categoria> getCategorias() throws InternetException;

    List<Moneda> getMonedas() throws InternetException;
}
