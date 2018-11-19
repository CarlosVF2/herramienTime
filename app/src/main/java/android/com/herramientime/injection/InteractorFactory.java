package android.com.herramientime.injection;


import android.com.herramientime.modules.domain.interactor.MainActivityInteractor;
import android.com.herramientime.modules.herramientas.interactor.HerramientasFragmentInteractor;

/**
 * Created by carlos 06/11/2018.
 */

public interface InteractorFactory {

    //Main interactor
    MainActivityInteractor getMainActivityInteractor();

    //Herramientas
    HerramientasFragmentInteractor getHerramientasFragmentInteractor();
}

