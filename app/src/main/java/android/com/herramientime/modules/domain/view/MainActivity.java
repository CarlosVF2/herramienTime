package android.com.herramientime.modules.domain.view;

import android.com.herramientime.core.view.MvpActivity;

/**
 * Created by carlo on 06/11/2018.
 */

public interface MainActivity extends MvpActivity {
    void showMessageExitConfirm();

    void refreshMenu();

    void setNombreUsuarioText(String usuario);

    void setIDUsuarioText(String id);

    void setButtonIniciarSesionVisibility(boolean visibility);

    void closeDrawer();
}
