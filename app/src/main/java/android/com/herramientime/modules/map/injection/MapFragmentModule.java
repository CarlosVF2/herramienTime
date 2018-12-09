package android.com.herramientime.modules.map.injection;

import android.com.herramientime.injection.InteractorFactory;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.modules.map.interactor.MapFragmentInteractor;
import android.content.res.Resources;

public class MapFragmentModule {
    private final InteractorFactory interactorFactory;
    private final NavigationManager navigationManager;
    private final Resources resources;


    public MapFragmentModule(InteractorFactory interactorFactory, NavigationManager navigationManager, Resources resources) {
        this.interactorFactory = interactorFactory;
        this.resources = resources;
        this.navigationManager = navigationManager;
    }

    public MapFragmentInteractor getMapFragmentInteractor() {
        return this.interactorFactory.getMapFragmentInteractor();
    }

    public NavigationManager getNavigationManager() {
        return navigationManager;
    }


    public Resources getResources() {
        return resources;
    }
}
