package android.com.herramientime.modules.experiencias.injection;

import android.com.herramientime.injection.InteractorFactory;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.modules.experiencias.interactor.AlquilerExperienciaFragmentInteractor;
import android.content.res.Resources;

/**
 * Created by carlo on 06/11/2018.
 */

public class AlquilerExperienciaFragmentModule {

    private final AlquilerExperienciaFragmentInteractor alquilerExperienciaFragmentInteractor;
    private final NavigationManager navigationManager;
    private final Resources resources;

    public AlquilerExperienciaFragmentModule(NavigationManager navigationManager, InteractorFactory interactorFactory, Resources resources) {
        this.navigationManager = navigationManager;
        this.alquilerExperienciaFragmentInteractor = interactorFactory.getAlquilerExperienciaFragmentInteractor();
        this.resources = resources;
    }

    public AlquilerExperienciaFragmentInteractor getAlquilerExperienciaFragmentInteractor() {
        return alquilerExperienciaFragmentInteractor;
    }

    public NavigationManager getNavigationManager() {
        return navigationManager;
    }

    public Resources getResources() {
        return resources;
    }
}
