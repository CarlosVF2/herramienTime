package android.com.herramientime.modules.experiencias.injection;

import android.com.herramientime.injection.InteractorFactory;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.modules.experiencias.interactor.ExperienciaDetalleFragmentInteractor;

/**
 * Created by carlo on 06/11/2018.
 */

public class ExperienciaDetalleFragmentModule {

    private final ExperienciaDetalleFragmentInteractor experienciaDetalleFragmentInteractor;
    private final NavigationManager navigationManager;

    public ExperienciaDetalleFragmentModule(InteractorFactory interactorFactory, NavigationManager navigationManager) {
        this.experienciaDetalleFragmentInteractor = interactorFactory.getExperienciaDetalleFragmentInteractor();
        this.navigationManager = navigationManager;
    }

    public ExperienciaDetalleFragmentInteractor getExperienciaDetalleFragmentInteractor() {
        return experienciaDetalleFragmentInteractor;
    }

    public NavigationManager getNavigationManager() {
        return navigationManager;
    }
}
