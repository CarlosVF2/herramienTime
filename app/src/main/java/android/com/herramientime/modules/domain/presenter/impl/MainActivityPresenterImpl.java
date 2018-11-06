package android.com.herramientime.modules.domain.presenter.impl;

import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.injection.impl.NavigationManagerImpl;
import android.com.herramientime.modules.domain.injection.MainActivityComponent;
import android.com.herramientime.modules.domain.interactor.MainActivityInteractor;
import android.com.herramientime.modules.domain.presenter.MainActivityPresenter;
import android.com.herramientime.modules.domain.view.MainActivity;
import android.content.Intent;

import javax.inject.Inject;

/**
 * Created by carlo on 06/11/2018.
 */

public class MainActivityPresenterImpl
        <ACTIVITY extends MainActivity>
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

    protected MainActivityPresenterImpl() {
        super();
    }

    @Inject
    public MainActivityPresenterImpl(MainActivityComponent activityComponent) {
        activityInteractor = activityComponent.getMainActivityModule().getActivityInteractor();
        navigationManager = activityComponent.getMainActivityModule().getNavigationManager();
        navigationManager.setNavigationListener(this);
        //try {
        //    if (!getNavigationManager().isFragmentAttached()) {
        //        //getNavigationManager().navigateToAnadirElementos("", AccionType.MONTAJE_DE_ELEMENTOS,"","","");
        //        getNavigationManager().navigateToOrdersGroup();
        //    }
        //} catch (IOException ignored) {
        //    // never can happen
        //}
    }

    //region NavigationListener
    @Override
    public void onBackstackChanged() {
        //El contenido de la pila ha cambiado, por tanto recargamos visualmente-
    }
    //endregion NavigationListener
}
