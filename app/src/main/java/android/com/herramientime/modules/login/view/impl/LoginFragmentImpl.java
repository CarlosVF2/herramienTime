package android.com.herramientime.modules.login.view.impl;

import android.com.herramientime.R;
import android.com.herramientime.core.view.impl.MvpFragmentImpl;
import android.com.herramientime.modules.login.presenter.LoginFragmentPresenter;
import android.com.herramientime.modules.login.view.LoginFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by carlo on 06/11/2018.
 */

public class LoginFragmentImpl
        <PRESENTER extends LoginFragmentPresenter> extends MvpFragmentImpl<PRESENTER>
        implements LoginFragment, TextWatcher, View.OnClickListener {

    private TextInputLayout textInputLayoutNombre;
    private TextInputLayout textInputLayoutApellidos;
    private TextInputLayout textInputLayoutUsuario;
    private TextInputLayout textInputLayoutPassword;
    private Button buttonIniciarSesion;
    private Button buttonRegistrarse;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponentes(view);
    }

    private void initComponentes(View view) {
        textInputLayoutNombre = view.findViewById(R.id.textInputLayoutNombre);
        textInputLayoutApellidos = view.findViewById(R.id.textInputLayoutApellidos);
        textInputLayoutUsuario = view.findViewById(R.id.textInputLayoutUsuario);
        textInputLayoutPassword = view.findViewById(R.id.textInputLayoutPassword);
        textInputLayoutNombre.getEditText().addTextChangedListener(this);
        textInputLayoutApellidos.getEditText().addTextChangedListener(this);
        textInputLayoutUsuario.getEditText().addTextChangedListener(this);
        textInputLayoutPassword.getEditText().addTextChangedListener(this);
        buttonIniciarSesion = view.findViewById(R.id.buttonIniciarSesion);
        buttonIniciarSesion.setOnClickListener(this);
        buttonRegistrarse = view.findViewById(R.id.buttonRegistrarse);
        buttonRegistrarse.setOnClickListener(this);
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
            if (textInputLayoutNombre.getEditText().getEditableText() == editable) {
                presenter.setNombre(editable.toString());
            } else if (textInputLayoutApellidos.getEditText().getEditableText() == editable) {
                presenter.setApellidos(editable.toString());
            } else if (textInputLayoutUsuario.getEditText().getEditableText() == editable) {
                presenter.setUsuario(editable.toString());
            } else if (textInputLayoutPassword.getEditText().getEditableText() == editable) {
                presenter.setPassword(editable.toString());
            }
        }

    }
    //endregion TextWatcher

    //region OnClickListener
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.buttonIniciarSesion:
                getMvpPresenter().onClickIniciarSesion();
                break;
            case R.id.buttonRegistrarse:
                getMvpPresenter().onClickRegistrarse();
                break;
        }

    }
    //endregion OnClickListener
}
