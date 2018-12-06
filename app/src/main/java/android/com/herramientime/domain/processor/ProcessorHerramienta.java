package android.com.herramientime.domain.processor;

import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.rest.entities.HerramientaRest;

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
        return herramienta;
    }
}
