package android.com.herramientime.injection;


import android.com.herramientime.modules.domain.interactor.MainActivityInteractor;

/**
 * Created by carlos 06/11/2018.
 */

public interface InteractorFactory {

    //Main interactor
    MainActivityInteractor getMainActivityInteractor();

}

