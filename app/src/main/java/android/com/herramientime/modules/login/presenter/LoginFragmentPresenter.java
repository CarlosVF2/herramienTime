package android.com.herramientime.modules.login.presenter;

import android.com.herramientime.core.presenter.MvpFragmentPresenter;

/**
 * Created by carlo on 06/11/2018.
 */

public interface LoginFragmentPresenter extends MvpFragmentPresenter {

    void onClickIniciarSesion();

    void onClickRegistrarse();


    //region set fields
    void setNombre(String nombre);

    void setApellidos(String apellidos);

    void setUsuario(String usuario);

    void setPassword(String password);

    //endregion set fields
}
