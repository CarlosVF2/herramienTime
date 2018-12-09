package android.com.herramientime.domain.processor;

import android.com.herramientime.modules.usuarios.entities.Usuario;
import android.com.rest.entities.UsuariosRest;

import java.util.ArrayList;
import java.util.List;

public class ProcessorUsuario {


    public List<Usuario> convertFrom(List<UsuariosRest> usuariosRests) {
        List<Usuario> usuarios = new ArrayList<>();
        for (UsuariosRest usuarioRest : usuariosRests) {
            usuarios.add(convertFrom(usuarioRest));
        }
        return usuarios;
    }

    public Usuario convertFrom(UsuariosRest from) {
        Usuario usuario = new Usuario();
        usuario.setApellidos(from.getApellidos());
        usuario.setCalificacion(from.getCalificacion());
        usuario.setCoordenadaX(from.getCoordenadaX());
        usuario.setCoordenadaY(from.getCoordenadaY());
        usuario.setDireccion(from.getDireccion());
        usuario.setId(from.getId());
        usuario.setNombre(from.getNombre());
        usuario.setIdMensaje(from.getIdMensaje());
        usuario.setPassword(from.getPassword());
        return usuario;
    }

}
