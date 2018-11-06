package android.com.herramientime.modules.domain.injection;

import android.com.herramientime.injection.Constants;
import android.com.herramientime.injection.InteractorFactory;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.modules.domain.interactor.MainActivityInteractor;

/**
 * Created by carlo on 06/11/2018.
 */

public class MainActivityModule {

    private Constants constants;
    private MainActivityInteractor activityInteractor;
    private NavigationManager navigationManager;

    public MainActivityModule(Constants constants, InteractorFactory interactorFactory, NavigationManager navigationManager) {
        this.constants = constants;
        this.activityInteractor = interactorFactory.getMainActivityInteractor();
        this.navigationManager = navigationManager;
    }

    public Constants getConstants() {
        return constants;
    }

    public MainActivityInteractor getActivityInteractor() {
        return activityInteractor;
    }

    public NavigationManager getNavigationManager() {
        return navigationManager;
    }
}
