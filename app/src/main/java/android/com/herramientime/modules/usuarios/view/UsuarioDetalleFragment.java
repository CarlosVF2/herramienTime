package android.com.herramientime.modules.usuarios.view;

import android.com.herramientime.core.view.MvpFragment;

/**
 * Created by carlo on 06/11/2018.
 */

public interface UsuarioDetalleFragment extends MvpFragment {
    void setNombreApellidosUser(String text);

    void setUsuario(String text);

    void setAcercaDeTi(String acercaDeTi);
    void setCalificacion(float calificacion);
}
