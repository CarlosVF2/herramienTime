package android.com.herramientime.modules.domain.repository.impl;

import android.com.herramientime.R;
import android.com.herramientime.domain.processor.ProcessorUsuario;
import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.domain.entities.UsuarioException;
import android.com.herramientime.modules.domain.repository.MainActivityRepository;
import android.com.herramientime.modules.usuarios.entities.Usuario;
import android.com.rest.RestApiServiceHelper;
import android.com.rest.entities.InternetException;
import android.com.rest.entities.UsuariosRest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.text.TextUtils;

import java.util.List;

public class MainActivityRepositoryImpl implements MainActivityRepository {

    private final Context context;
    private final ProcessorUsuario processorUsuario;
    private final RestApiServiceHelper restApiServiceHelper;
    private final Resources resources;

    public MainActivityRepositoryImpl(Context context, ProcessorUsuario processorUsuario, RestApiServiceHelper restApiServiceHelper, Resources resources) {
        this.context = context;
        this.processorUsuario = processorUsuario;
        this.restApiServiceHelper = restApiServiceHelper;
        this.resources = resources;
    }

    @Override
    public Usuario getLoggedUser() throws InternetException, LocalException, UsuarioException {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String userLogged = sharedPref.getString(context.getString(R.string.preference_file_key), null);
        if (!TextUtils.isEmpty(userLogged)) {
            List<UsuariosRest> usuariosRests = restApiServiceHelper.getUsuarios();
            UsuariosRest usuariosRest = new UsuariosRest(userLogged);
            int index = usuariosRests.indexOf(usuariosRest);
            if (index > -1) {
                return processorUsuario.convertFrom(usuariosRests.get(index));
            }
            throw new UsuarioException(resources.getString(R.string.prompt_first_register));
        } else {
            return null;
        }
    }
}
