package android.com.herramientime.modules.domain.repository;

import android.com.herramientime.modules.domain.entities.Categoria;
import android.com.rest.entities.InternetException;

import java.util.List;

public interface CategoriasRepository {
    List<Categoria> getCategorias() throws InternetException;
}
