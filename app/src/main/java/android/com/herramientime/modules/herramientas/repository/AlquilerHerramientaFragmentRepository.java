package android.com.herramientime.modules.herramientas.repository;

import android.com.herramientime.modules.herramientas.entities.AlquilerHerramienta;
import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.rest.entities.InternetException;

public interface AlquilerHerramientaFragmentRepository {

    Herramienta saveHerramienta(AlquilerHerramienta alquilerHerramienta) throws InternetException;
}
