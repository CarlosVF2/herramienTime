package android.com.herramientime.modules.experiencias.view.impl;

import android.com.herramientime.R;
import android.com.herramientime.core.view.impl.MvpFragmentImpl;
import android.com.herramientime.modules.experiencias.presenter.AlquilerExperienciaFragmentPresenter;
import android.com.herramientime.modules.experiencias.view.AlquilarExperienciaFragment;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

/**
 * Created by carlo on 06/11/2018.
 */

public class AlquilarExperienciaFragmentImpl
        <PRESENTER extends AlquilerExperienciaFragmentPresenter> extends MvpFragmentImpl<PRESENTER>
        implements AlquilarExperienciaFragment, TextWatcher, AdapterView.OnItemSelectedListener {

    private TextInputLayout textInputLayoutTitulo;
    private TextInputLayout textInputLayoutDescripcion;
    private TextInputLayout textInputLayoutPrecio;
    private Spinner spinnerSimbolos;
    private ArrayAdapter<String> adapterSpinnerSimbolos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alquilar_experiencia, container, false);
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
        spinnerSimbolos = view.findViewById(R.id.spinnerSimbolos);
        spinnerSimbolos.setOnItemSelectedListener(this);
        textInputLayoutTitulo.getEditText().addTextChangedListener(this);
        textInputLayoutDescripcion.getEditText().addTextChangedListener(this);
        textInputLayoutPrecio.getEditText().addTextChangedListener(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_alquilar_experiencia, menu);
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

    @Override
    public void setAdapterSpinnerMoneda(List<String> valuesAdapterMoneda) {
        if (adapterSpinnerSimbolos == null) {
            String[] valores = valuesAdapterMoneda.toArray(new String[valuesAdapterMoneda.size()]);
            adapterSpinnerSimbolos = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, valores);
        }
        spinnerSimbolos.setAdapter(adapterSpinnerSimbolos);
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

    //region OnItemSelectedListener

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int id = adapterView.getId();
        switch (id) {
            case R.id.spinnerSimbolos:
                getMvpPresenter().onItemSimbolosSelected(i);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //endregion OnItemSelectedListener

}
