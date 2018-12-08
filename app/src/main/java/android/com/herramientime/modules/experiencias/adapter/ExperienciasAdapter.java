package android.com.herramientime.modules.experiencias.adapter;

import android.com.herramientime.modules.experiencias.entities.Experiencia;

import java.util.List;

public interface ExperienciasAdapter<MODEL extends Experiencia> {
    void setData(List<MODEL> herramientas, ExperienciasVHListener listener);
}
