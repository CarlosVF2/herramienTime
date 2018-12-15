package android.com.herramientime.modules.experiencias.view.impl;

import android.com.herramientime.R;
import android.com.herramientime.core.view.impl.MvpFragmentImpl;
import android.com.herramientime.modules.experiencias.adapter.ExperienciasAdapter;
import android.com.herramientime.modules.experiencias.adapter.ExperienciasVHListener;
import android.com.herramientime.modules.experiencias.adapter.impl.ExperienciasAdapterImpl;
import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.herramientime.modules.experiencias.presenter.ExperienciasFragmentPresenter;
import android.com.herramientime.modules.experiencias.view.ExperienciasFragment;
import android.com.rest.utils.Utilidades;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

/**
 * Created by carlo on 06/11/2018.
 */

public class ExperienciasFragmentImpl
        <PRESENTER extends ExperienciasFragmentPresenter> extends MvpFragmentImpl<PRESENTER>
        implements ExperienciasFragment, SwipeRefreshLayout.OnRefreshListener, DrawerLayout.DrawerListener, View.OnClickListener {


    private SwipeRefreshLayout swipeRefreshLayoutExperiencia;
    private View viewContainer;
    private RecyclerView recyclerViewExperiencia;
    private GridLayoutManager mLayoutManager;
    private ExperienciasAdapter experienciasAdapter;
    private DrawerLayout mDrawer;
    //Controles perteneciente a los filtros
    private TextInputLayout textInputLayoutDescripcion;
    private TextInputLayout textInputLayoutPrecioInicial;
    private TextInputLayout textInputLayoutPrecioFinal;
    private Button buttonAplicar;
    private Button buttonRestaurar;

    //region Lifecycle Android
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewContainer = inflater.inflate(R.layout.fragment_experiencias, container, false);
        setupAdapter();
        return viewContainer;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponentes(view);
    }

    private void initComponentes(View view) {
        mDrawer = view.findViewById(R.id.drawerLayout);
        swipeRefreshLayoutExperiencia = view.findViewById(R.id.swipeRefreshLayoutExperiencia);
        swipeRefreshLayoutExperiencia.setOnRefreshListener(this);
        recyclerViewExperiencia = view.findViewById(R.id.recyclerViewExperienca);
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerViewExperiencia.setLayoutManager(mLayoutManager);
        recyclerViewExperiencia.setAdapter((RecyclerView.Adapter) experienciasAdapter);
        textInputLayoutDescripcion = view.findViewById(R.id.textInputLayoutDescripcion);
        textInputLayoutPrecioInicial = view.findViewById(R.id.textInputLayoutPrecioInicial);
        textInputLayoutPrecioFinal = view.findViewById(R.id.textInputLayoutPrecioFinal);
        buttonAplicar = view.findViewById(R.id.buttonAplicar);
        buttonAplicar.setOnClickListener(this);
        buttonRestaurar = view.findViewById(R.id.buttonRestaurar);
        buttonRestaurar.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mDrawer.addDrawerListener(this);
    }

    @Override
    public void onPause() {
        mDrawer.removeDrawerListener(this);
        super.onPause();
    }

    //endregion Lifecycle Android

    private void setupAdapter() {
        if (experienciasAdapter == null) {
            experienciasAdapter = new ExperienciasAdapterImpl(getContext());
        }
    }

    //region Menu

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_experiencias, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_map:
                return true;
            case R.id.action_subir_experiencia:
                getMvpPresenter().onClickActionSubirExperiencia();
                return true;
            case R.id.action_filter:
                getMvpPresenter().onClickFilter();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //endregion Menu

    //region Core LifeCycle

    @Override
    public void onInitLoading() {
        recyclerViewExperiencia.setVisibility(View.GONE);
    }

    @Override
    public void onLoaded() {
        recyclerViewExperiencia.setVisibility(View.VISIBLE);
        hideProgressDialog();
    }

    //endregion Core LifeCycle

    @Override
    public void setData(List<Experiencia> experiencias, ExperienciasVHListener listener) {
        if (experienciasAdapter != null) {
            experienciasAdapter.setData(experiencias, listener);
        }
    }

    @Override
    public void toggleDrawer() {
        if (mDrawer.isDrawerOpen(GravityCompat.END)) {
            mDrawer.closeDrawer(GravityCompat.END, true);
        } else {
            mDrawer.openDrawer(GravityCompat.END, true);
        }
    }

    @Override
    public void setRefresh(boolean visibility) {
        swipeRefreshLayoutExperiencia.setRefreshing(visibility);
    }

    @Override
    public String getDescriptionText() {
        return textInputLayoutDescripcion.getEditText().getText().toString();
    }

    @Override
    public String getPrecioInicialText() {
        return textInputLayoutPrecioInicial.getEditText().getText().toString();
    }

    @Override
    public String getPrecioFinalText() {
        return textInputLayoutPrecioFinal.getEditText().getText().toString();
    }

    @Override
    public void restoreFilters() {
        textInputLayoutDescripcion.getEditText().setText("");
        textInputLayoutPrecioInicial.getEditText().setText("");
        textInputLayoutPrecioFinal.getEditText().setText("");
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

    private void hideKeyboard() {
        Utilidades.hideKeyBoard(getContext(), viewContainer);
    }

    //region RefreshListener
    @Override
    public void onRefresh() {
        getMvpPresenter().onRefresh();
    }
    //endregion RefreshListener

    //region DrawerListener
    @Override
    public void onDrawerSlide(@NonNull View view, float v) {

    }

    @Override
    public void onDrawerOpened(@NonNull View view) {
        hideKeyboard();
    }

    @Override
    public void onDrawerClosed(@NonNull View view) {
        hideKeyboard();
    }

    @Override
    public void onDrawerStateChanged(int i) {

    }
    //endreigon DrawerListener

    //region OnClickListener
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.buttonAplicar:
                getMvpPresenter().onClickAplicarFilter();
                break;
            case R.id.buttonRestaurar:
                getMvpPresenter().onClickRestaurarFilter();
                break;
        }

    }
    //endregion OnClickListener

}
