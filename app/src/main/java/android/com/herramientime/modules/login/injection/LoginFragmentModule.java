package android.com.herramientime.modules.login.injection;

import android.com.herramientime.injection.InteractorFactory;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.modules.login.interactor.LoginFragmentInteractor;
import android.content.res.Resources;

/**
 * Created by carlo on 06/11/2018.
 */

public class LoginFragmentModule {

    private final LoginFragmentInteractor loginFragmentInteractor;
    private final Resources resources;
    private final NavigationManager navigationManager;

    public LoginFragmentModule(InteractorFactory interactorFactory, Resources resources, NavigationManager navigationManager) {
        this.resources = resources;
        this.loginFragmentInteractor = interactorFactory.getLoginFragmentInteractor();
        this.navigationManager = navigationManager;
    }

    public LoginFragmentInteractor getLoginFragmentInteractor() {
        return loginFragmentInteractor;
    }

    public Resources getResources() {
        return resources;
    }

    public NavigationManager geNavigationManager() {
        return navigationManager;
    }
}
