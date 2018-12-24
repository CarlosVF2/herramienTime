package android.com.herramientime.modules.experiencias.view.impl;

import android.com.herramientime.R;
import android.com.herramientime.core.view.impl.MvpFragmentImpl;
import android.com.herramientime.modules.experiencias.presenter.ExperienciaDetalleFragmentPresenter;
import android.com.herramientime.modules.experiencias.view.ExperienciaDetalleFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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
    private RatingBar ratingBarCalificacion;
    private TextView textViewAcerca;
    private TextView textViewIdUsuario;
    private RelativeLayout relativeLayoutContainer;

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
        ratingBarCalificacion = view.findViewById(R.id.ratingBarCalificacion);
        textViewAcerca = view.findViewById(R.id.textViewAcerca);
        textViewIdUsuario = view.findViewById(R.id.textViewIdUsuario);
        relativeLayoutContainer = view.findViewById(R.id.relativeLayoutContainer);
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
                getMvpPresenter().onClickReservar();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //region LifeCycleCore

    @Override
    public void onInitLoading() {
        relativeLayoutContainer.setVisibility(View.GONE);
    }

    @Override
    public void onLoaded() {
        relativeLayoutContainer.setVisibility(View.VISIBLE);
        hideProgressDialog();
    }

    //endregion LifeCycleCore

    //region set Text

    @Override
    public void setImageExperiencia(String url) {
        //Glide.with(getContext())
        //        .load(url)
        //        .into(imageViewExperiencia);
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
    public void setIdUsuario(String idUsuario) {
        textViewIdUsuario.setText(idUsuario);
    }

    @Override
    public void setCalificacion(float calificacion) {
        ratingBarCalificacion.setRating(calificacion);
    }

    @Override
    public void setAcerca(String acerca) {
        textViewAcerca.setText(acerca);
    }

    @Override
    public void setResumen(String resumen) {
        textViewResumen.setText(resumen);
    }

    @Override
    public void onLoadErrorUser(String cause) {

        new AlertDialog.Builder(getContext())
                .setTitle(getString(R.string.title_dialog_atencion))
                .setMessage(cause)
                .setPositiveButton(getString(R.string.prompt_aceptar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        PRESENTER presenter = getMvpPresenter();
                        if (presenter != null) {
                            presenter.onClickAceptarLogin();
                        }

                    }
                }).setNegativeButton(getString(R.string.prompt_cancelar), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //NOTHING TO DO

            }
        })
                .create().show();

    }

    //endregion set Text
}
