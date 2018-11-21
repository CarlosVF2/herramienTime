package android.com.herramientime.modules.herramientas.presenter.impl;

import android.com.herramientime.app.HerramienTimeApp;
import android.com.herramientime.core.presenter.impl.MvpFragmentPresenterImpl;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.modules.herramientas.interactor.HerramientasFragmentInteractor;
import android.com.herramientime.modules.herramientas.presenter.HerramientasFragmentPresenter;
import android.com.herramientime.modules.herramientas.view.HerramientasFragment;
import android.os.Bundle;

import javax.inject.Inject;

/**
 * Created by carlo on 06/11/2018.
 */

public class HerramientasFragmentPresenterImpl<VIEW extends HerramientasFragment> extends MvpFragmentPresenterImpl<VIEW>
        implements HerramientasFragmentPresenter {

    @Inject
    private NavigationManager navigationManager;
    @Inject
    private HerramientasFragmentInteractor herramientasFragmentInteractor;

    public static void newHerramientasFragmentPresenterInstance(Bundle bundle) {
        HerramientasFragmentPresenterImpl presenter = new HerramientasFragmentPresenterImpl();
        presenter.setArguments(bundle);
    }

    @Override
    public void onViewBinded() {
        if (herramientasFragmentInteractor == null) {
            herramientasFragmentInteractor = HerramienTimeApp.getComponentDependencies().getHerramientasFragmentComponent().getHerramientasFragmentModule().getActivityInteractor();
        }
        herramientasFragmentInteractor.getHerramientas();
    }

    @Override
    public void onViewUnbinded() {

    }

    @Override
    public void onDestroy() {

    }
}
