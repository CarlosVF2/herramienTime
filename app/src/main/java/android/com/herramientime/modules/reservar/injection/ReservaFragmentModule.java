package android.com.herramientime.modules.reservar.injection;

import android.com.herramientime.injection.InteractorFactory;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.modules.reservar.interactor.ReservaFragmentInteractor;
import android.content.res.Resources;

/**
 * Created by carlo on 06/11/2018.
 */

public class ReservaFragmentModule {

    private final ReservaFragmentInteractor reservaFragmentInteractor;
    private final NavigationManager navigationManager;
    private final Resources resources;

    public ReservaFragmentModule(NavigationManager navigationManager, InteractorFactory interactorFactory, Resources resources) {
        this.navigationManager = navigationManager;
        this.reservaFragmentInteractor = interactorFactory.getReservaFragmentInteractor();
        this.resources = resources;
    }

    public ReservaFragmentInteractor getReservaFragmentInteractor() {
        return reservaFragmentInteractor;
    }

    public NavigationManager getNavigationManager() {
        return navigationManager;
    }

    public Resources getResources() {
        return resources;
    }
}
