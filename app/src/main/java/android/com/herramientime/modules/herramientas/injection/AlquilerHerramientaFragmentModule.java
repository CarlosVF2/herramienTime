package android.com.herramientime.modules.herramientas.injection;

import android.com.herramientime.injection.InteractorFactory;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.modules.herramientas.interactor.AlquilerHerramientaFragmentInteractor;
import android.content.res.Resources;

/**
 * Created by carlo on 06/11/2018.
 */

public class AlquilerHerramientaFragmentModule {

    private final AlquilerHerramientaFragmentInteractor alquilerHerramientaFragmentInteractor;
    private final NavigationManager navigationManager;
    private final Resources resources;

    public AlquilerHerramientaFragmentModule(NavigationManager navigationManager, InteractorFactory interactorFactory, Resources resources) {
        this.navigationManager = navigationManager;
        this.alquilerHerramientaFragmentInteractor = interactorFactory.getAlquilerHerramientaFragmentInteractor();
        this.resources = resources;
    }

    public AlquilerHerramientaFragmentInteractor getAlquilerHerramientaFragmentInteractor() {
        return alquilerHerramientaFragmentInteractor;
    }

    public NavigationManager getNavigationManager() {
        return navigationManager;
    }

    public Resources getResources() {
        return resources;
    }
}
