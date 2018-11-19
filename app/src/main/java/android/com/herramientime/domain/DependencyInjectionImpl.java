package android.com.herramientime.domain;

import android.app.Application;
import android.com.herramientime.injection.Constants;
import android.com.herramientime.injection.InteractorFactory;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.injection.PresenterFactory;
import android.com.herramientime.injection.RepositoryFactory;
import android.com.herramientime.injection.ViewFactory;
import android.com.herramientime.injection.impl.ConstantsImpl;
import android.com.herramientime.injection.impl.InteractorFactoryImpl;
import android.com.herramientime.injection.impl.NavigationManagerImpl;
import android.com.herramientime.injection.impl.PresenterFactoryImpl;
import android.com.herramientime.injection.impl.RepositoryFactoryImpl;
import android.com.herramientime.injection.impl.ViewFactoryImpl;
import android.content.Context;

import dagger.Provides;

/**
 * Created by carlo on 06/11/2018.
 */

public abstract class DependencyInjectionImpl {

    private NavigationManager navigationManager;
    private PresenterFactory presenterFactory;
    private ViewFactory viewFactory;
    private Constants constants;
    private InteractorFactory interactorFactory;
    private RepositoryFactory repositoryFactory;

    public DependencyInjectionImpl(Application application) {
        /* THIS CLASS INVOKES: */
        setupConstantsDependency();
        setupPresenterFactoryDependency();
        setupRepositoryFactoryDependency(application.getApplicationContext(), presenterFactory);
        setupInteractorFactoryDependency(getRepositoryFactoryInstance());
        setupViewFactoryDependency(application.getApplicationContext());
        setupNavigationManagerDependency(application, getViewFactoryInstance(), getPresenterFactory(), application.getApplicationContext());
    }


    // region setup

    private void setupConstantsDependency() {
        constants = new ConstantsImpl();
    }

    private void setupViewFactoryDependency(Context context) {
        viewFactory = new ViewFactoryImpl(context);
    }

    private void setupInteractorFactoryDependency(RepositoryFactory repositoryFactory) {
        interactorFactory = new InteractorFactoryImpl(repositoryFactory);
    }

    private void setupRepositoryFactoryDependency(Context context, PresenterFactory presenterFactory) {
        repositoryFactory = new RepositoryFactoryImpl(context, presenterFactory);
    }

    private void setupNavigationManagerDependency(Application application, ViewFactory viewFactory, PresenterFactory presenterFactory, Context context) {
        navigationManager = new NavigationManagerImpl(application, viewFactory, presenterFactory, context);
    }

    private void setupPresenterFactoryDependency() {
        presenterFactory = new PresenterFactoryImpl();
    }

    // endregion

    // region get

    @Provides
    public NavigationManager getNavigationManager() {
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

    private PresenterFactory getPresenterFactory() {
        return presenterFactory;
    }

    protected InteractorFactory getInteractorFactoryInstance() {
        return interactorFactory;
    }

    private RepositoryFactory getRepositoryFactoryInstance() {
        return repositoryFactory;
    }

    // endregion

}