package android.com.herramientime.modules.herramientas.presenter.impl;

import android.com.herramientime.app.HerramienTimeApp;
import android.com.herramientime.core.entities.ErrorCause;
import android.com.herramientime.core.presenter.impl.MvpFragmentPresenterImpl;
import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.herramientime.modules.herramientas.entities.HerramientaDetalleFragmentPresenterStatus;
import android.com.herramientime.modules.herramientas.interactor.HerramientaDetalleFragmentInteractor;
import android.com.herramientime.modules.herramientas.presenter.HerramientaDetalleFragmentPresenter;
import android.com.herramientime.modules.herramientas.view.HerramientaDetalleFragment;
import android.os.Bundle;

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

    private ResponseFuture<Herramienta> responseFutureGetHerramienta;
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

    @Override
    public void onViewBinded() {
        if (interactor == null) {
            interactor = HerramienTimeApp.getComponentDependencies().getHerramientaDetalleFragmentComponent().getHerramientaDetalleModule().getHerramientaDetalleFragmentInteractor();
        }
        getMvpFragment().onInitLoading();
        startGetHerramienta();

    }

    @Override
    public void onDataLoaded() {
        if (isLoadingFinish()) {
            FRAGMENT fragment = getMvpFragment();
            if (fragment != null) {
                if (presenterStatus.getError() != null) {
                    fragment.onLoadError(ErrorCause.getCause(presenterStatus.getError()));
                    presenterStatus.setError(null);
                    return;
                }
                fragment.setImageHerramienta(presenterStatus.getHerramienta().getUrlImagen());
                fragment.setDescripcion(presenterStatus.getHerramienta().getDescripcion());
                fragment.setPrecio(presenterStatus.getHerramienta().getMoneda());
                fragment.setResumen(presenterStatus.getHerramienta().getResumen());
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

    //region ResponseFuture
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
