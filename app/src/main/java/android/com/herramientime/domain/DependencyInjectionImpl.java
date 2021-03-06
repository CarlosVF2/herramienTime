package android.com.herramientime.domain;

import android.app.Application;
import android.com.herramientime.R;
import android.com.herramientime.domain.processor.Processors;
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
import android.com.rest.RestApiServiceHelper;
import android.com.rest.RestApiServiceHelperImpl;
import android.content.Context;
import android.content.res.Resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.seidor.core.di.annotations.Provides;
import com.seidor.core.utils.domain.DependencyInjection;
import com.seidor.core.utils.injection.SchedulerFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by carlo on 06/11/2018.
 */

public abstract class DependencyInjectionImpl extends DependencyInjection {

    private NavigationManager navigationManager;
    private PresenterFactory presenterFactory;
    private ViewFactory viewFactory;
    private Constants constants;
    private InteractorFactory interactorFactory;
    private RepositoryFactory repositoryFactory;
    private RestApiServiceHelper restApiServiceHelper;
    private Processors processors;

    public DependencyInjectionImpl(Application application) {
        super(application);
        setupConstantsDependency();
        setupPresenterFactoryDependency();
        setupProcessors();
        setupRestApiServiceHelper(application.getApplicationContext());
        setupRepositoryFactoryDependency(application.getApplicationContext(), getRestApiServiceHelper(), getProcessors(), getResources());
        setupInteractorFactoryDependency(getRepositoryFactoryInstance(), getSchedulerFactoryInstance());
        setupViewFactoryDependency(application.getApplicationContext());
        setupNavigationManagerDependency(application, getViewFactoryInstance(), getPresenterFactory(), application.getApplicationContext());
        setupGlideModule(getApplicationContext());
    }


    // region setup

    private void setupGlideModule(Context context) {

    }

    private void setupProcessors() {
        processors = new Processors();
    }

    private void setupRestApiServiceHelper(Context context) {
        restApiServiceHelper = new RestApiServiceHelperImpl(getRestApiBuilder(context), context);
    }

    private void setupConstantsDependency() {
        constants = new ConstantsImpl();
    }

    private void setupViewFactoryDependency(Context context) {
        viewFactory = new ViewFactoryImpl(context);
    }

    private void setupInteractorFactoryDependency(RepositoryFactory repositoryFactory, SchedulerFactory schedulerFactory) {
        interactorFactory = new InteractorFactoryImpl(repositoryFactory, schedulerFactory);
    }

    private void setupRepositoryFactoryDependency(Context context, RestApiServiceHelper restApiServiceHelper, Processors processors, Resources resources) {
        repositoryFactory = new RepositoryFactoryImpl(context, restApiServiceHelper, processors, resources);
    }

    private void setupNavigationManagerDependency(Application application, ViewFactory viewFactory, PresenterFactory presenterFactory, Context context) {
        navigationManager = new NavigationManagerImpl(application, viewFactory, presenterFactory, context);
    }

    private void setupPresenterFactoryDependency() {
        presenterFactory = new PresenterFactoryImpl();
    }

    // endregion

    // region get

    private Processors getProcessors() {
        return processors;
    }

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

    protected RestApiServiceHelper getRestApiServiceHelper() {
        return restApiServiceHelper;
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

    private Retrofit.Builder getRestApiBuilder(Context context) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(context.getResources().getString(R.string.base_url));
    }

    // endregion

}