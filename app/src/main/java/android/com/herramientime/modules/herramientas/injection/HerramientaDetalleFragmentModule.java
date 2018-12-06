package android.com.herramientime.modules.herramientas.injection;

import android.com.herramientime.injection.InteractorFactory;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.modules.herramientas.interactor.HerramientaDetalleFragmentInteractor;
import android.com.herramientime.modules.herramientas.interactor.HerramientasFragmentInteractor;

/**
 * Created by carlo on 06/11/2018.
 */

public class HerramientaDetalleFragmentModule {

    private final HerramientaDetalleFragmentInteractor herramientaDetalleFragmentInteractor;

    public HerramientaDetalleFragmentModule(InteractorFactory interactorFactory) {
        this.herramientaDetalleFragmentInteractor = interactorFactory.getHerramientaDetalleFragmentInteractor();
    }
}
