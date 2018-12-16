package android.com.herramientime.modules.usuarios.view.impl;

import android.com.herramientime.R;
import android.com.herramientime.core.view.impl.MvpFragmentImpl;
import android.com.herramientime.modules.usuarios.presenter.UsuarioDetalleFragmentPresenter;
import android.com.herramientime.modules.usuarios.view.UsuarioDetalleFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by carlo on 06/11/2018.
 */

public class UsuarioDetalleFragmentImpl
        <PRESENTER extends UsuarioDetalleFragmentPresenter> extends MvpFragmentImpl<PRESENTER>
        implements UsuarioDetalleFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_usuario_detalle, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponentes(view);
    }

    private void initComponentes(View view) {
    }

    //region Core LifeCycle

    @Override
    public void onInitLoading() {

    }

    @Override
    public void onLoaded() {
        hideProgressDialog();
    }

    //endregion Core LifeCycle
}
