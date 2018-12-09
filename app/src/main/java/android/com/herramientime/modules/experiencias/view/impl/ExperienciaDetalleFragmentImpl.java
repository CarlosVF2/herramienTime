package android.com.herramientime.modules.experiencias.view.impl;

import android.com.herramientime.R;
import android.com.herramientime.core.view.impl.MvpFragmentImpl;
import android.com.herramientime.modules.experiencias.presenter.ExperienciaDetalleFragmentPresenter;
import android.com.herramientime.modules.experiencias.view.ExperienciaDetalleFragment;
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
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by carlo on 06/11/2018.
 */

public class ExperienciaDetalleFragmentImpl
        <PRESENTER extends ExperienciaDetalleFragmentPresenter> extends MvpFragmentImpl<PRESENTER>
        implements ExperienciaDetalleFragment {

    private ImageView imageViewExperiencia;
    private TextView textViewPrecio;
    private TextView textViewDescripcion;
    private TextView textViewResumen;
    private LinearLayout linearLayoutContainer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle_experiencia, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponentes(view);
    }

    private void initComponentes(View view) {
        imageViewExperiencia = view.findViewById(R.id.imageViewExperiencia);
        textViewPrecio = view.findViewById(R.id.textViewPrecio);
        textViewDescripcion = view.findViewById(R.id.textViewDescripcion);
        textViewResumen = view.findViewById(R.id.textViewResumen);
        linearLayoutContainer = view.findViewById(R.id.linearLayoutContainer);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_detalle_experiencia, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_reservar:
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

    @Override
    public void onLoadError(String error) {
        super.onLoadError(error);

    }

    //endregion LifeCycleCore

    //region set Text

    @Override
    public void setImageExperiencia(String url) {
        Glide.with(getContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(imageViewExperiencia);
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

    //endregion set Text
}
