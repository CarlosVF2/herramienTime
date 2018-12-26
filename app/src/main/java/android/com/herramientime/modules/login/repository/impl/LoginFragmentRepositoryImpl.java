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
    public Usuario getUserById(String id) throws InternetException {
        List<UsuariosRest> usuariosRests = restApiServiceHelper.getUsuarios();
        UsuariosRest usuariosRest = new UsuariosRest(id);
        int index = usuariosRests.indexOf(usuariosRest);
        if (index > -1) {
            return processorUsuario.convertFrom(usuariosRests.get(index));
        }
        throw new InternetException(resources.getString(R.string.error_user_not_found));
    }

    @Override
    public Usuario iniciarSesion(Login login) throws InternetException {
        checkFieldsFillIniciarSesion(login);
        Usuario usuario = getUserById(login.getUser());
        if(usuario != null && !login.getPassword().contentEquals(usuario.getPassword())){
            throw new InternetException(context.getString(R.string.prompt_need_password));
        }
        createUserInSharedPreferences(usuario);
        return usuario;
    }

    @Override
    public Usuario registrar(Login login) throws InternetException, InterruptedException {
        checkFieldsFillRegistrar(login);
        List<UsuariosRest> usuariosRests = restApiServiceHelper.getUsuarios();
        checkIfExistsUser(usuariosRests, login.getUser());
        UsuariosRest usuariosRest = new UsuariosRest();
        usuariosRest.setApellidos(login.getApellido());
        usuariosRest.setNombre(login.getNombre());
        usuariosRest.setId(login.getUser());
        usuariosRest.setPassword(login.getPassword());
        usuariosRest.setAcercaDeTi(login.getAcercaDeTi());
        usuariosRests.add(usuariosRest);
        restApiServiceHelper.postUsuario(usuariosRests);
        Thread.sleep(1000); //Los datos no son bien reflejados y actualizados si no se pone este stop en el hilo.
        iniciarSesion(login);
        return processorUsuario.convertFrom(usuariosRest);
    }

    private void checkIfExistsUser(List<UsuariosRest> usuariosRests, String user) throws InternetException {
        for (UsuariosRest usuariosRest : usuariosRests) {
            if (usuariosRest.getId().contentEquals(user)) {
                throw new InternetException(resources.getString(R.string.error_user_exists));
            }
        }
    }

    private void createUserInSharedPreferences(Usuario usuariosRest) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(context.getString(R.string.preference_file_key), usuariosRest.getId());
        editor.commit();
    }

    private void checkFieldsFillRegistrar(Login login) throws InternetException {
        checkFieldsFillIniciarSesion(login);
        if (TextUtils.isEmpty(login.getNombre())) {
            throw new InternetException(resources.getString(R.string.error_fill_name));
        } else if (TextUtils.isEmpty(login.getApellido())) {
            throw new InternetException(resources.getString(R.string.errpr_fill_surname));
        } else if (TextUtils.isEmpty(login.getAcercaDeTi())) {
            throw new InternetException(resources.getString(R.string.error_field_acerca_de_ti));
        }

    }

    private void checkFieldsFillIniciarSesion(Login login) throws InternetException {
        if (TextUtils.isEmpty(login.getUser())) {
            throw new InternetException(resources.getString(R.string.error_fill_user));
        } else if (TextUtils.isEmpty(login.getPassword())) {
            throw new InternetException(resources.getString(R.string.errpr_fill_password));
        }
    }
}
