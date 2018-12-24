package android.com.herramientime.modules.usuarios.view.impl;

import android.com.herramientime.R;
import android.com.herramientime.core.view.impl.MvpFragmentImpl;
import android.com.herramientime.modules.usuarios.presenter.UsuarioDetalleFragmentPresenter;
import android.com.herramientime.modules.usuarios.view.UsuarioDetalleFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

/**
 * Created by carlo on 06/11/2018.
 */

public class UsuarioDetalleFragmentImpl
        <PRESENTER extends UsuarioDetalleFragmentPresenter> extends MvpFragmentImpl<PRESENTER>
        implements UsuarioDetalleFragment {

    private TextInputLayout textInputLayoutNombreApellidos;
    private TextInputLayout textInputLayoutUsuario;
    private TextInputLayout textInputLayoutAcercaDeTi;
    private RatingBar ratingBarCalificacion;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

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
        textInputLayoutNombreApellidos = view.findViewById(R.id.textInputLayoutNombreApellidos);
        textInputLayoutUsuario = view.findViewById(R.id.textInputLayoutUsuario);
        textInputLayoutAcercaDeTi = view.findViewById(R.id.textInputLayoutAcercaDeTi);
        ratingBarCalificacion = view.findViewById(R.id.ratingBarCalificacion);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_detalle_usuario, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_cerrar_sesion:
                getMvpPresenter().onClickCerrarSesion();
                return true;
        }
        return super.onOptionsItemSelected(item);
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

    //region SET

    @Override
    public void setNombreApellidosUser(String text) {
        textInputLayoutNombreApellidos.getEditText().setText(text);
    }

    @Override
    public void setUsuario(String text) {
        textInputLayoutUsuario.getEditText().setText(text);
    }

    @Override
    public void setAcercaDeTi(String acercaDeTi) {
        textInputLayoutAcercaDeTi.getEditText().setText(acercaDeTi);
    }

    @Override
    public void setCalificacion(float calificacion) {
        ratingBarCalificacion.setRating(calificacion);
    }

    //endregion SEt
}
