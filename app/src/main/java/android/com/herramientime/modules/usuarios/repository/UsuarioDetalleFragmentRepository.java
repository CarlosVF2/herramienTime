package android.com.herramientime.modules.usuarios.repository;

import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.usuarios.entities.Usuario;
import android.com.rest.entities.InternetException;

public interface UsuarioDetalleFragmentRepository {

    Usuario getUserById(String id) throws InternetException;

    Boolean cerrarSesion() throws LocalException;
}
