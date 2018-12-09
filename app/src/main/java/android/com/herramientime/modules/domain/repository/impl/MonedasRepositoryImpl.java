package android.com.herramientime.modules.domain.repository.impl;

import android.com.herramientime.domain.processor.ProcessorMoneda;
import android.com.herramientime.modules.domain.entities.Moneda;
import android.com.herramientime.modules.domain.repository.MonedasRepository;
import android.com.rest.RestApiServiceHelper;
import android.com.rest.entities.InternetException;

import java.util.List;

public class MonedasRepositoryImpl implements MonedasRepository {

    private final RestApiServiceHelper restApiServiceHelper;
    private final ProcessorMoneda processorMoneda;

    public MonedasRepositoryImpl(RestApiServiceHelper restApiServiceHelper, ProcessorMoneda processorMoneda) {
        this.restApiServiceHelper = restApiServiceHelper;
        this.processorMoneda = processorMoneda;
    }

    @Override
    public List<Moneda> getMonedas() throws InternetException {
        return processorMoneda.convertFrom(restApiServiceHelper.getMonedas());
    }
}
