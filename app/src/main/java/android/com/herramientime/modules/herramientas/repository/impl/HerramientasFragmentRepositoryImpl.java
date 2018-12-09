package android.com.herramientime.modules.herramientas.repository.impl;

import android.com.herramientime.domain.processor.ProcessorHerramienta;
import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.herramientime.modules.herramientas.repository.HerramientasFragmentRepository;
import android.com.rest.RestApiServiceHelper;
import android.com.rest.entities.HerramientaRest;
import android.com.rest.entities.InternetException;

import java.util.List;

public class HerramientasFragmentRepositoryImpl implements HerramientasFragmentRepository {

    private final ProcessorHerramienta processorHerramienta;
    private final RestApiServiceHelper restApiServiceHelper;

    public HerramientasFragmentRepositoryImpl(ProcessorHerramienta processorHerramienta, RestApiServiceHelper restApiServiceHelper) {
        this.processorHerramienta = processorHerramienta;
        this.restApiServiceHelper = restApiServiceHelper;
    }

    @Override
    public List<Herramienta> getHerramientas() throws Exception {
        try {
            List<HerramientaRest> herramientaRests = restApiServiceHelper.getHerramientas();
            return processorHerramienta.convertFrom(herramientaRests);
        } catch (InternetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
