package android.com.herramientime.modules.usuarios.repository.impl;

import android.com.herramientime.R;
import android.com.herramientime.domain.processor.ProcessorUsuario;
import android.com.herramientime.modules.usuarios.entities.Usuario;
import android.com.herramientime.modules.usuarios.repository.UsuarioDetalleFragmentRepository;
import android.com.rest.RestApiServiceHelper;
import android.com.rest.entities.InternetException;
import android.com.rest.entities.UsuariosRest;
import android.content.res.Resources;

import java.util.List;

public class UsuarioDetalleFragmentRepositoryImpl implements UsuarioDetalleFragmentRepository {

    private final RestApiServiceHelper restApiServiceHelper;
    private final ProcessorUsuario processorUsuario;
    private final Resources resources;

    public UsuarioDetalleFragmentRepositoryImpl(RestApiServiceHelper restApiServiceHelper, ProcessorUsuario processorUsuario, Resources resources) {
        this.restApiServiceHelper = restApiServiceHelper;
        this.processorUsuario = processorUsuario;
        this.resources = resources;
    }

    @Override
    public Usuario getUserById(String id) throws InternetException {
        List<UsuariosRest> usuariosRests = restApiServiceHelper.getUsuarios();
        UsuariosRest usuariosRest = new UsuariosRest(id);
        int index = usuariosRests.indexOf(usuariosRest);
        if (index > -1) {
            return processorUsuario.convertFrom(usuariosRests.get(index));
        }
        throw new InternetException(resources.getString(R.string.error_user_not_found));
    }

}
