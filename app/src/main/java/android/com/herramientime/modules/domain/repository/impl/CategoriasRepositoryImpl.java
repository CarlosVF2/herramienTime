package android.com.herramientime.modules.domain.repository.impl;

import android.com.herramientime.domain.processor.ProcessorCategoria;
import android.com.herramientime.modules.domain.entities.Categoria;
import android.com.herramientime.modules.domain.repository.CategoriasRepository;
import android.com.rest.RestApiServiceHelper;
import android.com.rest.entities.InternetException;

import java.util.List;

public class CategoriasRepositoryImpl implements CategoriasRepository {

    private final RestApiServiceHelper restApiServiceHelper;
    private final ProcessorCategoria processorCategoria;

    public CategoriasRepositoryImpl(RestApiServiceHelper restApiServiceHelper, ProcessorCategoria processorCategoria) {
        this.restApiServiceHelper = restApiServiceHelper;
        this.processorCategoria = processorCategoria;
    }

    @Override
    public List<Categoria> getCategorias() throws InternetException {
        return processorCategoria.convertFrom(restApiServiceHelper.getCategorias());
    }
}
