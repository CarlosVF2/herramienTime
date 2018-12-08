package android.com.herramientime.modules.herramientas.injection;

import android.com.herramientime.injection.InteractorFactory;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.modules.herramientas.interactor.HerramientaDetalleFragmentInteractor;

/**
 * Created by carlo on 06/11/2018.
 */

public class HerramientaDetalleFragmentModule {

    private final HerramientaDetalleFragmentInteractor herramientaDetalleFragmentInteractor;
    private final NavigationManager navigationManager;

    public HerramientaDetalleFragmentModule(InteractorFactory interactorFactory, NavigationManager navigationManager) {
        this.herramientaDetalleFragmentInteractor = interactorFactory.getHerramientaDetalleFragmentInteractor();
        this.navigationManager = navigationManager;
    }

    public HerramientaDetalleFragmentInteractor getHerramientaDetalleFragmentInteractor() {
        return herramientaDetalleFragmentInteractor;
    }

    public NavigationManager getNavigationManager() {
        return navigationManager;
    }
}
