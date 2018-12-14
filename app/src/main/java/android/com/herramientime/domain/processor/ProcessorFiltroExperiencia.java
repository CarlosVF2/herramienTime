package android.com.herramientime.domain.processor;

import android.com.herramientime.modules.herramientas.entities.FiltrosExperiencia;
import android.com.rest.entities.FiltrosExperienciaRest;

public class ProcessorFiltroExperiencia {
    public FiltrosExperienciaRest convertFrom(FiltrosExperiencia filtrosExperiencia) {
        FiltrosExperienciaRest rest = new FiltrosExperienciaRest();
        rest.setDescripcion(filtrosExperiencia.getDescripcion());
        rest.setPrecioFinal(filtrosExperiencia.getPrecioFinal());
        rest.setPrecioInicial(filtrosExperiencia.getPrecioInicial());
        return rest;
    }
}
