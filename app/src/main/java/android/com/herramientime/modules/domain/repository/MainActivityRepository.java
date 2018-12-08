package android.com.herramientime.modules.domain.repository;

import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.usuarios.entities.Usuario;
import android.com.rest.entities.InternetException;

public interface MainActivityRepository {

    Usuario getLoggedUser() throws InternetException, LocalException;
}
