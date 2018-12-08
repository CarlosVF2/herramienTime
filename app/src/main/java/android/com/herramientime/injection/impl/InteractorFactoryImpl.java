package android.com.herramientime.injection.impl;


import android.com.herramientime.injection.InteractorFactory;
import android.com.herramientime.injection.RepositoryFactory;
import android.com.herramientime.modules.domain.interactor.MainActivityInteractor;
import android.com.herramientime.modules.domain.interactor.impl.MainActivityInteractorImpl;
import android.com.herramientime.modules.experiencias.interactor.ExperienciaDetalleFragmentInteractor;
import android.com.herramientime.modules.experiencias.interactor.ExperienciaFragmentInteractor;
import android.com.herramientime.modules.experiencias.interactor.impl.ExperienciaDetalleFragmentInteractorImpl;
import android.com.herramientime.modules.experiencias.interactor.impl.ExperienciaFragmentInteractorImpl;
import android.com.herramientime.modules.herramientas.interactor.AlquilerHerramientaFragmentInteractor;
import android.com.herramientime.modules.herramientas.interactor.HerramientaDetalleFragmentInteractor;
import android.com.herramientime.modules.herramientas.interactor.HerramientasFragmentInteractor;
import android.com.herramientime.modules.herramientas.interactor.impl.AlquilerHerramientaFragmentInteractorImpl;
import android.com.herramientime.modules.herramientas.interactor.impl.HerramientaDetalleFragmentInteractorImpl;
import android.com.herramientime.modules.herramientas.interactor.impl.HerramientasFragmentInteractorImpl;
import android.com.herramientime.modules.reservar.interactor.ReservaFragmentInteractor;
import android.com.herramientime.modules.reservar.interactor.impl.ReservaFragmentInteractorImpl;

import com.seidor.core.utils.injection.SchedulerFactory;

/**
 * Created by carlos 06/11/2018.
 */
public class InteractorFactoryImpl implements InteractorFactory {
    private final RepositoryFactory repositoryFactory;
    private final SchedulerFactory schedulerFactory;

    public InteractorFactoryImpl(RepositoryFactory repositoryFactory, SchedulerFactory schedulerFactory) {
        this.repositoryFactory = repositoryFactory;
        this.schedulerFactory = schedulerFactory;
    }

    @Override
    public MainActivityInteractor getMainActivityInteractor() {
        return new MainActivityInteractorImpl(repositoryFactory.getMainActivityRepository());
    }

    @Override
    public HerramientasFragmentInteractor getHerramientasFragmentInteractor() {
        return new HerramientasFragmentInteractorImpl( schedulerFactory.getUIScheduler(), repositoryFactory.getHerramientasFragmentRepository());
    }

    @Override
    public HerramientaDetalleFragmentInteractor getHerramientaDetalleFragmentInteractor() {
        return new HerramientaDetalleFragmentInteractorImpl(schedulerFactory.getUIScheduler(), repositoryFactory.getHerramientaDetalleFragmentRepository());
    }

    @Override
    public ExperienciaFragmentInteractor getExperienciasFragmentInteractor() {
        return new ExperienciaFragmentInteractorImpl(schedulerFactory.getUIScheduler(), repositoryFactory.getExperienciasFragmentRepository());
    }

    @Override
    public ExperienciaDetalleFragmentInteractor getExperienciaDetalleFragmentInteractor() {
        return new ExperienciaDetalleFragmentInteractorImpl(schedulerFactory.getUIScheduler(), repositoryFactory.getExperienciaDetalleFragmentRepository());
    }

    @Override
    public ReservaFragmentInteractor getReservaFragmentInteractor() {
        return new ReservaFragmentInteractorImpl(schedulerFactory.getUIScheduler(), repositoryFactory.getReservaFragmentRepository());
    }

    @Override
    public AlquilerHerramientaFragmentInteractor getAlquilerHerramientaFragmentInteractor() {
        return new AlquilerHerramientaFragmentInteractorImpl(schedulerFactory.getUIScheduler(), repositoryFactory.getAlquilerHerramientaFragmentRepository());
    }
}
