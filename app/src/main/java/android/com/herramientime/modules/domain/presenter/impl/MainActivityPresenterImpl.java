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

import com.seidor.core.di.annotations.Inject;

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
            navigationManager.setNavigationListener(this);
        }
        try {
            if (!navigationManager.isFragmentAttached()) {
                navigationManager.navigateToHerramientas();
            }
        } catch (LocalException ignored) {
            // never can happen
        }
        getMvpActivity().setNombreUsuarioText("Carlos Vega");
        getMvpActivity().setIDUsuarioText("carvegfer");
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
                getMvpActivity().showMessageExitConfirm();
            } else {
                navigationManager.navigateBack();
            }
        } catch (LocalException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exitConfirm() {
        try {
            navigationManager.navigateBack();
        } catch (LocalException e) {
            e.printStackTrace();
        }
    }

    //region Click Navigation Item
    @Override
    public void navigationExperienciasClicked() {
        VIEW view = getMvpActivity();
        if (view != null) {
            view.closeDrawer();
        }
        try {
            navigationManager.navigateToExperiencias();
        } catch (LocalException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void navigationHelpClicked() {
        VIEW view = getMvpActivity();
        if (view != null) {
            view.closeDrawer();
        }

    }

    @Override
    public void navigationSettingsClicked() {
        VIEW view = getMvpActivity();
        if (view != null) {
            view.closeDrawer();
        }

    }

    @Override
    public void navigationHerramientasClicked() {
        VIEW view = getMvpActivity();
        if (view != null) {
            view.closeDrawer();
        }
        try {
            navigationManager.navigateToHerramientas();
        } catch (LocalException e) {
            e.printStackTrace();
        }
    }
    //endregion Click Navigation Item

    //region NavigationListener
    @Override
    public void onBackstackChanged() {
        //El contenido de la pila ha cambiado, por tanto recargamos visualmente

        VIEW activity = getMvpActivity();
        if (activity != null) {
            activity.refreshMenu();
        }
    }
    //endregion NavigationListener
}
