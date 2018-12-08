package android.com.herramientime.injection;

import android.com.herramientime.core.view.MvpFragment;
import android.com.herramientime.injection.impl.NavigationManagerImpl;
import android.com.herramientime.modules.domain.entities.LocalException;

/**
 * Created by carlos 06/11/2018.
 */

public interface NavigationManager {

    //Main
    void navigateToMainActivity();

    //Herramientas
    void navigateToHerramientas() throws LocalException;
    void navigateToDetalleHerramienta(String idHerramienta) throws LocalException;

    //Experiencias
    void navigateToExperiencias() throws LocalException;
    void navigateToDetalleExperiencia(String idHerramienta) throws LocalException;

    //Reserva
    void navigateToReservaExperiencia(String idExperiencia, MvpFragment currentFragment, int requestCode) throws LocalException;
    void navigateToReservaHerramienta(String idHerramienta, MvpFragment currentFragment, int requestCode) throws LocalException;

    //Alquiler
    void navigateToAlquilerHerramienta() throws LocalException;
    void navigateToAlquilerExperiencia() throws LocalException;

    //Login
    void navigateToLogin() throws LocalException;


    //Gestionar ir hacia atrás con Fragment
    void navigateBack() throws LocalException;

    //Setear el listener de la navegación por si se quiere modificar
    void setNavigationListener(NavigationManagerImpl.NavigationListener navigationListener);

    boolean isFragmentAttached() throws LocalException;


    boolean isRootFragment() throws LocalException;
}
