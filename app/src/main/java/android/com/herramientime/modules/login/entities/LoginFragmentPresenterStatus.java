package android.com.herramientime.modules.login.entities;

import android.com.herramientime.core.entities.BasePresenterStatus;

import com.seidor.core.utils.wrapper.BundleWrapper;

public class LoginFragmentPresenterStatus extends BasePresenterStatus {

    private Exception error;
    private Login login = new Login();

    @Override
    public void saveInstance(BundleWrapper saveInstance) {

    }

    @Override
    public void restoreInstance(BundleWrapper restoreInstance) {

    }

    //region GET

    public Exception getError() {
        return error;
    }

    public Login getLogin() {
        return login;
    }

    //endregion GET

    //region SET

    public void setError(Exception error) {
        this.error = error;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    //endregion SET
}
