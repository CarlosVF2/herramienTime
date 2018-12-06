package android.com.herramientime.injection;


import android.content.Intent;
import android.os.Bundle;

/**
 * Created by carlos 06/11/2018.
 */

public interface PresenterFactory {

    //Main
    void setupMainActivityIntent(Intent intent);

    //Herramientas
    void setupHerramientasFragmentInstance(Bundle args);
    void setupHerramientaDetalleFragmentInstance(Bundle args, String idHerramienta);
}
