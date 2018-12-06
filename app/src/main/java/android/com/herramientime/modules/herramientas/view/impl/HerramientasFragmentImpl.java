package android.com.herramientime.modules.herramientas.view.impl;

import android.com.herramientime.R;
import android.com.herramientime.core.view.impl.MvpFragmentImpl;
import android.com.herramientime.modules.herramientas.adapter.HerramientasVHListener;
import android.com.herramientime.modules.herramientas.adapter.HerramientasAdapter;
import android.com.herramientime.modules.herramientas.adapter.impl.HerramientasAdapterImpl;
import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.herramientime.modules.herramientas.presenter.HerramientasFragmentPresenter;
import android.com.herramientime.modules.herramientas.view.HerramientasFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by carlo on 06/11/2018.
 */

public class HerramientasFragmentImpl
        <PRESENTER extends HerramientasFragmentPresenter> extends MvpFragmentImpl<PRESENTER>
        implements HerramientasFragment, SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayoutHerramienta;
    private RecyclerView recyclerViewHerramienta;
    private GridLayoutManager mLayoutManager;
    private HerramientasAdapter herramientasAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_herramientas, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponentes(view);
    }

    private void initComponentes(View view) {
        swipeRefreshLayoutHerramienta = view.findViewById(R.id.swipeRefreshLayoutHerramienta);
        swipeRefreshLayoutHerramienta.setOnRefreshListener(this);
        recyclerViewHerramienta = view.findViewById(R.id.recyclerViewHerramienta);
        mLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerViewHerramienta.setLayoutManager(mLayoutManager);
        setupAdapter();
    }

    private void setupAdapter() {
        if(herramientasAdapter == null){
            herramientasAdapter = new HerramientasAdapterImpl(getContext());
            recyclerViewHerramienta.setAdapter((RecyclerView.Adapter) herramientasAdapter);
        }
    }

    //region Core LifeCycle

    @Override
    public void onInitLoading() {
        recyclerViewHerramienta.setVisibility(View.GONE);
    }

    @Override
    public void onLoaded() {
        recyclerViewHerramienta.setVisibility(View.VISIBLE);

    }

    @Override
    public void onLoadError(String error) {
        recyclerViewHerramienta.setVisibility(View.GONE);

    }

    //endregion Core LifeCycle

    @Override
    public void setData(List<Herramienta> herramientas, HerramientasVHListener listener) {
        if(herramientasAdapter != null){
            herramientasAdapter.setData(herramientas, listener);
        }
    }

    @Override
    public void setRefresh(boolean visibility) {
        swipeRefreshLayoutHerramienta.setRefreshing(visibility);
    }

    //region RefreshListener
    @Override
    public void onRefresh() {
        getMvpPresenter().onRefresh();
    }
    //endregion RefreshListener

}
