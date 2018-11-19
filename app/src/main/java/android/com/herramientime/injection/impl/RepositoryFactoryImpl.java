package android.com.herramientime.injection.impl;

import android.com.herramientime.injection.PresenterFactory;
import android.com.herramientime.injection.RepositoryFactory;
import android.com.herramientime.modules.domain.repository.MainActivityRepository;
import android.com.herramientime.modules.domain.repository.impl.MainActivityRepositoryImpl;
import android.com.herramientime.modules.herramientas.repository.HerramientasFragmentRepository;
import android.com.herramientime.modules.herramientas.repository.impl.HerramientasFragmentRepositoryImpl;
import android.content.Context;

/**
 * Created by carlos 06/11/2018.
 */
public class RepositoryFactoryImpl implements RepositoryFactory {

    private Context context;
    private PresenterFactory presenterFactory;


    public RepositoryFactoryImpl(Context context, PresenterFactory presenterFactory) {
        this.context = context;
        this.presenterFactory = presenterFactory;
    }

    @Override
    public MainActivityRepository getMainActivityRepository() {
        return new MainActivityRepositoryImpl();
    }

    @Override
    public HerramientasFragmentRepository getHerramientasFragmentRepository() {
        return new HerramientasFragmentRepositoryImpl();
    }
}
