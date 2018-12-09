package android.com.herramientime.modules.experiencias.repository.impl;

import android.com.herramientime.R;
import android.com.herramientime.domain.processor.ProcessorExperiencia;
import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.domain.repository.MainActivityRepository;
import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.herramientime.modules.experiencias.repository.ExperienciaDetalleFragmentRepository;
import android.com.herramientime.modules.usuarios.entities.Usuario;
import android.com.rest.RestApiServiceHelper;
import android.com.rest.entities.ExperienciaRest;
import android.com.rest.entities.InternetException;
import android.content.res.Resources;

import java.util.List;

public class ExperienciaDetalleFragmentRepositoryImpl implements ExperienciaDetalleFragmentRepository {

    private final RestApiServiceHelper restApiServiceHelper;
    private final ProcessorExperiencia processorExperiencia;
    private final Resources resources;
    private final MainActivityRepository mainActivityRepository;

    public ExperienciaDetalleFragmentRepositoryImpl(ProcessorExperiencia processorExperiencia, RestApiServiceHelper restApiServiceHelper, Resources resources, MainActivityRepository mainActivityRepository) {
        this.restApiServiceHelper = restApiServiceHelper;
        this.processorExperiencia = processorExperiencia;
        this.resources = resources;
        this.mainActivityRepository = mainActivityRepository;
    }

    @Override
    public Experiencia getExperienciaById(String idExperiencia) throws LocalException, InternetException {
        List<ExperienciaRest> experiencias = restApiServiceHelper.getExperiencias();
        ExperienciaRest experienciaRest = new ExperienciaRest(idExperiencia);
        int index = experiencias.indexOf(experienciaRest);
        if (index > -1) {
            return processorExperiencia.convertFrom(experiencias.get(index));
        } else {
            throw new LocalException(resources.getString(R.string.error_not_found_experiencia));
        }
    }

    @Override
    public Boolean checkReservar() throws LocalException, InternetException {
        Usuario userLogged = mainActivityRepository.getLoggedUser();
        if (userLogged == null) {
            throw new LocalException(resources.getString(R.string.prompt_error_first_log_reserve));
        }
        return true;
    }
}
