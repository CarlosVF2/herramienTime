package android.com.herramientime.injection.impl;

import android.com.herramientime.injection.ViewFactory;
import android.com.herramientime.modules.domain.view.impl.MainActivityImpl;
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
import android.content.Context;
import android.content.Intent;

/**
 * Created by carlos 06/11/2018.
 */
public class ViewFactoryImpl implements ViewFactory {

    private Context context;

    public ViewFactoryImpl(Context context) {
        this.context = context;
    }

    @Override
    public Intent getMainActivityIntent() {
        Intent intent = new Intent(context, MainActivityImpl.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    @Override
    public HerramientasFragmentImpl newHerramientasFragmentInstance() {
        HerramientasFragmentImpl fragment = new HerramientasFragmentImpl();
        return fragment;
    }

    @Override
    public HerramientaDetalleFragmentImpl newHerramientaDetalleFragmentInstance() {
        HerramientaDetalleFragmentImpl fragment = new HerramientaDetalleFragmentImpl();
        return fragment;
    }

    @Override
    public ExperienciasFragmentImpl newExperienciasFragmentInstance() {
        ExperienciasFragmentImpl fragment = new ExperienciasFragmentImpl();
        return fragment;
    }

    @Override
    public ExperienciaDetalleFragmentImpl newExperienciaDetalleFragmentInstance() {
        ExperienciaDetalleFragmentImpl fragment = new ExperienciaDetalleFragmentImpl();
        return fragment;
    }

    @Override
    public ReservaFragmentImpl newReservaFragmentInstance() {
        ReservaFragmentImpl fragment = new ReservaFragmentImpl();
        return fragment;
    }

    @Override
    public AlquilarHerramientaFragmentImpl newAlquilerHerramientaFragmentInstance() {
        AlquilarHerramientaFragmentImpl fragment = new AlquilarHerramientaFragmentImpl();
        return fragment;
    }

    @Override
    public AlquilarExperienciaFragmentImpl newAlquilerExperienciaFragmentInstance() {
        AlquilarExperienciaFragmentImpl fragment = new AlquilarExperienciaFragmentImpl();
        return fragment;
    }

    @Override
    public LoginFragmentImpl newLoginFragmentInstance() {
        LoginFragmentImpl fragment = new LoginFragmentImpl();
        return fragment;
    }

    @Override
    public MapFragmentImpl newMapFragmentInstance() {
        MapFragmentImpl fragment = new MapFragmentImpl();
        return fragment;
    }

    @Override
    public UsuarioDetalleFragmentImpl newUsuarioDetalleFragmentInstance() {
        UsuarioDetalleFragmentImpl fragment = new UsuarioDetalleFragmentImpl();
        return fragment;
    }
}
