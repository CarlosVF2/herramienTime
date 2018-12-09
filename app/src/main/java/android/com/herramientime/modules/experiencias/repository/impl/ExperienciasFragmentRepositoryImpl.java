package android.com.herramientime.modules.experiencias.repository.impl;

import android.com.herramientime.R;
import android.com.herramientime.domain.processor.ProcessorExperiencia;
import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.domain.repository.MainActivityRepository;
import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.herramientime.modules.experiencias.repository.ExperienciasFragmentRepository;
import android.com.herramientime.modules.usuarios.entities.Usuario;
import android.com.rest.RestApiServiceHelper;
import android.com.rest.entities.ExperienciaRest;
import android.com.rest.entities.InternetException;
import android.content.res.Resources;

import java.util.List;

public class ExperienciasFragmentRepositoryImpl implements ExperienciasFragmentRepository {

    private final ProcessorExperiencia processorExperiencia;
    private final RestApiServiceHelper restApiServiceHelper;
    private final Resources resources;
    private final MainActivityRepository mainActivityRepository;

    public ExperienciasFragmentRepositoryImpl(ProcessorExperiencia processorExperiencia, RestApiServiceHelper restApiServiceHelper, Resources resources, MainActivityRepository mainActivityRepository) {
        this.processorExperiencia = processorExperiencia;
        this.restApiServiceHelper = restApiServiceHelper;
        this.resources = resources;
        this.mainActivityRepository = mainActivityRepository;
    }

    @Override
    public List<Experiencia> getExperiencias() throws InternetException {
        List<ExperienciaRest> experienciaRests = restApiServiceHelper.getExperiencias();
        return processorExperiencia.convertFrom(experienciaRests);
    }

    @Override
    public Boolean checkUpload() throws InternetException, LocalException {
        Usuario userLogged = mainActivityRepository.getLoggedUser();
        if (userLogged == null) {
            throw new LocalException(resources.getString(R.string.error_need_log_upload_experience));
        }
        return true;
    }
}
