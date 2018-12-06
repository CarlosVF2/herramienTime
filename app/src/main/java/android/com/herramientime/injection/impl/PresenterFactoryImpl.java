package android.com.herramientime.injection.impl;


import android.com.herramientime.injection.PresenterFactory;
import android.com.herramientime.modules.domain.presenter.impl.MainActivityPresenterImpl;
import android.com.herramientime.modules.herramientas.presenter.impl.HerramientaDetalleFragmentPresenterImpl;
import android.com.herramientime.modules.herramientas.presenter.impl.HerramientasFragmentPresenterImpl;
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
}
