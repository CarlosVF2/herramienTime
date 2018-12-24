package android.com.herramientime.modules.experiencias.view;

import android.com.herramientime.core.view.MvpFragment;

/**
 * Created by carlo on 06/11/2018.
 */

public interface ExperienciaDetalleFragment extends MvpFragment {

    //region SET text

    void setImageExperiencia(String url);

    void setPrecio(String precio);

    void setDescripcion(String descripcion);

    void setIdUsuario(String idUsuario);

    void setCalificacion(float calificacion);

    void setAcerca(String acerca);

    void setResumen(String resumen);

    void onLoadErrorUser(String cause);

    //endregion SET text
}
