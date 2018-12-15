package android.com.herramientime.modules.herramientas.view.impl;

import android.com.herramientime.R;
import android.com.herramientime.core.view.impl.MvpFragmentImpl;
import android.com.herramientime.modules.herramientas.adapter.HerramientasAdapter;
import android.com.herramientime.modules.herramientas.adapter.HerramientasVHListener;
import android.com.herramientime.modules.herramientas.adapter.impl.HerramientasAdapterImpl;
import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.herramientime.modules.herramientas.presenter.HerramientasFragmentPresenter;
import android.com.herramientime.modules.herramientas.view.HerramientasFragment;
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

public class HerramientasFragmentImpl
        <PRESENTER extends HerramientasFragmentPresenter> extends MvpFragmentImpl<PRESENTER>
        implements HerramientasFragment, SwipeRefreshLayout.OnRefreshListener, DrawerLayout.DrawerListener, View.OnClickListener {

    private SwipeRefreshLayout swipeRefreshLayoutHerramienta;
    private RecyclerView recyclerViewHerramienta;
    private GridLayoutManager mLayoutManager;
    private HerramientasAdapter herramientasAdapter;
    private DrawerLayout mDrawer;
    private View viewContainer;
    //Controles perteneciente a los filtros
    private TextInputLayout textInputLayoutDescripcion;
    private TextInputLayout textInputLayoutPrecioInicial;
    private TextInputLayout textInputLayoutPrecioFinal;
    private Button buttonAplicar;
    private Button buttonRestaurar;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewContainer = inflater.inflate(R.layout.fragment_herramientas, container, false);
        setupAdapter();
        return viewContainer;
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponentes(view);
    }

    private void initComponentes(View view) {
        mDrawer = view.findViewById(R.id.drawerLayout);
        swipeRefreshLayoutHerramienta = view.findViewById(R.id.swipeRefreshLayoutHerramienta);
        swipeRefreshLayoutHerramienta.setOnRefreshListener(this);
        recyclerViewHerramienta = view.findViewById(R.id.recyclerViewHerramienta);
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerViewHerramienta.setLayoutManager(mLayoutManager);
        recyclerViewHerramienta.setAdapter((RecyclerView.Adapter) herramientasAdapter);
        textInputLayoutDescripcion = view.findViewById(R.id.textInputLayoutDescripcion);
        textInputLayoutPrecioInicial = view.findViewById(R.id.textInputLayoutPrecioInicial);
        textInputLayoutPrecioFinal = view.findViewById(R.id.textInputLayoutPrecioFinal);
        buttonAplicar = view.findViewById(R.id.buttonAplicar);
        buttonAplicar.setOnClickListener(this);
        buttonRestaurar = view.findViewById(R.id.buttonRestaurar);
        buttonRestaurar.setOnClickListener(this);
        setupAdapter();
    }

    private void setupAdapter() {
        if (herramientasAdapter == null) {
            herramientasAdapter = new HerramientasAdapterImpl(getContext());
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_herramientas, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_map:
                getMvpPresenter().onClickMap();
                return true;
            case R.id.action_subir_herramienta:
                getMvpPresenter().onClickSubirHerramienta();
                return true;
            case R.id.action_filter:
                getMvpPresenter().onClickFilter();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //region Core LifeCycle

    @Override
    public void onInitLoading() {
        recyclerViewHerramienta.setVisibility(View.GONE);
    }

    @Override
    public void onLoaded() {
        recyclerViewHerramienta.setVisibility(View.VISIBLE);
        hideProgressDialog();
    }

    //endregion Core LifeCycle

    @Override
    public void setData(List<Herramienta> herramientas, HerramientasVHListener listener) {
        if (herramientasAdapter != null) {
            herramientasAdapter.setData(herramientas, listener);
        }
    }

    @Override
    public void setRefresh(boolean visibility) {
        swipeRefreshLayoutHerramienta.setRefreshing(visibility);
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

    @Override
    public void toggleDrawer() {
        if (mDrawer.isDrawerOpen(GravityCompat.END)) {
            mDrawer.closeDrawer(GravityCompat.END, true);
        } else {
            mDrawer.openDrawer(GravityCompat.END, true);
        }
    }

    private void hideKeyboard() {
        Utilidades.hideKeyBoard(getContext(), viewContainer);
    }

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
    //endregion DrawerListener

    //region RefreshListener
    @Override
    public void onRefresh() {
        getMvpPresenter().onRefresh();
    }
    //endregion RefreshListener

    //region OnClick
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
    //endregion OnClick

}
