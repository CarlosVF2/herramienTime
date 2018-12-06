package android.com.herramientime.modules.herramientas.adapter;

import android.com.herramientime.modules.herramientas.entities.Herramienta;

import java.util.List;

public interface HerramientasAdapter<MODEL extends Herramienta> {
    void setData(List<MODEL> herramientas, HerramientasVHListener listener);
}
