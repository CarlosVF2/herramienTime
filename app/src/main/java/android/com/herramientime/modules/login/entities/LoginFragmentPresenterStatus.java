package android.com.herramientime.modules.login.entities;

import android.com.herramientime.core.entities.BasePresenterStatus;
import android.os.Bundle;

public class LoginFragmentPresenterStatus extends BasePresenterStatus {


    private Login login = new Login();
    private boolean registrar;

    @Override
    public void saveInstance(Bundle saveInstance) {

    }

    @Override
    public void restoreInstance(Bundle restoreInstance) {

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
