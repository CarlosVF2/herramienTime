package android.com.herramientime.domain.processor;

import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.rest.entities.HerramientaRest;
import android.com.rest.utils.Utilidades;
import android.text.TextUtils;

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
        herramienta.setUrlImagen(from.getUrlImagen());
        herramienta.setFechaFinTexto(from.getFechaFin());
        herramienta.setFechaInicioTexto(from.getFechaInicio());
        herramienta.setSimboloMoneda(from.getSimboloMoneda());
        herramienta.setResumen(from.getResumen());
        herramienta.setMoneda(from.getMoneda());
        herramienta.setPrecioText(from.getPrecio());
        herramienta.setPrecio(Double.valueOf(from.getPrecio()));
        herramienta.setCategoria(from.getCategoria());
        herramienta.setCategoriaDescriptivo(from.getCategoriaDescriptivo());
        ;
        if (!TextUtils.isEmpty(from.getFechaInicio())) {
            herramienta.setFechaFin(Utilidades.getDateFromString(from.getFechaInicio()));
        }
        if (!TextUtils.isEmpty(from.getFechaFin())) {
            herramienta.setFechaFin(Utilidades.getDateFromString(from.getFechaFin()));
        }
        if (TextUtils.isEmpty(from.getReservada())) {
            herramienta.setReservada(false);
        } else {
            herramienta.setReservada(from.getReservada().contentEquals("1"));
        }

        return herramienta;
    }


    public HerramientaRest convertFrom(Herramienta from) {
        HerramientaRest herramienta = new HerramientaRest();
        herramienta.setId(from.getId());
        herramienta.setDescripcion(from.getDescripcion());
        herramienta.setIdUsuario(from.getIdUsuario());
        herramienta.setNombreUsuario(from.getNombreUsuario());
        herramienta.setUrlImagen(from.getUrlImagen());
        herramienta.setFechaFin(from.getFechaFinTexto());
        herramienta.setFechaInicio(from.getFechaInicioTexto());
        herramienta.setSimboloMoneda(from.getSimboloMoneda());
        herramienta.setResumen(from.getResumen());
        herramienta.setMoneda(from.getMoneda());
        herramienta.setPrecio(from.getPrecioText());
        herramienta.setCategoria(from.getCategoria());
        ;
        herramienta.setReservada(from.isReservada() ? "1" : "0");
        return herramienta;
    }
}
