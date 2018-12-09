package android.com.herramientime.modules.experiencias.view.impl;

import android.com.herramientime.R;
import android.com.herramientime.core.view.impl.MvpFragmentImpl;
import android.com.herramientime.modules.experiencias.adapter.ExperienciasAdapter;
import android.com.herramientime.modules.experiencias.adapter.ExperienciasVHListener;
import android.com.herramientime.modules.experiencias.adapter.impl.ExperienciasAdapterImpl;
import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.herramientime.modules.experiencias.presenter.ExperienciasFragmentPresenter;
import android.com.herramientime.modules.experiencias.view.ExperienciasFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class ExperienciasFragmentImpl
        <PRESENTER extends ExperienciasFragmentPresenter> extends MvpFragmentImpl<PRESENTER>
        implements ExperienciasFragment, SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayoutExperiencia;
    private RecyclerView recyclerViewExperiencia;
    private GridLayoutManager mLayoutManager;
    private ExperienciasAdapter experienciasAdapter;
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_experiencias, container, false);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponentes(view);
    }

    private void initComponentes(View view) {
        swipeRefreshLayoutExperiencia = view.findViewById(R.id.swipeRefreshLayoutExperiencia);
        swipeRefreshLayoutExperiencia.setOnRefreshListener(this);
        recyclerViewExperiencia = view.findViewById(R.id.recyclerViewExperienca);
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerViewExperiencia.setLayoutManager(mLayoutManager);
        setupAdapter();
    }

    private void setupAdapter() {
        if (experienciasAdapter == null) {
            experienciasAdapter = new ExperienciasAdapterImpl(getContext());
            recyclerViewExperiencia.setAdapter((RecyclerView.Adapter) experienciasAdapter);
        }
    }

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
        }
        return super.onOptionsItemSelected(item);
    }

    //region Core LifeCycle

    @Override
    public void onInitLoading() {
        recyclerViewExperiencia.setVisibility(View.GONE);
    }

    @Override
    public void onLoaded() {
        recyclerViewExperiencia.setVisibility(View.VISIBLE);

    }

    @Override
    public void onLoadError(String error) {
        super.onLoadError(error);

    }

    //endregion Core LifeCycle

    @Override
    public void setData(List<Experiencia> experiencias, ExperienciasVHListener listener) {
        if (experienciasAdapter != null) {
            experienciasAdapter.setData(experiencias, listener);
        }
    }

    @Override
    public void setRefresh(boolean visibility) {
        swipeRefreshLayoutExperiencia.setRefreshing(visibility);
    }

    //region RefreshListener
    @Override
    public void onRefresh() {
        getMvpPresenter().onRefresh();
    }
    //endregion RefreshListener

}
