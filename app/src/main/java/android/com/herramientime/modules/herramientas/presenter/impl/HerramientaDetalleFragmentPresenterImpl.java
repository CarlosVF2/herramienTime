package android.com.herramientime.modules.herramientas.presenter.impl;

import android.com.herramientime.app.HerramienTimeApp;
import android.com.herramientime.core.entities.ErrorCause;
import android.com.herramientime.core.presenter.impl.MvpFragmentPresenterImpl;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.domain.entities.UsuarioException;
import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.herramientime.modules.herramientas.entities.HerramientaDetalleFragmentPresenterStatus;
import android.com.herramientime.modules.herramientas.injection.HerramientaDetalleFragmentComponent;
import android.com.herramientime.modules.herramientas.interactor.HerramientaDetalleFragmentInteractor;
import android.com.herramientime.modules.herramientas.presenter.HerramientaDetalleFragmentPresenter;
import android.com.herramientime.modules.herramientas.view.HerramientaDetalleFragment;
import android.os.Bundle;

import com.seidor.core.di.annotations.Inject;
import com.seidor.core.task.executor.future.OnCompleted;
import com.seidor.core.task.executor.future.OnData;
import com.seidor.core.task.executor.future.OnError;
import com.seidor.core.task.executor.future.ResponseFuture;

/**
 * Created by carlo on 06/11/2018.
 */

public class HerramientaDetalleFragmentPresenterImpl<FRAGMENT extends HerramientaDetalleFragment> extends MvpFragmentPresenterImpl<FRAGMENT>
        implements HerramientaDetalleFragmentPresenter {

    private final String PARAM__ID_HERRAM = "PARAM__ID_HERRAM";

    private HerramientaDetalleFragmentInteractor interactor;
    private NavigationManager navigationManager;

    private ResponseFuture<Herramienta> responseFutureGetHerramienta;
    private ResponseFuture<Boolean> responseFutureCheckReservar;
    private final HerramientaDetalleFragmentPresenterStatus presenterStatus = new HerramientaDetalleFragmentPresenterStatus();

    public static void newHerramientaDetalleFragmentPresenterInstance(Bundle bundle, String id) {
        HerramientaDetalleFragmentPresenterImpl presenter = new HerramientaDetalleFragmentPresenterImpl();
        presenter.setupDependencies(bundle, id);
    }

    private void setupDependencies(Bundle bundle, String id){
        setArguments(bundle);
        bundle.putString(PARAM__ID_HERRAM, id);
    }

    @Override
    public void setParams(Bundle bundle) {
        if (presenterStatus.getIdHerramienta() == null) {
            presenterStatus.setIdHerramienta(bundle.getString(PARAM__ID_HERRAM));
        }
    }


    public HerramientaDetalleFragmentPresenterImpl() {
    }

    @Inject
    public HerramientaDetalleFragmentPresenterImpl(HerramientaDetalleFragmentComponent herramientaDetalleFragmentComponent) {
        this.navigationManager = herramientaDetalleFragmentComponent.getHerramientaDetalleModule().getNavigationManager();
        this.interactor = herramientaDetalleFragmentComponent.getHerramientaDetalleModule().getHerramientaDetalleFragmentInteractor();
    }

    @Override
    public void onViewBinded() {
        super.onViewBinded();
        if (interactor == null) {
            interactor = HerramienTimeApp.getComponentDependencies().getHerramientaDetalleFragmentComponent().getHerramientaDetalleModule().getHerramientaDetalleFragmentInteractor();
        }
        if (navigationManager == null) {
            navigationManager = HerramienTimeApp.getComponentDependencies().getHerramientaDetalleFragmentComponent().getHerramientaDetalleModule().getNavigationManager();
        }
        getMvpFragment().onInitLoading();
        startGetHerramienta();

    }

    @Override
    public void onDataLoaded() {
        if (isLoadingFinish()) {
            super.onDataLoaded();
            FRAGMENT fragment = getMvpFragment();
            if (fragment != null) {
                if (presenterStatus.getError() != null) {
                    if (presenterStatus.getError().getCause() instanceof UsuarioException) {
                        fragment.onLoadErrorUser(ErrorCause.getCause(presenterStatus.getError()));
                    } else {
                        //Si no se habia representado el error (porque no habia vista viva en ese momento) se representa una vez que sea ejecutable.
                        fragment.onLoadError(ErrorCause.getCause(presenterStatus.getError()));
                    }
                    presenterStatus.setError(null);
                    return;
                }
                fragment.setImageHerramienta(presenterStatus.getHerramienta().getUrlImagen());
                fragment.setDescripcion(presenterStatus.getHerramienta().getDescripcion());
                fragment.setPrecio(presenterStatus.getHerramienta().getMoneda());
                fragment.setResumen(presenterStatus.getHerramienta().getResumen());
                fragment.setResumen(presenterStatus.getHerramienta().getCategoriaDescriptivo());
                fragment.onLoaded();
            }
        }
    }

    @Override
    public boolean isLoadingFinish() {
        if (responseFutureGetHerramienta == null || !responseFutureGetHerramienta.isDone()) {
            return false;
        }
        return true;
    }

    @Override
    public void onClickReservar() {
        startCheckReservar();
    }

    @Override
    public void onClickAceptarLogin() {
        try {
            navigationManager.navigateToLogin();
        } catch (LocalException e) {
            e.printStackTrace();
        }
    }


    //region ResponseFuture
    private void startCheckReservar() {
        if (responseFutureCheckReservar != null) {
            responseFutureCheckReservar.cancel(true);
        }
        responseFutureCheckReservar = interactor.checkReservar().onData(new OnData<Boolean>() {
            @Override
            public void onData(Boolean aBoolean) {
                if (aBoolean) {
                    try {
                        navigationManager.navigateToReservaHerramienta(presenterStatus.getIdHerramienta(), null, -1);
                    } catch (LocalException e) {
                        e.printStackTrace();
                    }
                }
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

    private void startGetHerramienta() {
        if (responseFutureGetHerramienta != null) {
            responseFutureGetHerramienta.cancel(true);
        }
        responseFutureGetHerramienta = interactor.getHerramientaById(presenterStatus.getIdHerramienta()).onData(new OnData<Herramienta>() {
            @Override
            public void onData(Herramienta herramienta) {
                presenterStatus.setHerramienta(herramienta);
            }
        }).onCompleted(new OnCompleted() {
            @Override
            public void onCompleted() {
                onDataLoaded();
            }
        }).onError(new OnError() {
            @Override
            public void onError(Exception e) {
                presenterStatus.setError(e);
            }
        });
    }
    //endregion ResponseFuture
}
