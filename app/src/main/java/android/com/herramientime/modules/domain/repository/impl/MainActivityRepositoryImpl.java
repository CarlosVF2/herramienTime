package android.com.herramientime.modules.domain.repository.impl;

import android.com.herramientime.R;
import android.com.herramientime.domain.processor.ProcessorUsuario;
import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.domain.repository.MainActivityRepository;
import android.com.herramientime.modules.usuarios.entities.Usuario;
import android.com.rest.RestApiServiceHelper;
import android.com.rest.entities.InternetException;
import android.com.rest.entities.UsuariosRest;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

public class MainActivityRepositoryImpl implements MainActivityRepository {

    private final Context context;
    private final ProcessorUsuario processorUsuario;
    private final RestApiServiceHelper restApiServiceHelper;

    public MainActivityRepositoryImpl(Context context, ProcessorUsuario processorUsuario, RestApiServiceHelper restApiServiceHelper) {
        this.context = context;
        this.processorUsuario = processorUsuario;
        this.restApiServiceHelper = restApiServiceHelper;
    }

    @Override
    public Usuario getLoggedUser() throws InternetException, LocalException {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String userLogged = sharedPref.getString(context.getString(R.string.preference_file_key), null);
        if (userLogged != null) {
            List<UsuariosRest> usuariosRests = restApiServiceHelper.getUsuarios();
            UsuariosRest usuariosRest = new UsuariosRest(userLogged);
            int index = usuariosRests.indexOf(usuariosRest);
            if (index > -1) {
                return processorUsuario.convertFrom(usuariosRests.get(index));
            }
        }
        return null;
    }
}
