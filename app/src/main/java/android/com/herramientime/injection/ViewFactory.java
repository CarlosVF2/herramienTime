package android.com.herramientime.injection;


import android.com.herramientime.modules.experiencias.view.impl.ExperienciaDetalleFragmentImpl;
import android.com.herramientime.modules.experiencias.view.impl.ExperienciasFragmentImpl;
import android.com.herramientime.modules.herramientas.view.impl.HerramientaDetalleFragmentImpl;
import android.com.herramientime.modules.herramientas.view.impl.HerramientasFragmentImpl;
import android.content.Intent;

/**
 * Created by carlos 06/11/2018.
 */

public interface ViewFactory {

    //ExperienciasFragment
    Intent getMainActivityIntent();

    //Herramientas
    HerramientasFragmentImpl newHerramientasFragmentInstance();
    HerramientaDetalleFragmentImpl newHerramientaDetalleFragmentInstance();

    //Experiencias
    ExperienciasFragmentImpl newExperienciasFragmentInstance();

    ExperienciaDetalleFragmentImpl newExperienciaDetalleFragmentInstance();
}
