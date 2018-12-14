package android.com.herramientime.injection.impl;

import android.com.herramientime.domain.processor.Processors;
import android.com.herramientime.injection.RepositoryFactory;
import android.com.herramientime.modules.domain.repository.CategoriasRepository;
import android.com.herramientime.modules.domain.repository.MainActivityRepository;
import android.com.herramientime.modules.domain.repository.MonedasRepository;
import android.com.herramientime.modules.domain.repository.impl.CategoriasRepositoryImpl;
import android.com.herramientime.modules.domain.repository.impl.MainActivityRepositoryImpl;
import android.com.herramientime.modules.domain.repository.impl.MonedasRepositoryImpl;
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
import android.com.herramientime.modules.login.repository.LoginFragmentRepository;
import android.com.herramientime.modules.login.repository.impl.LoginFragmentRepositoryImpl;
import android.com.herramientime.modules.map.repository.MapFragmentRepository;
import android.com.herramientime.modules.map.repository.impl.MapFragmentRepositoryImpl;
import android.com.herramientime.modules.reservar.repository.ReservaFragmentRepository;
import android.com.herramientime.modules.reservar.repository.impl.ReservaFragmentRepositoryImpl;
import android.com.rest.RestApiServiceHelper;
import android.content.Context;
import android.content.res.Resources;

/**
 * Created by carlos 06/11/2018.
 */
public class RepositoryFactoryImpl implements RepositoryFactory {

    private final Context context;
    private final RestApiServiceHelper restApiServiceHelper;
    private final Processors processors;
    private final Resources resources;


    public RepositoryFactoryImpl(Context context, RestApiServiceHelper restApiServiceHelper, Processors processors, Resources resources) {
        this.context = context;
        this.restApiServiceHelper = restApiServiceHelper;
        this.processors = processors;
        this.resources = resources;
    }

    @Override
    public MainActivityRepository getMainActivityRepository() {
        return new MainActivityRepositoryImpl(context, processors.getProcessorUsuario(), restApiServiceHelper);
    }

    @Override
    public HerramientasFragmentRepository getHerramientasFragmentRepository() {
        return new HerramientasFragmentRepositoryImpl(processors.getProcessorHerramienta(), restApiServiceHelper, resources, getMainActivityRepository(), processors.getFiltroHerramientas());
    }

    @Override
    public HerramientaDetalleFragmentRepository getHerramientaDetalleFragmentRepository() {
        return new HerramientaDetalleFragmentRepositoryImpl(processors.getProcessorHerramienta(), restApiServiceHelper, context.getResources(), getMainActivityRepository());
    }

    @Override
    public ExperienciasFragmentRepository getExperienciasFragmentRepository() {
        return new ExperienciasFragmentRepositoryImpl(processors.getProcessorExperiencia(), restApiServiceHelper, resources, getMainActivityRepository(), processors.getFiltroExperiencia());
    }

    @Override
    public ExperienciaDetalleFragmentRepository getExperienciaDetalleFragmentRepository() {
        return new ExperienciaDetalleFragmentRepositoryImpl(processors.getProcessorExperiencia(), restApiServiceHelper, context.getResources(), getMainActivityRepository());
    }

    @Override
    public ReservaFragmentRepository getReservaFragmentRepository() {
        return new ReservaFragmentRepositoryImpl(restApiServiceHelper, processors.getProcessorHerramienta(), processors.getProcessorExperiencia());
    }

    @Override
    public AlquilerHerramientaFragmentRepository getAlquilerHerramientaFragmentRepository() {
        return new AlquilerHerramientaFragmentRepositoryImpl(restApiServiceHelper, processors.getProcessorHerramienta(), geCategoriasRepository(), geMonedasRepository(), getMainActivityRepository(), context);
    }

    @Override
    public AlquilerExperienciaFragmentRepository getAlquilerExperienciaFragmentRepository() {
        return new AlquilerExperienciaFragmentRepositoryImpl(restApiServiceHelper, processors.getProcessorExperiencia(), geMonedasRepository(), getMainActivityRepository());
    }

    @Override
    public LoginFragmentRepository getLoginFragmentRepository() {
        return new LoginFragmentRepositoryImpl(restApiServiceHelper, processors.getProcessorUsuario(), context.getResources(), context);
    }

    @Override
    public MapFragmentRepository getMapFragmentRepository() {
        return new MapFragmentRepositoryImpl();
    }

    @Override
    public CategoriasRepository geCategoriasRepository() {
        return new CategoriasRepositoryImpl(restApiServiceHelper, processors.getProcessorCategoria());
    }

    @Override
    public MonedasRepository geMonedasRepository() {
        return new MonedasRepositoryImpl(restApiServiceHelper, processors.geProcessorMoneda());
    }
}
