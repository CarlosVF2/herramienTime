package android.com.herramientime.modules.herramientas.repository;

import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.rest.entities.InternetException;

import java.util.List;

public interface HerramientasFragmentRepository {

    List<Herramienta> getHerramientas() throws InternetException;

    Boolean checkUpload() throws InternetException, LocalException;
}
