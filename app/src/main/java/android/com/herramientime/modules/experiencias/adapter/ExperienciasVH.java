package android.com.herramientime.modules.experiencias.adapter;

import android.com.herramientime.modules.experiencias.entities.Experiencia;

public interface ExperienciasVH<DATA extends Experiencia> {
    void setDescripcionExperiencia(String text);

    void setPrecioHoraExperiencia(String text);

    void setImagenExperiencia(String urlImagen);
}
