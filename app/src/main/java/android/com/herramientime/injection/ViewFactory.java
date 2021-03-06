package android.com.herramientime.injection;


import android.com.herramientime.modules.experiencias.view.impl.AlquilarExperienciaFragmentImpl;
import android.com.herramientime.modules.experiencias.view.impl.ExperienciaDetalleFragmentImpl;
import android.com.herramientime.modules.experiencias.view.impl.ExperienciasFragmentImpl;
import android.com.herramientime.modules.herramientas.view.impl.AlquilarHerramientaFragmentImpl;
import android.com.herramientime.modules.herramientas.view.impl.HerramientaDetalleFragmentImpl;
import android.com.herramientime.modules.herramientas.view.impl.HerramientasFragmentImpl;
import android.com.herramientime.modules.login.view.impl.LoginFragmentImpl;
import android.com.herramientime.modules.map.view.impl.MapFragmentImpl;
import android.com.herramientime.modules.reservar.view.impl.ReservaFragmentImpl;
import android.com.herramientime.modules.usuarios.view.impl.UsuarioDetalleFragmentImpl;
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

    //Reservas
    ReservaFragmentImpl newReservaFragmentInstance();

    //Alquiler
    AlquilarHerramientaFragmentImpl newAlquilerHerramientaFragmentInstance();
    AlquilarExperienciaFragmentImpl newAlquilerExperienciaFragmentInstance();

    //Login
    LoginFragmentImpl newLoginFragmentInstance();

    //Map
    MapFragmentImpl newMapFragmentInstance();

    //Usuario detalle
    UsuarioDetalleFragmentImpl newUsuarioDetalleFragmentInstance();

}
