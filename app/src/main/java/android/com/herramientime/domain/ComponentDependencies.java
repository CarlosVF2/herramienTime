package android.com.herramientime.domain;

import android.app.Application;
import android.com.herramientime.modules.domain.injection.MainActivityComponent;
import android.com.herramientime.modules.domain.injection.MainActivityModule;

/**
 * Created by carlo 06/11/2018
 */

public class ComponentDependencies extends ModuleDependencies {

    private MainActivityComponent mainActivityComponent;

    public ComponentDependencies(Application application) {
        super(application);
        setupMainActivityComponent(getMainActivityModule());
    }

    //region Setup

    private void setupMainActivityComponent(MainActivityModule mainActivityModule) {

    }

    //endregion Setup
}
