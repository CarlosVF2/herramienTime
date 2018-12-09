package android.com.herramientime.injection.impl;


import android.com.herramientime.injection.PresenterFactory;
import android.com.herramientime.modules.domain.presenter.impl.MainActivityPresenterImpl;
import android.com.herramientime.modules.experiencias.presenter.impl.AlquilerExperienciaFragmentPresenterImpl;
import android.com.herramientime.modules.experiencias.presenter.impl.ExperienciaDetalleFragmentPresenterImpl;
import android.com.herramientime.modules.experiencias.presenter.impl.ExperienciasFragmentPresenterImpl;
import android.com.herramientime.modules.herramientas.presenter.impl.AlquilerHerramientaFragmentPresenterImpl;
import android.com.herramientime.modules.herramientas.presenter.impl.HerramientaDetalleFragmentPresenterImpl;
import android.com.herramientime.modules.herramientas.presenter.impl.HerramientasFragmentPresenterImpl;
import android.com.herramientime.modules.login.presenter.impl.LoginFragmentPresenterImpl;
import android.com.herramientime.modules.map.presenter.impl.MapFragmentPresenterImpl;
import android.com.herramientime.modules.reservar.presenter.impl.ReservaFragmentPresenterImpl;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by carlos 06/11/2018.
 */
public class PresenterFactoryImpl implements PresenterFactory {


    @Override
    public void setupMainActivityIntent(Intent intent) {
        MainActivityPresenterImpl.newMainActivityPresenterInstance(intent);
    }

    @Override
    public void setupHerramientasFragmentInstance(Bundle args) {
        HerramientasFragmentPresenterImpl.newHerramientasFragmentPresenterInstance(args);
    }

    @Override
    public void setupHerramientaDetalleFragmentInstance(Bundle args, String idHerramienta) {
        HerramientaDetalleFragmentPresenterImpl.newHerramientaDetalleFragmentPresenterInstance(args, idHerramienta);
    }

    @Override
    public void setupExperienciasFragmentInstance(Bundle args) {
        ExperienciasFragmentPresenterImpl.newExperienciasFragmentPresenterInstance(args);
    }

    @Override
    public void setupExperienciaDetalleFragmentInstance(Bundle args, String idExperiencia) {
        ExperienciaDetalleFragmentPresenterImpl.newExperienciaDetalleFragmentPresenterInstance(args, idExperiencia);
    }

    @Override
    public void setupReservaFragmentInstance(Bundle args, String idExperiencia, String idHerramienta) {
        ReservaFragmentPresenterImpl.newReservaFragmentPresenterInstance(args, idExperiencia, idHerramienta);
    }

    @Override
    public void setupAlquilerHerramientaFragmentInstance(Bundle args) {
        AlquilerHerramientaFragmentPresenterImpl.newAlquilerHerramientaFragmentPresenterInstance(args);
    }

    @Override
    public void setupAlquilerExperienciaFragmentInstance(Bundle args) {
        AlquilerExperienciaFragmentPresenterImpl.newAlquilerExperienciaFragmentPresenterInstance(args);
    }

    @Override
    public void setupLoginFragmentInstance(Bundle args) {
        LoginFragmentPresenterImpl.newLoginFragmentPresenterInstance(args);
    }

    @Override
    public void setupMapFragmentInstance(Bundle args) {
        MapFragmentPresenterImpl.newMapFragmentPresenterInstance(args, null);
    }
}
