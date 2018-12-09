package android.com.herramientime.modules.herramientas.repository.impl;

import android.com.herramientime.R;
import android.com.herramientime.domain.processor.ProcessorHerramienta;
import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.domain.repository.MainActivityRepository;
import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.herramientime.modules.herramientas.repository.HerramientaDetalleFragmentRepository;
import android.com.herramientime.modules.usuarios.entities.Usuario;
import android.com.rest.RestApiServiceHelper;
import android.com.rest.entities.HerramientaRest;
import android.com.rest.entities.InternetException;
import android.content.res.Resources;

import java.util.List;

public class HerramientaDetalleFragmentRepositoryImpl implements HerramientaDetalleFragmentRepository {

    private final RestApiServiceHelper restApiServiceHelper;
    private final ProcessorHerramienta processorHerramienta;
    private final Resources resources;
    private final MainActivityRepository mainActivityRepository;

    public HerramientaDetalleFragmentRepositoryImpl(ProcessorHerramienta processorHerramienta, RestApiServiceHelper restApiServiceHelper, Resources resources, MainActivityRepository mainActivityRepository) {
        this.restApiServiceHelper = restApiServiceHelper;
        this.processorHerramienta = processorHerramienta;
        this.resources = resources;
        this.mainActivityRepository = mainActivityRepository;
    }

    @Override
    public Herramienta getHerramientaById(String idHerramienta) throws LocalException, InternetException {
        List<HerramientaRest> herramientas = restApiServiceHelper.getHerramientas();
        HerramientaRest herramientaRest = new HerramientaRest(idHerramienta);
        int index = herramientas.indexOf(herramientaRest);
        if (index > -1) {
            return processorHerramienta.convertFrom(herramientas.get(index));
        } else {
            throw new LocalException(resources.getString(R.string.prompt_not_found_herramienta));
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
