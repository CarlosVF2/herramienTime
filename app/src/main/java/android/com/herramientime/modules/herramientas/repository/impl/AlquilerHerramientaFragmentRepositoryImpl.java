package android.com.herramientime.modules.herramientas.repository.impl;

import android.com.herramientime.domain.processor.ProcessorHerramienta;
import android.com.herramientime.modules.herramientas.entities.AlquilerHerramienta;
import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.herramientime.modules.herramientas.repository.AlquilerHerramientaFragmentRepository;
import android.com.rest.RestApiServiceHelper;
import android.com.rest.entities.HerramientaRest;
import android.com.rest.entities.InternetException;
import android.text.TextUtils;

import java.util.List;

public class AlquilerHerramientaFragmentRepositoryImpl implements AlquilerHerramientaFragmentRepository {

    private final RestApiServiceHelper restApiServiceHelper;
    private final ProcessorHerramienta processorHerramienta;

    public AlquilerHerramientaFragmentRepositoryImpl(RestApiServiceHelper restApiServiceHelper, ProcessorHerramienta processorHerramienta) {
        this.restApiServiceHelper = restApiServiceHelper;
        this.processorHerramienta = processorHerramienta;
    }

    @Override
    public Herramienta saveHerramienta(AlquilerHerramienta alquilerHerramienta) throws InternetException {
        checkIfAllFieldsAreFill(alquilerHerramienta);
        List<HerramientaRest> herramientaRests = restApiServiceHelper.getHerramientas();
        HerramientaRest herramientaRest = new HerramientaRest();
        startValues(herramientaRest);
        String id = getLastId(herramientaRests.get(herramientaRests.size() - 1).getId());
        herramientaRest.setId(id);
        herramientaRest.setPrecio(alquilerHerramienta.getPrecioTexto());
        herramientaRest.setDescripcion(alquilerHerramienta.getTitulo());
        herramientaRest.setResumen(herramientaRest.getResumen());
        herramientaRests.add(herramientaRest);
        restApiServiceHelper.postHerramienta(herramientaRests);
        return processorHerramienta.convertFrom(herramientaRest);
    }

    private String getLastId(String id) {
        int index = Integer.valueOf(id);
        index = index + 1;
        return String.format("%05d", index);
    }

    private void checkIfAllFieldsAreFill(AlquilerHerramienta alquilerHerramienta) throws InternetException {
        if (TextUtils.isEmpty(alquilerHerramienta.getDescripcion())) {
            throw new InternetException("Es necesario rellenar la descripcion");
        } else if (TextUtils.isEmpty(alquilerHerramienta.getPrecioTexto())) {
            throw new InternetException("Es necesario rellenar el precio");
        } else if (TextUtils.isEmpty(alquilerHerramienta.getTitulo())) {
            throw new InternetException("Es necesario rellenar el titulo");
        }
    }

    private void startValues(HerramientaRest herramientaRest) {
        herramientaRest.setReservada("0");
        herramientaRest.setIdUsuario("cvegfer");
        herramientaRest.setNombreUsuario("Carlos");
        herramientaRest.setFechaFin("");
        herramientaRest.setFechaInicio("");
        herramientaRest.setUrlImagen("");
    }
}
