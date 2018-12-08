package android.com.herramientime.domain.processor;

import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.rest.entities.ExperienciaRest;

import java.util.ArrayList;
import java.util.List;

public class ProcessorExperiencia {

    public List<Experiencia> convertFrom(List<ExperienciaRest> experienciaRests) {
        List<Experiencia> experiencias = new ArrayList<>();
        for (ExperienciaRest experienciaRest : experienciaRests) {
            experiencias.add(convertFrom(experienciaRest));
        }
        return experiencias;
    }

    public Experiencia convertFrom(ExperienciaRest from) {
        Experiencia experiencia = new Experiencia();
        experiencia.setId(from.getId());
        experiencia.setIdUsuario(from.getIdUsuario());
        experiencia.setDescripcion(from.getDescripcion());
        experiencia.setResumen(from.getResumen());
        experiencia.setUrlImagen(from.getUrlImagen());
        experiencia.setMoneda(from.getMoneda());
        experiencia.setPrecioHora(from.getPrecioHora());
        experiencia.setSimboloMoneda(from.getSimboloMoneda());
        return experiencia;
    }

}
