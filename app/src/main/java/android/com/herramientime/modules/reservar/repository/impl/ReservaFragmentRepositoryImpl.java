package android.com.herramientime.modules.reservar.repository.impl;

import android.com.herramientime.domain.processor.ProcessorExperiencia;
import android.com.herramientime.domain.processor.ProcessorHerramienta;
import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.herramientime.modules.reservar.repository.ReservaFragmentRepository;
import android.com.rest.RestApiServiceHelper;
import android.com.rest.entities.ExperienciaRest;
import android.com.rest.entities.HerramientaRest;
import android.com.rest.entities.InternetException;

import java.util.List;

public class ReservaFragmentRepositoryImpl implements ReservaFragmentRepository {

    private final RestApiServiceHelper restApiServiceHelper;
    private final ProcessorHerramienta processorHerramienta;
    private final ProcessorExperiencia processorExperiencia;

    public ReservaFragmentRepositoryImpl(RestApiServiceHelper restApiServiceHelper, ProcessorHerramienta processorHerramienta, ProcessorExperiencia processorExperiencia) {
        this.restApiServiceHelper = restApiServiceHelper;
        this.processorHerramienta = processorHerramienta;
        this.processorExperiencia = processorExperiencia;
    }

    @Override
    public Experiencia getExperienciaById(String idExperiencia) throws InternetException {
        List<ExperienciaRest> experienciaRests = restApiServiceHelper.getExperiencias();
        ExperienciaRest experienciaRest = new ExperienciaRest(idExperiencia);
        int index = experienciaRests.indexOf(experienciaRest);
        if (index > -1) {
            return processorExperiencia.convertFrom(experienciaRests.get(index));
        }
        return null;
    }

    @Override
    public Herramienta getHerramientaById(String idHerramienta) throws InternetException {
        List<HerramientaRest> herramientaRestList = restApiServiceHelper.getHerramientas();
        HerramientaRest herramientaRest = new HerramientaRest(idHerramienta);
        int index = herramientaRestList.indexOf(herramientaRest);
        if (index > -1) {
            return processorHerramienta.convertFrom(herramientaRestList.get(index));
        }
        return null;
    }
}
