package android.com.herramientime.injection.impl;


import android.com.herramientime.injection.InteractorFactory;
import android.com.herramientime.injection.RepositoryFactory;
import android.com.herramientime.modules.domain.interactor.MainActivityInteractor;
import android.com.herramientime.modules.domain.interactor.impl.MainActivityInteractorImpl;
import android.com.herramientime.modules.herramientas.interactor.HerramientasFragmentInteractor;
import android.com.herramientime.modules.herramientas.interactor.impl.HerramientasFragmentInteractorImpl;

/**
 * Created by carlos 06/11/2018.
 */
public class InteractorFactoryImpl implements InteractorFactory {

    private RepositoryFactory repositoryFactory;

    public InteractorFactoryImpl(RepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }

    @Override
    public MainActivityInteractor getMainActivityInteractor() {
        return new MainActivityInteractorImpl(repositoryFactory.getMainActivityRepository());
    }

    @Override
    public HerramientasFragmentInteractor getHerramientasFragmentInteractor() {
        return new HerramientasFragmentInteractorImpl(repositoryFactory.getHerramientasFragmentRepository());
    }
}
