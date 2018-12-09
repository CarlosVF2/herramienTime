package android.com.herramientime.modules.herramientas.view.impl;

import android.com.herramientime.R;
import android.com.herramientime.core.view.impl.MvpFragmentImpl;
import android.com.herramientime.modules.herramientas.presenter.AlquilerHerramientaFragmentPresenter;
import android.com.herramientime.modules.herramientas.view.AlquilarHerramientaFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by carlo on 06/11/2018.
 */

public class AlquilarHerramientaFragmentImpl
        <PRESENTER extends AlquilerHerramientaFragmentPresenter> extends MvpFragmentImpl<PRESENTER>
        implements AlquilarHerramientaFragment, TextWatcher {

    private TextInputLayout textInputLayoutTitulo;
    private TextInputLayout textInputLayoutDescripcion;
    private TextInputLayout textInputLayoutPrecio;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alquilar_herramienta, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponentes(view);
    }

    private void initComponentes(View view) {
        textInputLayoutTitulo = view.findViewById(R.id.textInputLayoutTitulo);
        textInputLayoutDescripcion = view.findViewById(R.id.textInputLayoutDescripcion);
        textInputLayoutPrecio = view.findViewById(R.id.textInputLayoutPrecio);
        textInputLayoutTitulo.getEditText().addTextChangedListener(this);
        textInputLayoutDescripcion.getEditText().addTextChangedListener(this);
        textInputLayoutPrecio.getEditText().addTextChangedListener(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_alquilar_herramienta, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_confirmar:
                getMvpPresenter().onClickConfirmar();
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

    }

    @Override
    public void onLoadError(String error) {
        super.onLoadError(error);

    }

    //endregion Core LifeCycle

    //region TextWatcher

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        PRESENTER presenter = getMvpPresenter();
        if (presenter != null) {
            if (textInputLayoutTitulo.getEditText().getEditableText() == editable) {
                presenter.setTitulo(editable.toString());
            } else if (textInputLayoutDescripcion.getEditText().getEditableText() == editable) {
                presenter.setDescripcion(editable.toString());
            } else if (textInputLayoutPrecio.getEditText().getEditableText() == editable) {
                presenter.setPrecio(editable.toString());
            }
        }
    }

    //endregion TextWatcher

}
