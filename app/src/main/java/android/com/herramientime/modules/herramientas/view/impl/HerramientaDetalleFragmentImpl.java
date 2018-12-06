package android.com.herramientime.modules.herramientas.view.impl;

import android.com.herramientime.R;
import android.com.herramientime.core.view.impl.MvpFragmentImpl;
import android.com.herramientime.modules.herramientas.presenter.HerramientaDetalleFragmentPresenter;
import android.com.herramientime.modules.herramientas.presenter.HerramientasFragmentPresenter;
import android.com.herramientime.modules.herramientas.view.HerramientaDetalleFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by carlo on 06/11/2018.
 */

public class HerramientaDetalleFragmentImpl
        <PRESENTER extends HerramientaDetalleFragmentPresenter> extends MvpFragmentImpl<PRESENTER>
        implements HerramientaDetalleFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle_herramienta, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponentes(view);
    }

    private void initComponentes(View view) {
    }

    @Override
    public void onInitLoading() {

    }

    @Override
    public void onLoaded() {

    }

    @Override
    public void onLoadError(String error) {

    }
}
