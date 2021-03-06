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

    //Experiencias
    void setupExperienciasFragmentInstance(Bundle args);
    void setupExperienciaDetalleFragmentInstance(Bundle args, String idExperiencia);

    //Reserva
    void setupReservaFragmentInstance(Bundle args, String idExperiencia, String idHerramienta);

    //Alquiler
    void setupAlquilerHerramientaFragmentInstance(Bundle args);
    void setupAlquilerExperienciaFragmentInstance(Bundle args);

    //Login
    void setupLoginFragmentInstance(Bundle args);

    //Map
    void setupMapFragmentInstance(Bundle args);

    //Usuario detalle
    void setupUsuarioDetalleFragmentInstance(Bundle args, String idUsuario);
}
