package android.com.herramientime.modules.experiencias.repository.impl;

import android.com.herramientime.domain.processor.ProcessorExperiencia;
import android.com.herramientime.modules.experiencias.entities.AlquilerExperiencia;
import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.herramientime.modules.experiencias.repository.AlquilerExperienciaFragmentRepository;
import android.com.rest.RestApiServiceHelper;
import android.com.rest.entities.ExperienciaRest;
import android.com.rest.entities.InternetException;
import android.text.TextUtils;

import java.util.List;

public class AlquilerExperienciaFragmentRepositoryImpl implements AlquilerExperienciaFragmentRepository {

    private final RestApiServiceHelper restApiServiceHelper;
    private final ProcessorExperiencia processorExperiencia;

    public AlquilerExperienciaFragmentRepositoryImpl(RestApiServiceHelper restApiServiceHelper, ProcessorExperiencia processorExperiencia) {
        this.restApiServiceHelper = restApiServiceHelper;
        this.processorExperiencia = processorExperiencia;
    }

    @Override
    public Experiencia saveExperiencia(AlquilerExperiencia alquilerExperiencia) throws InternetException {
        checkIfAllFieldsAreFill(alquilerExperiencia);
        List<ExperienciaRest> experienciaRests = restApiServiceHelper.getExperiencias();
        ExperienciaRest experienciaRest = new ExperienciaRest();
        startValues(experienciaRest);
        String id = getLastId(experienciaRests.get(experienciaRests.size() - 1).getId());
        experienciaRest.setId(id);
        experienciaRest.setPrecioHora(alquilerExperiencia.getPrecioTexto());
        experienciaRest.setDescripcion(alquilerExperiencia.getTitulo());
        experienciaRest.setResumen(experienciaRest.getResumen());
        experienciaRests.add(experienciaRest);
        restApiServiceHelper.postExperiencia(experienciaRests);
        return processorExperiencia.convertFrom(experienciaRest);
    }

    private String getLastId(String id) {
        int index = Integer.valueOf(id);
        index = index + 1;
        return String.format("%05d", index);
    }

    private void checkIfAllFieldsAreFill(AlquilerExperiencia alquilerExperiencia) throws InternetException {
        if (TextUtils.isEmpty(alquilerExperiencia.getDescripcion())) {
            throw new InternetException("Es necesario rellenar la descripcion");
        } else if (TextUtils.isEmpty(alquilerExperiencia.getPrecioTexto())) {
            throw new InternetException("Es necesario rellenar el precio");
        } else if (TextUtils.isEmpty(alquilerExperiencia.getTitulo())) {
            throw new InternetException("Es necesario rellenar el titulo");
        }
    }

    private void startValues(ExperienciaRest experienciaRest) {
        experienciaRest.setIdUsuario("cvegfer");
        experienciaRest.setUrlImagen("");
    }
}
