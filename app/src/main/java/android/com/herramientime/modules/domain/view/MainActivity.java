package android.com.herramientime.modules.domain.view;

import android.com.herramientime.core.view.MvpActivity;

/**
 * Created by carlo on 06/11/2018.
 */

public interface MainActivity extends MvpActivity {
    void showMessageExitConfirm();

    void refreshMenu();

    void setNombreUsuarioText(String usuario);

    void setDatosUsuarioVisibility(boolean visibility);
    void setButtonIniciarSesionVisibility(boolean visibility);

    void setIDUsuarioText(String id);

    void closeDrawer();
}
