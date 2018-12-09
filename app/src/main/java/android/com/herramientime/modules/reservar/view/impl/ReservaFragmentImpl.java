package android.com.herramientime.modules.reservar.view.impl;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.com.herramientime.R;
import android.com.herramientime.core.view.impl.MvpFragmentImpl;
import android.com.herramientime.modules.reservar.presenter.ReservaFragmentPresenter;
import android.com.herramientime.modules.reservar.view.ReservaFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by carlo on 06/11/2018.
 */

public class ReservaFragmentImpl
        <PRESENTER extends ReservaFragmentPresenter> extends MvpFragmentImpl<PRESENTER>
        implements ReservaFragment, View.OnClickListener {

    private TextInputLayout textInputLayoutHerramienta;
    private TextInputLayout textInputLayoutFechaInicial;
    private TextInputLayout textInputLayoutFechaFinal;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reserva, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponentes(view);
    }

    private void initComponentes(View view) {
        textInputLayoutHerramienta = view.findViewById(R.id.textInputLayoutHerramienta);
        textInputLayoutFechaInicial = view.findViewById(R.id.textInputLayoutFechaInicial);
        textInputLayoutFechaInicial.getEditText().setOnClickListener(this);
        textInputLayoutFechaFinal = view.findViewById(R.id.textInputLayoutFechaFinal);
        textInputLayoutFechaFinal.getEditText().setOnClickListener(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_reserva, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_confirmar:
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

    //endregion Core LifeCycle

    @Override
    public void showDatePickerFechaInicial(Calendar fechaElegida) {

        DatePickerDialog recogerFecha = new DatePickerDialog(getContext(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar res = Calendar.getInstance();
                res.set(year, month, dayOfMonth);
                PRESENTER presenter = getMvpPresenter();
                if (presenter != null) {
                    presenter.onFechaInicialSelected(res.getTime());
                }

            }
        }, fechaElegida.get(Calendar.YEAR), fechaElegida.get(Calendar.MONTH), fechaElegida.get(Calendar.DAY_OF_MONTH));

        recogerFecha.setButton(DialogInterface.BUTTON_NEUTRAL, getString(R.string.borrar), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (which == DialogInterface.BUTTON_NEUTRAL) {
                    // Do Stuff
                    PRESENTER presenter = getMvpPresenter();
                    if (presenter != null) {
                        presenter.onFechaInicialSelected(null);
                    }
                }
            }
        });
        recogerFecha.setTitle(getResources().getString(R.string.prompt_fecha_inicial));
        recogerFecha.show();
    }

    @Override
    public void showDatePickerFechaFinal(Calendar fechaElegida) {

        DatePickerDialog recogerFecha = new DatePickerDialog(getContext(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar res = Calendar.getInstance();
                res.set(year, month, dayOfMonth);
                PRESENTER presenter = getMvpPresenter();
                if (presenter != null) {
                    presenter.onFechaFinalSelected(res.getTime());
                }

            }
        }, fechaElegida.get(Calendar.YEAR), fechaElegida.get(Calendar.MONTH), fechaElegida.get(Calendar.DAY_OF_MONTH));

        recogerFecha.setButton(DialogInterface.BUTTON_NEUTRAL, getString(R.string.borrar), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (which == DialogInterface.BUTTON_NEUTRAL) {
                    PRESENTER presenter = getMvpPresenter();
                    if (presenter != null) {
                        presenter.onFechaFinalSelected(null);
                    }
                }
            }
        });
        recogerFecha.setTitle(getResources().getString(R.string.prompt_fecha_final));
        recogerFecha.show();

    }

    @Override
    public void setFechaInicial(String fechaInicial) {
        textInputLayoutFechaInicial.getEditText().setText(fechaInicial);
    }

    @Override
    public void setFechaFinal(String fechaFinal) {
        textInputLayoutFechaFinal.getEditText().setText(fechaFinal);
    }

    @Override
    public void setDescripcionText(String descripcion) {
        textInputLayoutHerramienta.getEditText().setText(descripcion);
    }

    @Override
    public void setHintText(String hint) {
        textInputLayoutHerramienta.setHint(hint);
    }

    //region OnClickListener
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.textInputEditTextFechaInicial:
                getMvpPresenter().onClickFechaInicial();
                break;
            case R.id.textInputEditTextFechaFinal:
                getMvpPresenter().onClickFechaFinal();
                break;
        }

    }
    //endregion OnClickListener
}
