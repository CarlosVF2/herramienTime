package android.com.herramientime.modules.herramientas.view.impl;

import android.com.herramientime.R;
import android.com.herramientime.core.view.impl.MvpFragmentImpl;
import android.com.herramientime.modules.herramientas.presenter.HerramientaDetalleFragmentPresenter;
import android.com.herramientime.modules.herramientas.view.HerramientaDetalleFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by carlo on 06/11/2018.
 */

public class HerramientaDetalleFragmentImpl
        <PRESENTER extends HerramientaDetalleFragmentPresenter> extends MvpFragmentImpl<PRESENTER>
        implements HerramientaDetalleFragment {

    private ImageView imageViewHerramienta;
    private TextView textViewPrecio;
    private TextView textViewDescripcion;
    private TextView textViewResumen;
    private TextView textViewCategoria;
    private LinearLayout linearLayoutContainer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

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
        imageViewHerramienta = view.findViewById(R.id.imageViewHerramienta);
        textViewPrecio = view.findViewById(R.id.textViewPrecio);
        textViewDescripcion = view.findViewById(R.id.textViewDescripcion);
        textViewResumen = view.findViewById(R.id.textViewResumen);
        textViewCategoria = view.findViewById(R.id.textViewCategoria);
        linearLayoutContainer = view.findViewById(R.id.linearLayoutContainer);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_detalle_herramienta, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_reservar:
                getMvpPresenter().onClickReservar();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //region LifeCycleCore

    @Override
    public void onInitLoading() {
        linearLayoutContainer.setVisibility(View.GONE);
    }

    @Override
    public void onLoaded() {
        linearLayoutContainer.setVisibility(View.VISIBLE);

    }

    //endregion LifeCycleCore

    //region set Text

    @Override
    public void setImageHerramienta(String url) {

        Glide.with(getContext())
                .load(url)
                .into(imageViewHerramienta);
    }

    @Override
    public void setPrecio(String precio) {
        textViewPrecio.setText(precio);
    }

    @Override
    public void setDescripcion(String descripcion) {
        textViewDescripcion.setText(descripcion);
    }

    @Override
    public void setResumen(String resumen) {
        textViewResumen.setText(resumen);
    }

    @Override
    public void setCategoria(String categoria) {
        textViewResumen.setText(categoria);
    }

    //endregion set Text
}
