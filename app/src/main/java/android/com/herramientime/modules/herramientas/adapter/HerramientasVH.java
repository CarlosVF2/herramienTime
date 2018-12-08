package android.com.herramientime.modules.herramientas.adapter;

import android.com.herramientime.modules.herramientas.entities.Herramienta;

public interface HerramientasVH<DATA extends Herramienta> {
    void setNombreHerramienta(String text);

    void setPrecioExperiencia(String text);
    void setReservadaVisibility(boolean visibility);
    void setImagenHerramienta(String urlImagen);
}
