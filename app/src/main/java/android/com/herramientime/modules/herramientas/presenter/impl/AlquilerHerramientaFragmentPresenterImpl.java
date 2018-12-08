package android.com.herramientime.modules.herramientas.presenter.impl;

import android.com.herramientime.R;
import android.com.herramientime.app.HerramienTimeApp;
import android.com.herramientime.core.entities.ErrorCause;
import android.com.herramientime.core.presenter.impl.MvpFragmentPresenterImpl;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.herramientas.entities.AlquilerHerramientaFragmentPresenterStatus;
import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.herramientime.modules.herramientas.injection.AlquilerHerramientaFragmentComponent;
import android.com.herramientime.modules.herramientas.interactor.AlquilerHerramientaFragmentInteractor;
import android.com.herramientime.modules.herramientas.presenter.AlquilerHerramientaFragmentPresenter;
import android.com.herramientime.modules.herramientas.view.AlquilarHerramientaFragment;
import android.content.res.Resources;
import android.os.Bundle;

import com.seidor.core.di.annotations.Inject;
import com.seidor.core.task.executor.future.OnData;
import com.seidor.core.task.executor.future.OnError;
import com.seidor.core.task.executor.future.ResponseFuture;

/**
 * Created by carlo on 06/11/2018.
 */

public class AlquilerHerramientaFragmentPresenterImpl<FRAGMENT extends AlquilarHerramientaFragment> extends MvpFragmentPresenterImpl<FRAGMENT>
        implements AlquilerHerramientaFragmentPresenter {

    private NavigationManager navigationManager;
    private Resources resources;

    private AlquilerHerramientaFragmentPresenterStatus presenterStatus = new AlquilerHerramientaFragmentPresenterStatus();
    private ResponseFuture<Herramienta> responseFutureSaveHerramienta;
    private AlquilerHerramientaFragmentInteractor interactor;

    public static void newAlquilerHerramientaFragmentPresenterInstance(Bundle bundle) {
        AlquilerHerramientaFragmentPresenterImpl presenter = new AlquilerHerramientaFragmentPresenterImpl();
        presenter.setArguments(bundle);
    }

    public AlquilerHerramientaFragmentPresenterImpl() {
    }

    @Inject
    public AlquilerHerramientaFragmentPresenterImpl(AlquilerHerramientaFragmentComponent alquilerHerramientaFragmentComponent) {
        this.navigationManager = alquilerHerramientaFragmentComponent.getAlquilerHerramientaFragmentModule().getNavigationManager();
        this.resources = alquilerHerramientaFragmentComponent.getAlquilerHerramientaFragmentModule().getResources();
        this.interactor = alquilerHerramientaFragmentComponent.getAlquilerHerramientaFragmentModule().getAlquilerHerramientaFragmentInteractor();
    }

    @Override
    public void onViewBinded() {
        if (navigationManager == null) {
            navigationManager = HerramienTimeApp.getComponentDependencies().getNavigationManager();
        }
        if (resources == null) {
            resources = HerramienTimeApp.getComponentDependencies().getAlquilerHerramientaFragmentComponent().getAlquilerHerramientaFragmentModule().getResources();
        }
        if (interactor == null) {
            this.interactor = HerramienTimeApp.getComponentDependencies().getAlquilerHerramientaFragmentComponent().getAlquilerHerramientaFragmentModule().getAlquilerHerramientaFragmentInteractor();
        }
        getMvpFragment().onInitLoading();
        getMvpFragment().setTitle(resources.getString(R.string.title_alquiler_herramienta));
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
        presenterStatus.getAlquilerHerramienta().setTitulo(titulo);
    }

    @Override
    public void setDescripcion(String descripcion) {
        presenterStatus.getAlquilerHerramienta().setDescripcion(descripcion);
    }

    @Override
    public void setPrecio(String precio) {
        presenterStatus.getAlquilerHerramienta().setPrecioTexto(precio);
    }
    //endregion set fields

    //region ResponseFuture

    private void startResponseFutureSaveHerramienta() {
        if (responseFutureSaveHerramienta != null) {
            responseFutureSaveHerramienta.cancel(true);
        }
        responseFutureSaveHerramienta = interactor.saveHerramienta(presenterStatus.getAlquilerHerramienta()).onData(new OnData<Herramienta>() {
            @Override
            public void onData(Herramienta herramienta) {
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
