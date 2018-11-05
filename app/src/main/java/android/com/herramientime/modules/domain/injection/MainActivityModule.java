package android.com.herramientime.modules.domain.injection;

import android.com.herramientime.injection.Constants;
import android.com.herramientime.modules.domain.interactor.MainActivityInteractor;

/**
 * Created by carlo on 06/11/2018.
 */

public class MainActivityModule {

    private Constants constants;
    private MainActivityInteractor activityInteractor;

    public MainActivityModule(Constants constants, MainActivityInteractor activityInteractor) {
        this.constants = constants;
        this.activityInteractor = activityInteractor;
    }

    public Constants getConstants() {
        return constants;
    }

    public MainActivityInteractor getActivityInteractor() {
        return activityInteractor;
    }
}
