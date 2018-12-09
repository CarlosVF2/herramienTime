package android.com.herramientime.domain.processor;

import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.rest.entities.ExperienciaRest;
import android.com.rest.utils.Utilidades;
import android.text.TextUtils;

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
        experiencia.setReservada(from.getReservada());
        experiencia.setFechaInicialTexto(from.getFechaInicial());
        if (!TextUtils.isEmpty(from.getFechaInicial())) {
            experiencia.setFechaInicial(Utilidades.getDateFromString(from.getFechaInicial()));
        }
        experiencia.setFechaFinalTexto(from.getFechaFinal());
        if (!TextUtils.isEmpty(from.getFechaFinal())) {
            experiencia.setFechaInicial(Utilidades.getDateFromString(from.getFechaFinal()));
        }
        if (TextUtils.isEmpty(from.getReservada())) {
            experiencia.setReservada(false);
        } else {
            experiencia.setReservada(from.getReservada().contentEquals("1"));
        }
        return experiencia;
    }

    public ExperienciaRest convertFrom(Experiencia from) {
        ExperienciaRest experiencia = new ExperienciaRest();
        experiencia.setId(from.getId());
        experiencia.setIdUsuario(from.getIdUsuario());
        experiencia.setDescripcion(from.getDescripcion());
        experiencia.setResumen(from.getResumen());
        experiencia.setUrlImagen(from.getUrlImagen());
        experiencia.setMoneda(from.getMoneda());
        experiencia.setPrecioHora(from.getPrecioHora());
        experiencia.setSimboloMoneda(from.getSimboloMoneda());
        experiencia.setReservada(from.getReservada());
        experiencia.setFechaInicial(from.getFechaInicialTexto());
        experiencia.setFechaFinal(from.getFechaFinalTexto());
        experiencia.setReservada(from.getReservada());
        return experiencia;
    }
}
