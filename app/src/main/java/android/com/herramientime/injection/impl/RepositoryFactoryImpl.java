package android.com.herramientime.injection.impl;

import android.com.herramientime.domain.processor.Processors;
import android.com.herramientime.injection.PresenterFactory;
import android.com.herramientime.injection.RepositoryFactory;
import android.com.herramientime.modules.domain.repository.MainActivityRepository;
import android.com.herramientime.modules.domain.repository.impl.MainActivityRepositoryImpl;
import android.com.herramientime.modules.experiencias.repository.AlquilerExperienciaFragmentRepository;
import android.com.herramientime.modules.experiencias.repository.ExperienciaDetalleFragmentRepository;
import android.com.herramientime.modules.experiencias.repository.ExperienciasFragmentRepository;
import android.com.herramientime.modules.experiencias.repository.impl.AlquilerExperienciaFragmentRepositoryImpl;
import android.com.herramientime.modules.experiencias.repository.impl.ExperienciaDetalleFragmentRepositoryImpl;
import android.com.herramientime.modules.experiencias.repository.impl.ExperienciasFragmentRepositoryImpl;
import android.com.herramientime.modules.herramientas.repository.AlquilerHerramientaFragmentRepository;
import android.com.herramientime.modules.herramientas.repository.HerramientaDetalleFragmentRepository;
import android.com.herramientime.modules.herramientas.repository.HerramientasFragmentRepository;
import android.com.herramientime.modules.herramientas.repository.impl.AlquilerHerramientaFragmentRepositoryImpl;
import android.com.herramientime.modules.herramientas.repository.impl.HerramientaDetalleFragmentRepositoryImpl;
import android.com.herramientime.modules.herramientas.repository.impl.HerramientasFragmentRepositoryImpl;
import android.com.herramientime.modules.reservar.repository.ReservaFragmentRepository;
import android.com.herramientime.modules.reservar.repository.impl.ReservaFragmentRepositoryImpl;
import android.com.rest.RestApiServiceHelper;
import android.content.Context;

/**
 * Created by carlos 06/11/2018.
 */
public class RepositoryFactoryImpl implements RepositoryFactory {

    private final Context context;
    private final PresenterFactory presenterFactory;
    private final RestApiServiceHelper restApiServiceHelper;
    private final Processors processors;


    public RepositoryFactoryImpl(Context context, PresenterFactory presenterFactory, RestApiServiceHelper restApiServiceHelper, Processors processors) {
        this.context = context;
        this.presenterFactory = presenterFactory;
        this.restApiServiceHelper = restApiServiceHelper;
        this.processors = processors;
    }

    @Override
    public MainActivityRepository getMainActivityRepository() {
        return new MainActivityRepositoryImpl();
    }

    @Override
    public HerramientasFragmentRepository getHerramientasFragmentRepository() {
        return new HerramientasFragmentRepositoryImpl(processors.getProcessorHerramienta(), restApiServiceHelper);
    }

    @Override
    public HerramientaDetalleFragmentRepository getHerramientaDetalleFragmentRepository() {
        return new HerramientaDetalleFragmentRepositoryImpl(processors.getProcessorHerramienta(), restApiServiceHelper, context.getResources());
    }

    @Override
    public ExperienciasFragmentRepository getExperienciasFragmentRepository() {
        return new ExperienciasFragmentRepositoryImpl(processors.getProcessorExperiencia(), restApiServiceHelper);
    }

    @Override
    public ExperienciaDetalleFragmentRepository getExperienciaDetalleFragmentRepository() {
        return new ExperienciaDetalleFragmentRepositoryImpl(processors.getProcessorExperiencia(), restApiServiceHelper, context.getResources());
    }

    @Override
    public ReservaFragmentRepository getReservaFragmentRepository() {
        return new ReservaFragmentRepositoryImpl();
    }

    @Override
    public AlquilerHerramientaFragmentRepository getAlquilerHerramientaFragmentRepository() {
        return new AlquilerHerramientaFragmentRepositoryImpl(restApiServiceHelper, processors.getProcessorHerramienta());
    }

    @Override
    public AlquilerExperienciaFragmentRepository getAlquilerExperienciaFragmentRepository() {
        return new AlquilerExperienciaFragmentRepositoryImpl(restApiServiceHelper, processors.getProcessorExperiencia());
    }
}
