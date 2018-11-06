package android.com.herramientime.domain;

import android.app.Application;
import android.com.herramientime.injection.Constants;
import android.com.herramientime.injection.InteractorFactory;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.injection.PresenterFactory;
import android.com.herramientime.injection.RepositoryFactory;
import android.com.herramientime.injection.ViewFactory;
import android.com.herramientime.injection.impl.InteractorFactoryImpl;
import android.com.herramientime.injection.impl.NavigationManagerImpl;
import android.com.herramientime.injection.impl.PresenterFactoryImpl;
import android.com.herramientime.injection.impl.RepositoryFactoryImpl;
import android.content.Context;

import dagger.Provides;

/**
 * Created by carlo on 06/11/2018.
 */

public abstract class DependencyInjectionImpl {

    private static final String TAG = "DependencyInjectionImpl";

    private NavigationManager navigationManager;
    private PresenterFactory presenterFactory;
    private ViewFactory viewFactory;
    private Constants constants;
    private InteractorFactory interactorFactory;
    private RepositoryFactory repositoryFactory;

    public DependencyInjectionImpl(Application application) {
        /* THIS CLASS INVOKES: */
        setupPresenterFactoryDependency();
        setupInteractorFactoryDependency(null);
        setupRepositoryFactoryDependency(null, null);
        setupNavigationManagerDependency(application, viewFactory, presenterFactory, application.getApplicationContext());
    }

    // region setup

    private void setupInteractorFactoryDependency(RepositoryFactory repositoryFactory) {
        interactorFactory = new InteractorFactoryImpl();
    }

    private void setupRepositoryFactoryDependency(Context context, PresenterFactory presenterFactory) {
        repositoryFactory = new RepositoryFactoryImpl();
    }

    private void setupNavigationManagerDependency(Application application, ViewFactory viewFactory, PresenterFactory presenterFactory, Context context) {
        navigationManager = new NavigationManagerImpl(application, viewFactory, presenterFactory, context);
    }


    private void setupPresenterFactoryDependency() {
        presenterFactory = new PresenterFactoryImpl();
    }

    // endregion

    // region get

    protected NavigationManager getNavigationManager() {
        return navigationManager;
    }

    @Provides
    public ViewFactory getViewFactoryInstance() {
        return viewFactory;
    }

    @Provides
    public Constants getConstantsInstance() {
        return constants;
    }

    protected InteractorFactory getInteractorFactoryInstance() {
        return interactorFactory;
    }

    protected RepositoryFactory getRepositoryFactoryInstance() {
        return repositoryFactory;
    }

    // endregion

}