package android.com.herramientime.injection.impl;

import android.com.herramientime.injection.ViewFactory;
import android.com.herramientime.modules.domain.view.impl.MainActivityImpl;
import android.com.herramientime.modules.herramientas.view.impl.HerramientaDetalleFragmentImpl;
import android.com.herramientime.modules.herramientas.view.impl.HerramientasFragmentImpl;
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
}
