package android.com.herramientime.modules.login.view;

import android.com.herramientime.core.view.MvpFragment;

/**
 * Created by carlo on 06/11/2018.
 */

public interface LoginFragment extends MvpFragment {
    void setNombreVisibility(boolean visibility);

    void setApellidosVisibility(boolean visibility);

    void setButtonIniciarSesionVisibility(boolean visibility);
}
