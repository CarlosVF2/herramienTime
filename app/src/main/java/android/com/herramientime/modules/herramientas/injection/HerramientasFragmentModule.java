package android.com.herramientime.modules.herramientas.injection;

import android.com.herramientime.injection.InteractorFactory;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.modules.herramientas.interactor.HerramientasFragmentInteractor;

/**
 * Created by carlo on 06/11/2018.
 */

public class HerramientasFragmentModule {

    private final HerramientasFragmentInteractor herramientasFragmentInteractor;
    private final NavigationManager navigationManager;

    public HerramientasFragmentModule( NavigationManager navigationManager, InteractorFactory interactorFactory) {
        this.navigationManager = navigationManager;
        this.herramientasFragmentInteractor = null;
    }

    public HerramientasFragmentInteractor getHerramientasFragmentInteractor() {
        return herramientasFragmentInteractor;
    }

    public NavigationManager getNavigationManager() {
        return navigationManager;
    }
}
