package android.com.herramientime.modules.herramientas.injection;

import android.com.herramientime.injection.Constants;
import android.com.herramientime.injection.InteractorFactory;
import android.com.herramientime.modules.herramientas.interactor.HerramientasFragmentInteractor;

/**
 * Created by carlo on 06/11/2018.
 */

public class HerramientasFragmentModule {

    private Constants constants;
    private HerramientasFragmentInteractor activityInteractor;

    public HerramientasFragmentModule(Constants constants, InteractorFactory interactorFactory) {
        this.constants = constants;
        this.activityInteractor = interactorFactory.getHerramientasFragmentInteractor();
    }

    public Constants getConstants() {
        return constants;
    }

    public HerramientasFragmentInteractor getActivityInteractor() {
        return activityInteractor;
    }
}
