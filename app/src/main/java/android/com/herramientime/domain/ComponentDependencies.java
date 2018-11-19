package android.com.herramientime.domain;

import android.app.Application;
import android.com.herramientime.modules.domain.injection.MainActivityComponent;
import android.com.herramientime.modules.domain.injection.MainActivityModule;
import android.com.herramientime.modules.herramientas.injection.HerramientasFragmentComponent;
import android.com.herramientime.modules.herramientas.injection.HerramientasFragmentModule;

import dagger.Provides;

/**
 * Created by carlo 06/11/2018
 */

public class ComponentDependencies extends ModuleDependencies {

    private MainActivityComponent mainActivityComponent;
    private HerramientasFragmentComponent herramientasFragmentComponent;

    public ComponentDependencies(Application application) {
        super(application);
        setupMainActivityComponent(getMainActivityModule());
        setupHerramientasFragmentComponent(getHerramientasFragmentModule());
    }


    //region Setup

    private void setupMainActivityComponent(MainActivityModule mainActivityModule) {
        mainActivityComponent = new MainActivityComponent(mainActivityModule);
    }

    private void setupHerramientasFragmentComponent(HerramientasFragmentModule herramientasFragmentModule) {
        herramientasFragmentComponent = new HerramientasFragmentComponent(herramientasFragmentModule);
    }

    //endregion Setup

    //region Get

    @Provides
    public MainActivityComponent getMainActivityComponent() {
        return mainActivityComponent;
    }

    @Provides
    public HerramientasFragmentComponent getHerramientasFragmentComponent() {
        return herramientasFragmentComponent;
    }

    //endregion Get
}
