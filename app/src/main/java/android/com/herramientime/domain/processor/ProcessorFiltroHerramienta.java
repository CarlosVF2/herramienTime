package android.com.herramientime.domain.processor;

import android.com.herramientime.modules.herramientas.entities.FiltrosHerramientas;
import android.com.rest.entities.FiltrosHerramientasRest;

public class ProcessorFiltroHerramienta {
    public FiltrosHerramientasRest convertFrom(FiltrosHerramientas filtrosHerramientas) {
        FiltrosHerramientasRest rest = new FiltrosHerramientasRest();
        rest.setDescripcion(filtrosHerramientas.getDescripcion());
        rest.setPrecioFinal(filtrosHerramientas.getPrecioFinal());
        rest.setPrecioInicial(filtrosHerramientas.getPrecioInicial());
        return rest;
    }
}
