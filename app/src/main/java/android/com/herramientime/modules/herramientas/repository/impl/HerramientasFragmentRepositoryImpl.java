package android.com.herramientime.modules.herramientas.repository.impl;

import android.com.herramientime.R;
import android.com.herramientime.domain.processor.ProcessorFiltroHerramienta;
import android.com.herramientime.domain.processor.ProcessorHerramienta;
import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.domain.entities.ResultsException;
import android.com.herramientime.modules.domain.entities.UsuarioException;
import android.com.herramientime.modules.domain.repository.MainActivityRepository;
import android.com.herramientime.modules.herramientas.entities.FiltrosHerramientas;
import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.herramientime.modules.herramientas.repository.HerramientasFragmentRepository;
import android.com.herramientime.modules.usuarios.entities.Usuario;
import android.com.rest.RestApiServiceHelper;
import android.com.rest.entities.HerramientaRest;
import android.com.rest.entities.InternetException;
import android.content.res.Resources;

import java.util.List;

public class HerramientasFragmentRepositoryImpl implements HerramientasFragmentRepository {

    private final ProcessorHerramienta processorHerramienta;
    private final RestApiServiceHelper restApiServiceHelper;
    private final Resources resources;
    private final MainActivityRepository mainActivityRepository;
    private final ProcessorFiltroHerramienta processorFiltroHerramienta;

    public HerramientasFragmentRepositoryImpl(ProcessorHerramienta processorHerramienta, RestApiServiceHelper restApiServiceHelper, Resources resources, MainActivityRepository mainActivityRepository, ProcessorFiltroHerramienta processorFiltroHerramienta) {
        this.processorHerramienta = processorHerramienta;
        this.restApiServiceHelper = restApiServiceHelper;
        this.resources = resources;
        this.mainActivityRepository = mainActivityRepository;
        this.processorFiltroHerramienta = processorFiltroHerramienta;
    }

    @Override
    public List<Herramienta> getHerramientas(FiltrosHerramientas filtrosHerramientas) throws InternetException, ResultsException {
        List<HerramientaRest> herramientaRests = restApiServiceHelper.getHerramientas(processorFiltroHerramienta.convertFrom(filtrosHerramientas));
        if(herramientaRests.size()==0){
            throw new ResultsException("No se han encontrado resultados");
        }
        return processorHerramienta.convertFrom(herramientaRests);
    }

    @Override
    public Boolean checkUpload() throws InternetException, LocalException, UsuarioException {
        Usuario userLogged = mainActivityRepository.getLoggedUser();
        if (userLogged == null) {
            throw new UsuarioException(resources.getString(R.string.error_need_log_upload_herramienta));
        }
        return true;
    }
}
