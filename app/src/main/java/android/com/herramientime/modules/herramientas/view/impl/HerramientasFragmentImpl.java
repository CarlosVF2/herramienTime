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
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by carlo on 06/11/2018.
 */

public class HerramientasFragmentImpl
        <PRESENTER extends HerramientasFragmentPresenter> extends MvpFragmentImpl<PRESENTER>
        implements HerramientasFragment, SwipeRefreshLayout.OnRefreshListener, DrawerLayout.DrawerListener {

    private SwipeRefreshLayout swipeRefreshLayoutHerramienta;
    private RecyclerView recyclerViewHerramienta;
    private GridLayoutManager mLayoutManager;
    private HerramientasAdapter herramientasAdapter;
    private DrawerLayout mDrawer;
    private View viewContainer;

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
    //endreigon DrawerListener

    //region RefreshListener
    @Override
    public void onRefresh() {
        getMvpPresenter().onRefresh();
    }
    //endregion RefreshListener

}
