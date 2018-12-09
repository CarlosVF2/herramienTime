package android.com.herramientime.modules.login.entities;

import android.com.herramientime.core.entities.BasePresenterStatus;

import com.seidor.core.utils.wrapper.BundleWrapper;

public class LoginFragmentPresenterStatus extends BasePresenterStatus {


    private Login login = new Login();
    private boolean registrar;

    @Override
    public void saveInstance(BundleWrapper saveInstance) {

    }

    @Override
    public void restoreInstance(BundleWrapper restoreInstance) {

    }

    //region GET

    public Login getLogin() {
        return login;
    }

    public boolean isRegistrar() {
        return registrar;
    }

    //endregion GET

    //region SET

    public void setLogin(Login login) {
        this.login = login;
    }

    public void setRegistrar(boolean registrar) {
        this.registrar = registrar;
    }

    //endregion SET
}
