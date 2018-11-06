package android.com.herramientime.injection;

import android.com.herramientime.injection.impl.NavigationManagerImpl;
import android.com.herramientime.modules.domain.entities.LocalException;

/**
 * Created by carlos 06/11/2018.
 */

public interface NavigationManager {

    //Main
    void navigateToMainActivity();

    //Gestionar ir hacia atrás con Fragment
    void navigateBack() throws LocalException;

    //Setear el listener de la navegación por si se quiere modificar
    void setNavigationListener(NavigationManagerImpl.NavigationListener navigationListener);

}
