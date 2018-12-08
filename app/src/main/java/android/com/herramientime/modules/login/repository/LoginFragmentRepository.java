package android.com.herramientime.modules.login.repository;

import android.com.herramientime.modules.login.entities.Login;
import android.com.herramientime.modules.usuarios.entities.Usuario;
import android.com.rest.entities.InternetException;

public interface LoginFragmentRepository {

    Usuario iniciarSesion(Login login) throws InternetException;
}
