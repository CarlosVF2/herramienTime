package android.com.herramientime.injection;

import android.com.herramientime.modules.domain.repository.CategoriasRepository;
import android.com.herramientime.modules.domain.repository.MainActivityRepository;
import android.com.herramientime.modules.domain.repository.MonedasRepository;
import android.com.herramientime.modules.experiencias.repository.AlquilerExperienciaFragmentRepository;
import android.com.herramientime.modules.experiencias.repository.ExperienciaDetalleFragmentRepository;
import android.com.herramientime.modules.experiencias.repository.ExperienciasFragmentRepository;
import android.com.herramientime.modules.herramientas.repository.AlquilerHerramientaFragmentRepository;
import android.com.herramientime.modules.herramientas.repository.HerramientaDetalleFragmentRepository;
import android.com.herramientime.modules.herramientas.repository.HerramientasFragmentRepository;
import android.com.herramientime.modules.login.repository.LoginFragmentRepository;
import android.com.herramientime.modules.map.repository.MapFragmentRepository;
import android.com.herramientime.modules.reservar.repository.ReservaFragmentRepository;
import android.com.herramientime.modules.usuarios.repository.UsuarioDetalleFragmentRepository;

/**
 * Created by carlos 06/11/2018.
 */

public interface RepositoryFactory {
    //Main
    MainActivityRepository getMainActivityRepository();

    //Herramientas
    HerramientasFragmentRepository getHerramientasFragmentRepository();
    HerramientaDetalleFragmentRepository getHerramientaDetalleFragmentRepository();

    //Experiencias
    ExperienciasFragmentRepository getExperienciasFragmentRepository();
    ExperienciaDetalleFragmentRepository getExperienciaDetalleFragmentRepository();

    //Reservas
    ReservaFragmentRepository getReservaFragmentRepository();

    //Alquileres
    AlquilerHerramientaFragmentRepository getAlquilerHerramientaFragmentRepository();
    AlquilerExperienciaFragmentRepository getAlquilerExperienciaFragmentRepository();

    //Login
    LoginFragmentRepository getLoginFragmentRepository();

    //Map
    MapFragmentRepository getMapFragmentRepository();

    //Categorias helpValues
    CategoriasRepository geCategoriasRepository();

    //Monedas helpValue
    MonedasRepository geMonedasRepository();

    //Usuario Detalle
    UsuarioDetalleFragmentRepository getUsuarioDetalleRepository();
}
