package android.com.herramientime.modules.herramientas.presenter.impl;

import android.com.herramientime.app.HerramienTimeApp;
import android.com.herramientime.core.entities.ErrorCause;
import android.com.herramientime.core.presenter.impl.MvpFragmentPresenterImpl;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.herramientas.adapter.HerramientasVHListener;
import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.herramientime.modules.herramientas.entities.HerramientasFragmentPresenterStatus;
import android.com.herramientime.modules.herramientas.injection.HerramientasFragmentComponent;
import android.com.herramientime.modules.herramientas.interactor.HerramientasFragmentInteractor;
import android.com.herramientime.modules.herramientas.presenter.HerramientasFragmentPresenter;
import android.com.herramientime.modules.herramientas.view.HerramientasFragment;
import android.os.Bundle;

import com.seidor.core.di.annotations.Inject;
import com.seidor.core.task.executor.future.OnCompleted;
import com.seidor.core.task.executor.future.OnData;
import com.seidor.core.task.executor.future.OnError;
import com.seidor.core.task.executor.future.ResponseFuture;

import java.util.List;

/**
 * Created by carlo on 06/11/2018.
 */

public class HerramientasFragmentPresenterImpl<FRAGMENT extends HerramientasFragment> extends MvpFragmentPresenterImpl<FRAGMENT>
        implements HerramientasFragmentPresenter, HerramientasVHListener {

    private NavigationManager navigationManager;
    private HerramientasFragmentInteractor herramientasFragmentInteractor;

    private ResponseFuture<List<Herramienta>> responseFutureHerramientas;
    private final HerramientasFragmentPresenterStatus presenterStatus = new HerramientasFragmentPresenterStatus();

    public static void newHerramientasFragmentPresenterInstance(Bundle bundle) {
        HerramientasFragmentPresenterImpl presenter = new HerramientasFragmentPresenterImpl();
        presenter.setArguments(bundle);
    }

    public HerramientasFragmentPresenterImpl() {
    }

    @Inject
    public HerramientasFragmentPresenterImpl(HerramientasFragmentComponent herramientasFragmentComponent) {
        this.navigationManager = herramientasFragmentComponent.getHerramientasFragmentModule().getNavigationManager();
        this.herramientasFragmentInteractor = herramientasFragmentComponent.getHerramientasFragmentModule().getHerramientasFragmentInteractor();
    }

    @Override
    public void onViewBinded() {
        super.onViewBinded();
        if(navigationManager == null){
            navigationManager = HerramienTimeApp.getComponentDependencies().getNavigationManager();
        }
        if(herramientasFragmentInteractor == null){
            herramientasFragmentInteractor = HerramienTimeApp.getComponentDependencies().getHerramientasFragmentComponent().getHerramientasFragmentModule().getHerramientasFragmentInteractor();
        }
        getMvpFragment().onInitLoading();
        getMvpFragment().setTitle("Herramientas");
        startGetHerramientas();
    }

    @Override
    public void onViewUnbinded() {
        super.onViewUnbinded();
    }

    @Override
    public void onDestroy() {
        if(responseFutureHerramientas != null){
            responseFutureHerramientas.cancel(true);
        }
        super.onDestroy();
    }

    @Override
    public void onDataLoaded() {
        if(isLoadingFinish()){
            super.onDataLoaded();
            FRAGMENT fragment = getMvpFragment();
            if(fragment != null){
                fragment.setRefresh(false);
                if(presenterStatus.getError() != null){
                    //Si no se habia representado el error (porque no habia vista viva en ese momento) se representa una vez que sea ejecutable.
                    fragment.onLoadError(ErrorCause.getCause(presenterStatus.getError()));
                    presenterStatus.setError(null);
                    return;
                }
                fragment.setData(presenterStatus.getHerramientas(), this);
                fragment.onLoaded();
            }
        }
    }

    @Override
    public boolean isLoadingFinish() {
        if(responseFutureHerramientas == null || !responseFutureHerramientas.isDone()){
            return false;
        }
        return true;
    }

    @Override
    public void onRefresh() {
        startGetHerramientas();
    }

    @Override
    public void onClickSubirHerramienta() {
        try {
            navigationManager.navigateToAlquilerHerramienta();
        } catch (LocalException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClickMap() {
        try {
            navigationManager.navigateToMap();
        } catch (LocalException e) {
            e.printStackTrace();
        }
    }


    //region OnClickItem
    @Override
    public void onClickItem(int position) {
        Herramienta herramienta = presenterStatus.getHerramientas().get(position);
        try {
            navigationManager.navigateToDetalleHerramienta(herramienta.getId());
        } catch (LocalException e) {
            e.printStackTrace();
        }

    }
    //endregion OnClickItem

    //region ResponseFuture
    private void startGetHerramientas(){
        if (responseFutureHerramientas != null) {
            responseFutureHerramientas.cancel(true);
        }
        responseFutureHerramientas = herramientasFragmentInteractor.getHerramientas().onData(new OnData<List<Herramienta>>() {
            @Override
            public void onData(List<Herramienta> herramientas) {
                presenterStatus.setHerramientas(herramientas);
            }
        }).onError(new OnError() {
            @Override
            public void onError(Exception e) {
                presenterStatus.setError(e);
            }
        }).onCompleted(new OnCompleted() {
            @Override
            public void onCompleted() {
                onDataLoaded();
            }
        });
    }
    //endregion ResponseFuture
}
