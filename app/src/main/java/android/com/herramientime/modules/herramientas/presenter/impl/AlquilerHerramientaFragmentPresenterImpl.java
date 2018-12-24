package android.com.herramientime.modules.herramientas.presenter.impl;

import android.com.herramientime.R;
import android.com.herramientime.app.HerramienTimeApp;
import android.com.herramientime.core.entities.ErrorCause;
import android.com.herramientime.core.presenter.impl.MvpFragmentPresenterImpl;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.modules.domain.entities.Categoria;
import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.domain.entities.Moneda;
import android.com.herramientime.modules.herramientas.entities.AlquilerHerramientaFragmentPresenterStatus;
import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.herramientime.modules.herramientas.injection.AlquilerHerramientaFragmentComponent;
import android.com.herramientime.modules.herramientas.interactor.AlquilerHerramientaFragmentInteractor;
import android.com.herramientime.modules.herramientas.presenter.AlquilerHerramientaFragmentPresenter;
import android.com.herramientime.modules.herramientas.view.AlquilarHerramientaFragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;

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

public class AlquilerHerramientaFragmentPresenterImpl<FRAGMENT extends AlquilarHerramientaFragment> extends MvpFragmentPresenterImpl<FRAGMENT>
        implements AlquilerHerramientaFragmentPresenter {

    private NavigationManager navigationManager;
    private Resources resources;

    private AlquilerHerramientaFragmentPresenterStatus presenterStatus = new AlquilerHerramientaFragmentPresenterStatus();
    private ResponseFuture<Herramienta> responseFutureSaveHerramienta;
    private ResponseFuture<List<Categoria>> responseFutureCategorias;
    private ResponseFuture<List<Moneda>> responseFutureMonedas;
    private ResponseFuture<String> responseUri;
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

    //region Core

    @Override
    public void onViewBinded() {
        super.onViewBinded();
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
        if(responseFutureCategorias == null) {
            startResponseGetCategorias();
        }
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
        if(responseFutureCategorias != null){
            responseFutureCategorias.cancel(true);
        }
        if(responseFutureMonedas != null){
            responseFutureMonedas.cancel(true);
        }
        if(responseUri != null){
            responseUri.cancel(true);
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
                if (presenterStatus.isPendingSaveMediaCamera()) {
                    presenterStatus.setPendingSaveMediaCamera(false);
                    fragment.showPhoto(presenterStatus.getAlquilerHerramienta().getPathPhoto());
                }
                fragment.setAdapterSpinnerMoneda(getValuesAdapterMoneda());
                fragment.setAdapterSpinnerCategoria(getValuesAdapterCategoria());
                fragment.hideProgressDialog();
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


    private List<String> getValuesAdapterCategoria() {
        List<String> values = new ArrayList<>();
        for (Categoria moneda : presenterStatus.getCategorias()) {
            values.add(moneda.getDescripcion());
        }
        return values;
    }

    @Override
    public boolean isLoadingFinish() {
        if (responseFutureCategorias == null || !responseFutureCategorias.isDone()) {
            return false;
        }
        if (responseFutureMonedas == null || !responseFutureMonedas.isDone()) {
            return false;
        }

        return true;
    }

    //endregion Core

    @Override
    public void onClickConfirmar() {
        startResponseFutureSaveHerramienta();
    }

    @Override
    public void onClickMakePhoto() {
        startResponseTakePhoto();
    }

    private void startResponseTakePhoto() {
        if (responseUri != null) {
            responseUri.cancel(true);
        }
        responseUri = interactor.getPathPhoto().onData(new OnData<String>() {
            @Override
            public void onData(String s) {
                if (!TextUtils.isEmpty(s)) {
                    presenterStatus.getAlquilerHerramienta().setPathPhoto(s);
                }
                FRAGMENT fragment = getMvpFragment();
                if (fragment != null) {
                    fragment.launchIntentPhoto(s);
                }
            }
        }).onError(new OnError() {
            @Override
            public void onError(Exception e) {
                FRAGMENT fragment = getMvpFragment();
                if (fragment != null) {
                    fragment.onLoadError(ErrorCause.getCause(e));
                }
            }
        });
    }


    //region set fields

    @Override
    public void setTitulo(String titulo) {
        presenterStatus.getAlquilerHerramienta().setTitulo(titulo);
        checkAllFieldsFill();
    }

    @Override
    public void setDescripcion(String descripcion) {
        presenterStatus.getAlquilerHerramienta().setDescripcion(descripcion);
        checkAllFieldsFill();
    }

    @Override
    public void setPrecio(String precio) {
        presenterStatus.getAlquilerHerramienta().setPrecioTexto(precio);
        checkAllFieldsFill();
    }

    @Override
    public void onItemSimbolosSelected(int i) {
        presenterStatus.getAlquilerHerramienta().setMoneda(presenterStatus.getMonedas().get(i));
    }

    @Override
    public void onItemCateogiraSelected(int i) {
        presenterStatus.getAlquilerHerramienta().setCategoria(presenterStatus.getCategorias().get(i));
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        presenterStatus.restoreInstance(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        presenterStatus.saveInstance(outState);
    }

    @Override
    public void showPhoto() {
        presenterStatus.setPendingSaveMediaCamera(true);

    }

    private void checkAllFieldsFill() {
        FRAGMENT fragment = getMvpFragment();

        if (fragment != null) {
            if (!TextUtils.isEmpty(presenterStatus.getAlquilerHerramienta().getTitulo()) &&
                    !TextUtils.isEmpty(presenterStatus.getAlquilerHerramienta().getDescripcion()) &&
                    !TextUtils.isEmpty(presenterStatus.getAlquilerHerramienta().getPrecioTexto())) {
                fragment.setVisibilityMenuItemConfirmar(true);
            } else {
                fragment.setVisibilityMenuItemConfirmar(false);
            }
        }
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

    private void startResponseGetCategorias() {
        if (responseFutureCategorias != null) {
            responseFutureCategorias.cancel(true);
        }
        responseFutureCategorias = interactor.getCategorias().onData(new OnData<List<Categoria>>() {
            @Override
            public void onData(List<Categoria> categorias) {
                presenterStatus.setCategorias(categorias);
            }
        }).onCompleted(new OnCompleted() {
            @Override
            public void onCompleted() {
                onDataLoaded();
            }
        });

    }

    private void startResponseFutureSaveHerramienta() {
        if (responseFutureSaveHerramienta != null) {
            responseFutureSaveHerramienta.cancel(true);
        }
        FRAGMENT fragment = getMvpFragment();
        if (fragment != null) {
            fragment.showProgressDialogWithMessage("Dando de alta la herramienta");
            //setImagePhoto(fragment.getBitmapImage(), fragment);
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
        }).onCompleted(new OnCompleted() {
            @Override
            public void onCompleted() {
                FRAGMENT fragment1 = getMvpFragment();
                if (fragment1 != null) {
                    fragment1.hideProgressDialog();
                }
            }
        });
    }

    //endregion ResponseFuture
}
