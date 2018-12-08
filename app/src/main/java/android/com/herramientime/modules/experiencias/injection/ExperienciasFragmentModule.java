package android.com.herramientime.modules.experiencias.injection;

import android.com.herramientime.injection.InteractorFactory;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.modules.experiencias.interactor.ExperienciaFragmentInteractor;
import android.content.res.Resources;

/**
 * Created by carlo on 06/11/2018.
 */

public class ExperienciasFragmentModule {

    private final ExperienciaFragmentInteractor experienciaFragmentInteractor;
    private final NavigationManager navigationManager;
    private final Resources resources;

    public ExperienciasFragmentModule(NavigationManager navigationManager, InteractorFactory interactorFactory, Resources resources) {
        this.navigationManager = navigationManager;
        this.experienciaFragmentInteractor = interactorFactory.getExperienciasFragmentInteractor();
        this.resources = resources;
    }

    public ExperienciaFragmentInteractor getExperienciaFragmentInteractor() {
        return experienciaFragmentInteractor;
    }

    public NavigationManager getNavigationManager() {
        return navigationManager;
    }

    public Resources getResources() {
        return resources;

    }
}
