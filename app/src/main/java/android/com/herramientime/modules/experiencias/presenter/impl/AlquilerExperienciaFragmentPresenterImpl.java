package android.com.herramientime.modules.experiencias.presenter.impl;

import android.com.herramientime.R;
import android.com.herramientime.app.HerramienTimeApp;
import android.com.herramientime.core.entities.ErrorCause;
import android.com.herramientime.core.presenter.impl.MvpFragmentPresenterImpl;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.domain.entities.Moneda;
import android.com.herramientime.modules.experiencias.entities.AlquilarExperienciaFragmentPresenterStatus;
import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.herramientime.modules.experiencias.injection.AlquilerExperienciaFragmentComponent;
import android.com.herramientime.modules.experiencias.interactor.AlquilerExperienciaFragmentInteractor;
import android.com.herramientime.modules.experiencias.presenter.AlquilerExperienciaFragmentPresenter;
import android.com.herramientime.modules.experiencias.view.AlquilarExperienciaFragment;
import android.content.res.Resources;
import android.os.Bundle;

import com.seidor.core.di.annotations.Inject;
import com.seidor.core.task.executor.future.OnCompleted;
import com.seidor.core.task.executor.future.OnData;
import com.seidor.core.task.executor.future.OnError;
import com.seidor.core.task.executor.future.ResponseFuture;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carlo on 06/11/2018.
 */

public class AlquilerExperienciaFragmentPresenterImpl<FRAGMENT extends AlquilarExperienciaFragment> extends MvpFragmentPresenterImpl<FRAGMENT>
        implements AlquilerExperienciaFragmentPresenter {

    private NavigationManager navigationManager;
    private Resources resources;

    private AlquilarExperienciaFragmentPresenterStatus presenterStatus = new AlquilarExperienciaFragmentPresenterStatus();
    private ResponseFuture<Experiencia> responseFutureSaveExperiencia;
    private AlquilerExperienciaFragmentInteractor interactor;
    private ResponseFuture<List<Moneda>> responseFutureMonedas;

    public static void newAlquilerExperienciaFragmentPresenterInstance(Bundle bundle) {
        AlquilerExperienciaFragmentPresenterImpl presenter = new AlquilerExperienciaFragmentPresenterImpl();
        presenter.setArguments(bundle);
    }

    public AlquilerExperienciaFragmentPresenterImpl() {
    }

    @Inject
    public AlquilerExperienciaFragmentPresenterImpl(AlquilerExperienciaFragmentComponent alquilerExperienciaFragmentComponent) {
        this.navigationManager = alquilerExperienciaFragmentComponent.getAlquilerExperienciaFragmentModule().getNavigationManager();
        this.resources = alquilerExperienciaFragmentComponent.getAlquilerExperienciaFragmentModule().getResources();
        this.interactor = alquilerExperienciaFragmentComponent.getAlquilerExperienciaFragmentModule().getAlquilerExperienciaFragmentInteractor();
    }

    @Override
    public void onViewBinded() {
        super.onViewBinded();
        if (navigationManager == null) {
            navigationManager = HerramienTimeApp.getComponentDependencies().getNavigationManager();
        }
        if (resources == null) {
            resources = HerramienTimeApp.getComponentDependencies().getAlquilerExperienciaFragmentComponent().getAlquilerExperienciaFragmentModule().getResources();
        }
        if (interactor == null) {
            this.interactor = HerramienTimeApp.getComponentDependencies().getAlquilerExperienciaFragmentComponent().getAlquilerExperienciaFragmentModule().getAlquilerExperienciaFragmentInteractor();
        }
        getMvpFragment().onInitLoading();
        getMvpFragment().setTitle(resources.getString(R.string.title_alquiler_experiencia));
        if(responseFutureMonedas == null) {
            startResponseGetMonedas();
        }
    }

    @Override
    public void onViewUnbinded() {
        super.onViewUnbinded();
    }

    @Override
    public void onDestroy() {
        if(responseFutureMonedas != null){
            responseFutureMonedas.cancel(true);
        }

        super.onDestroy();
    }

    @Override
    public void onDataLoaded() {
        if (isLoadingFinish()) {
            super.onDataLoaded();
            FRAGMENT fragment = getMvpFragment();
            if (fragment != null) {
                if (presenterStatus.getError() != null) {
                    //Si no se habia representado el error (porque no habia vista viva en ese momento) se representa una vez que sea ejecutable.
                    fragment.onLoadError(ErrorCause.getCause(presenterStatus.getError()));
                    presenterStatus.setError(null);
                    return;
                }
                fragment.setAdapterSpinnerMoneda(getValuesAdapterMoneda());
                fragment.onLoaded();
            }
        }
    }

    private List<String> getValuesAdapterMoneda() {
        List<String> values = new ArrayList<>();
        for (Moneda moneda : presenterStatus.getMonedas()) {
            values.add(moneda.getSimbolo());
        }
        return values;
    }

    @Override
    public boolean isLoadingFinish() {
        if (responseFutureMonedas != null) {
            responseFutureMonedas.cancel(true);
        }
        return true;
    }

    @Override
    public void onClickConfirmar() {
        startResponseFutureSaveHerramienta();
    }


    //region set fields


    @Override
    public void setTitulo(String titulo) {
        presenterStatus.getAlquilerExperiencia().setTitulo(titulo);
    }

    @Override
    public void setDescripcion(String descripcion) {
        presenterStatus.getAlquilerExperiencia().setDescripcion(descripcion);
    }

    @Override
    public void setPrecio(String precio) {
        presenterStatus.getAlquilerExperiencia().setPrecioTexto(precio);
    }

    @Override
    public void onItemSimbolosSelected(int i) {
        presenterStatus.getAlquilerExperiencia().setMoneda(presenterStatus.getMonedas().get(i));
    }
    //endregion set fields

    //region ResponseFuture


    private void startResponseGetMonedas() {
        if (responseFutureMonedas != null) {
            responseFutureMonedas.cancel(true);
        }
        responseFutureMonedas = interactor.getMonedas().onData(new OnData<List<Moneda>>() {
            @Override
            public void onData(List<Moneda> monedas) {
                presenterStatus.setMonedas(monedas);
            }
        }).onCompleted(new OnCompleted() {
            @Override
            public void onCompleted() {
                onDataLoaded();
            }
        });
    }


    private void startResponseFutureSaveHerramienta() {
        if (responseFutureSaveExperiencia != null) {
            responseFutureSaveExperiencia.cancel(true);
        }
        responseFutureSaveExperiencia = interactor.saveExperiencia(presenterStatus.getAlquilerExperiencia()).onData(new OnData<Experiencia>() {
            @Override
            public void onData(Experiencia herramienta) {
                if (herramienta != null) {
                    try {
                        navigationManager.navigateBack();
                    } catch (LocalException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).onError(new OnError() {
            @Override
            public void onError(Exception e) {
                presenterStatus.setError(e);
                onDataLoaded();
            }
        });
    }

    //endregion ResponseFuture
}
