package android.com.herramientime.modules.herramientas.view;

import android.com.herramientime.core.view.MvpFragment;

/**
 * Created by carlo on 06/11/2018.
 */

public interface HerramientaDetalleFragment extends MvpFragment {

    //region SET text

    void setImageHerramienta(String url);

    void setPrecio(String precio);

    void setDescripcion(String descripcion);

    void setResumen(String resumen);

    void setCategoria(String categoria);

    //endregion SET text
}
