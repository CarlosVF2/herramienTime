package android.com.herramientime.modules.map.presenter.impl;

import android.com.herramientime.core.presenter.impl.MvpFragmentPresenterImpl;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.modules.map.injection.MapFragmentComponent;
import android.com.herramientime.modules.map.interactor.MapFragmentInteractor;
import android.com.herramientime.modules.map.presenter.MapFragmentPresenter;
import android.com.herramientime.modules.map.view.MapFragment;
import android.content.res.Resources;
import android.os.Bundle;

import com.seidor.core.di.annotations.Inject;

public class MapFragmentPresenterImpl<FRAGMENT extends MapFragment>
        extends MvpFragmentPresenterImpl<FRAGMENT>
        implements MapFragmentPresenter {

    private static final String EXTRA_LIST_IDS = "extra_list_ids";

    private String[] arrayListIds;
    private boolean isDialog;

    //dependences
    private MapFragmentInteractor mapFragmentInteractor;
    private NavigationManager navigationManager;
    private Resources resources;

    //reponses


    public MapFragmentPresenterImpl() {
        super();
    }

    public static void newMapFragmentPresenterInstance(Bundle bundle, String[] arrayListIds) {
        MapFragmentPresenterImpl presenter = new MapFragmentPresenterImpl();
        presenter.setupDependencies(bundle, arrayListIds);
        presenter.setArguments(bundle);
    }

    protected void setupDependencies(Bundle bundle, String[] arrayListAddress) {
        bundle.putStringArray(EXTRA_LIST_IDS, arrayListAddress);
    }

    @Inject
    public MapFragmentPresenterImpl(MapFragmentComponent mapFragmentComponent) {
        this.mapFragmentInteractor = mapFragmentComponent.getMapFragmentModule().getMapFragmentInteractor();
        this.navigationManager = mapFragmentComponent.getMapFragmentModule().getNavigationManager();
        this.resources = mapFragmentComponent.getMapFragmentModule().getResources();
    }

    @Override
    public void setParams(Bundle bundle) {
        super.setParams(bundle);
        if (arrayListIds == null) {
            arrayListIds = bundle.getStringArray(EXTRA_LIST_IDS);
        }
    }

    @Override
    public void onViewBinded() {
        super.onViewBinded();

        onDataLoaded();
    }

    @Override
    public void onViewUnbinded() {
        super.onViewUnbinded();
    }


    @Override
    public void onDataLoaded() {
        if (isLoadingFinish()) {
            super.onDataLoaded();
            FRAGMENT fragment = getMvpFragment();
            if (fragment != null) {
                fragment.onLoaded();
            }
        }
    }


    @Override
    public boolean isLoadingFinish() {
        return true;
    }


}