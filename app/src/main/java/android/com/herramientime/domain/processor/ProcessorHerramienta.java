package android.com.herramientime.domain.processor;

import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.rest.entities.HerramientaRest;
import android.com.rest.utils.Utilidades;

import java.util.ArrayList;
import java.util.List;

public class ProcessorHerramienta {

    public List<Herramienta> convertFrom(List<HerramientaRest> herramientaRests) {
        List<Herramienta> herramientas = new ArrayList<>();
        for (HerramientaRest herramientaRest : herramientaRests) {
            herramientas.add(convertFrom(herramientaRest));
        }
        return herramientas;
    }

    public Herramienta convertFrom(HerramientaRest from) {
        Herramienta herramienta = new Herramienta();
        herramienta.setId(from.getId());
        herramienta.setDescripcion(from.getDescripcion());
        herramienta.setIdUsuario(from.getIdUsuario());
        herramienta.setNombreUsuario(from.getNombreUsuario());
        herramienta.setReservada(from.getReservada().contentEquals("1"));
        herramienta.setUrlImagen(from.getUrlImagen());
        herramienta.setFechaFinTexto(from.getFechaFin());
        herramienta.setFechaFin(Utilidades.getDateFromString(from.getFechaFin()));
        herramienta.setFechaInicioTexto(from.getFechaInicio());
        herramienta.setFechaFin(Utilidades.getDateFromString(from.getFechaInicio()));
        herramienta.setSimboloMoneda(from.getSimboloMoneda());
        herramienta.setResumen(from.getResumen());
        herramienta.setMoneda(from.getMoneda());
        return herramienta;
    }


}
