package android.com.herramientime.modules.domain.presenter.impl;

import android.com.herramientime.app.HerramienTimeApp;
import android.com.herramientime.core.presenter.impl.MvpActivityPresenterImpl;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.injection.impl.NavigationManagerImpl;
import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.domain.injection.MainActivityComponent;
import android.com.herramientime.modules.domain.interactor.MainActivityInteractor;
import android.com.herramientime.modules.domain.presenter.MainActivityPresenter;
import android.com.herramientime.modules.domain.view.MainActivity;
import android.content.Intent;

import com.seidor.core.di.InjectorClass;
import com.seidor.core.di.annotations.Inject;

import java.io.IOException;

/**
 * Created by carlo on 06/11/2018.
 */

public class MainActivityPresenterImpl<VIEW extends MainActivity> extends MvpActivityPresenterImpl<VIEW>
        implements MainActivityPresenter, NavigationManagerImpl.NavigationListener {

    private NavigationManager navigationManager;
    private MainActivityInteractor activityInteractor;


    public static void newMainActivityPresenterInstance(Intent intent) {
        MainActivityPresenterImpl presenter = new MainActivityPresenterImpl();
        presenter.setupDependencies(intent);
    }

    protected void setupDependencies(Intent intent) {
        //super.setupDependencies(intentWrapper);
        //configureIntent(intentWrapper);
    }

    public MainActivityPresenterImpl() {
        super();
    }

    @Inject
    public MainActivityPresenterImpl(MainActivityComponent activityComponent) {
        activityInteractor = activityComponent.getMainActivityModule().getActivityInteractor();
        navigationManager = activityComponent.getMainActivityModule().getNavigationManager();
        navigationManager.setNavigationListener(this);
        try {
            if (!navigationManager.isFragmentAttached()) {
                navigationManager.navigateToHerramientas();
            }
        } catch (LocalException ignored) {
            // never can happen
        }
    }

    //region Lyfecicle core

    @Override
    public void onViewBinded() {
        if(navigationManager == null){
            navigationManager = HerramienTimeApp.getComponentDependencies().getNavigationManager();
        }
        try {
            if (!navigationManager.isFragmentAttached()) {
                navigationManager.navigateToHerramientas();
            }
        } catch (LocalException ignored) {
            // never can happen
        }
    }

    @Override
    public void onViewUnbinded() {

    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void onDataLoaded() {
        if (isLoadingFinish()) {

        }
    }

    @Override
    public boolean isLoadingFinish() {
        return true;
    }

    //endregion Lyfecicle core

    @Override
    public void onBackPressed() {

        try {
            if (navigationManager.isRootFragment()) {
                //getMvpActivity().showMessageExitConfirm();
            } else {
                navigationManager.navigateBack();
            }
        } catch (LocalException e) {
            e.printStackTrace();
        }
    }

    //region NavigationListener
    @Override
    public void onBackstackChanged() {
        //El contenido de la pila ha cambiado, por tanto recargamos visualmente-
    }
    //endregion NavigationListener
}
