package android.com.herramientime.modules.experiencias.repository.impl;

import android.com.herramientime.domain.processor.ProcessorExperiencia;
import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.herramientime.modules.experiencias.repository.ExperienciasFragmentRepository;
import android.com.rest.RestApiServiceHelper;
import android.com.rest.entities.ExperienciaRest;
import android.com.rest.entities.InternetException;

import java.util.List;

public class ExperienciasFragmentRepositoryImpl implements ExperienciasFragmentRepository {

    private final ProcessorExperiencia processorExperiencia;
    private final RestApiServiceHelper restApiServiceHelper;

    public ExperienciasFragmentRepositoryImpl(ProcessorExperiencia processorExperiencia, RestApiServiceHelper restApiServiceHelper) {
        this.processorExperiencia = processorExperiencia;
        this.restApiServiceHelper = restApiServiceHelper;
    }

    @Override
    public List<Experiencia> getExperiencias() throws InternetException {
        List<ExperienciaRest> experienciaRests = restApiServiceHelper.getExperiencias();
        return processorExperiencia.convertFrom(experienciaRests);
    }
}
