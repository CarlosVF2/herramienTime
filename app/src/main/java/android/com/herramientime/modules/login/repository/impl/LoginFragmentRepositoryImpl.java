package android.com.herramientime.modules.login.repository.impl;

import android.com.herramientime.R;
import android.com.herramientime.domain.processor.ProcessorUsuario;
import android.com.herramientime.modules.login.entities.Login;
import android.com.herramientime.modules.login.repository.LoginFragmentRepository;
import android.com.herramientime.modules.usuarios.entities.Usuario;
import android.com.rest.RestApiServiceHelper;
import android.com.rest.entities.InternetException;
import android.com.rest.entities.UsuariosRest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.text.TextUtils;

import java.util.List;

public class LoginFragmentRepositoryImpl implements LoginFragmentRepository {

    private final RestApiServiceHelper restApiServiceHelper;
    private final ProcessorUsuario processorUsuario;
    private final Resources resources;
    private final Context context;

    public LoginFragmentRepositoryImpl(RestApiServiceHelper restApiServiceHelper, ProcessorUsuario processorUsuario, Resources resources, Context context) {
        this.restApiServiceHelper = restApiServiceHelper;
        this.processorUsuario = processorUsuario;
        this.resources = resources;
        this.context = context;
    }

    @Override
    public Usuario iniciarSesion(Login login) throws InternetException {
        checkFieldsFill(login);
        List<UsuariosRest> usuariosRests = restApiServiceHelper.getUsuarios();
        UsuariosRest usuariosRest = new UsuariosRest(login.getUser());
        int index = usuariosRests.indexOf(usuariosRest);
        if (index > -1) {
            createUserInSharedPreferences(usuariosRests.get(index));
            return processorUsuario.convertFrom(usuariosRests.get(index));
        }
        throw new InternetException(resources.getString(R.string.error_user_not_found));
    }

    private void createUserInSharedPreferences(UsuariosRest usuariosRest) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(context.getString(R.string.preference_file_key), usuariosRest.getId());
        editor.commit();
    }

    private void checkFieldsFill(Login login) throws InternetException {
        if (TextUtils.isEmpty(login.getUser())) {
            throw new InternetException(resources.getString(R.string.error_fill_user));
        } else if (TextUtils.isEmpty(login.getPassword())) {
            throw new InternetException(resources.getString(R.string.errpr_fill_password));
        }
    }
}
