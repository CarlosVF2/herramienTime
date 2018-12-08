package android.com.herramientime.modules.login.injection;

import android.com.herramientime.injection.InteractorFactory;
import android.com.herramientime.modules.login.interactor.LoginFragmentInteractor;
import android.content.res.Resources;

/**
 * Created by carlo on 06/11/2018.
 */

public class LoginFragmentModule {

    private final LoginFragmentInteractor loginFragmentInteractor;
    private final Resources resources;

    public LoginFragmentModule(InteractorFactory interactorFactory, Resources resources) {
        this.resources = resources;
        this.loginFragmentInteractor = interactorFactory.getLoginFragmentInteractor();
    }

    public LoginFragmentInteractor getLoginFragmentInteractor() {
        return loginFragmentInteractor;
    }

    public Resources getResources() {
        return resources;
    }
}
