package android.com.herramientime.modules.herramientas.injection;

import android.com.herramientime.injection.InteractorFactory;
import android.com.herramientime.modules.herramientas.interactor.HerramientaDetalleFragmentInteractor;

/**
 * Created by carlo on 06/11/2018.
 */

public class HerramientaDetalleFragmentModule {

    private final HerramientaDetalleFragmentInteractor herramientaDetalleFragmentInteractor;

    public HerramientaDetalleFragmentModule(InteractorFactory interactorFactory) {
        this.herramientaDetalleFragmentInteractor = interactorFactory.getHerramientaDetalleFragmentInteractor();
    }

    public HerramientaDetalleFragmentInteractor getHerramientaDetalleFragmentInteractor() {
        return herramientaDetalleFragmentInteractor;
    }
}
