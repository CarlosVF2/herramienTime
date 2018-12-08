package android.com.herramientime.modules.experiencias.presenter.impl;

import android.com.herramientime.R;
import android.com.herramientime.app.HerramienTimeApp;
import android.com.herramientime.core.entities.ErrorCause;
import android.com.herramientime.core.presenter.impl.MvpFragmentPresenterImpl;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.experiencias.entities.AlquilarExperienciaFragmentPresenterStatus;
import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.herramientime.modules.experiencias.injection.AlquilerExperienciaFragmentComponent;
import android.com.herramientime.modules.experiencias.interactor.AlquilerExperienciaFragmentInteractor;
import android.com.herramientime.modules.experiencias.presenter.AlquilerExperienciaFragmentPresenter;
import android.com.herramientime.modules.experiencias.view.AlquilarExperienciaFragment;
import android.content.res.Resources;
import android.os.Bundle;

import com.seidor.core.di.annotations.Inject;
import com.seidor.core.task.executor.future.OnData;
import com.seidor.core.task.executor.future.OnError;
import com.seidor.core.task.executor.future.ResponseFuture;

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
    }

    @Override
    public void onViewUnbinded() {
        super.onViewUnbinded();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDataLoaded() {
        if (isLoadingFinish()) {
            FRAGMENT fragment = getMvpFragment();
            if (fragment != null) {
                if (presenterStatus.getError() != null) {
                    //Si no se habia representado el error (porque no habia vista viva en ese momento) se representa una vez que sea ejecutable.
                    fragment.onLoadError(ErrorCause.getCause(presenterStatus.getError()));
                    presenterStatus.setError(null);
                    return;
                }
                fragment.onLoaded();
            }
        }
    }

    @Override
    public boolean isLoadingFinish() {
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
    //endregion set fields

    //region ResponseFuture

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
