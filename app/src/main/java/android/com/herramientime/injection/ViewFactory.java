package android.com.herramientime.injection;


import android.com.herramientime.modules.herramientas.view.impl.HerramientasFragmentImpl;
import android.content.Intent;

/**
 * Created by carlos 06/11/2018.
 */

public interface ViewFactory {

    //HerramientasFragment
    Intent getMainActivityIntent();

    //Herramientas
    HerramientasFragmentImpl newHerramientasFragmentInstance();
}
