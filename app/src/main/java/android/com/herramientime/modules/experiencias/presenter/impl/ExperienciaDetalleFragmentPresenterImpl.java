package android.com.herramientime.modules.experiencias.presenter.impl;

import android.com.herramientime.app.HerramienTimeApp;
import android.com.herramientime.core.entities.ErrorCause;
import android.com.herramientime.core.presenter.impl.MvpFragmentPresenterImpl;
import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.herramientime.modules.experiencias.entities.ExperienciaDetalleFragmentPresenterStatus;
import android.com.herramientime.modules.experiencias.interactor.ExperienciaDetalleFragmentInteractor;
import android.com.herramientime.modules.experiencias.presenter.ExperienciaDetalleFragmentPresenter;
import android.com.herramientime.modules.experiencias.view.ExperienciaDetalleFragment;
import android.os.Bundle;

import com.seidor.core.task.executor.future.OnCompleted;
import com.seidor.core.task.executor.future.OnData;
import com.seidor.core.task.executor.future.OnError;
import com.seidor.core.task.executor.future.ResponseFuture;

/**
 * Created by carlo on 06/11/2018.
 */

public class ExperienciaDetalleFragmentPresenterImpl<FRAGMENT extends ExperienciaDetalleFragment> extends MvpFragmentPresenterImpl<FRAGMENT>
        implements ExperienciaDetalleFragmentPresenter {

    private final String PARAM__ID_EXPERIENC = "PARAM__ID_EXPERIENC";

    private ExperienciaDetalleFragmentInteractor interactor;

    private ResponseFuture<Experiencia> responseFutureGetExperiencia;
    private final ExperienciaDetalleFragmentPresenterStatus presenterStatus = new ExperienciaDetalleFragmentPresenterStatus();

    public static void newExperienciaDetalleFragmentPresenterInstance(Bundle bundle, String id) {
        ExperienciaDetalleFragmentPresenterImpl presenter = new ExperienciaDetalleFragmentPresenterImpl();
        presenter.setupDependencies(bundle, id);
    }

    private void setupDependencies(Bundle bundle, String id) {
        setArguments(bundle);
        bundle.putString(PARAM__ID_EXPERIENC, id);
    }

    @Override
    public void setParams(Bundle bundle) {
        if (presenterStatus.getIdExperiencia() == null) {
            presenterStatus.setIdExperiencia(bundle.getString(PARAM__ID_EXPERIENC));
        }
    }

    @Override
    public void onViewBinded() {
        if (interactor == null) {
            interactor = HerramienTimeApp.getComponentDependencies().getExperienciaDetalleFragmentComponent().getHerramientaDetalleModule().getExperienciaDetalleFragmentInteractor();
        }
        getMvpFragment().onInitLoading();
        startGetExperiencia();

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
                fragment.setImageExperiencia("https://www.google.es/search?q=martillo+electrico&rlz=1C1CHBD_esES792ES795&source=lnms&tbm=isch&sa=X&ved=0ahUKEwi_xrT7i5DfAhXLUBUIHShoB00Q_AUIDigB&biw=1920&bih=889#imgrc=hxDR4kTgXMxP3M:");
                fragment.setDescripcion(presenterStatus.getExperiencia().getDescripcion());
                fragment.setPrecio(presenterStatus.getExperiencia().getPrecioHora() + " " + presenterStatus.getExperiencia().getSimboloMoneda());
                fragment.setResumen(presenterStatus.getExperiencia().getResumen());
                fragment.onLoaded();
            }
        }
    }

    @Override
    public boolean isLoadingFinish() {
        if (responseFutureGetExperiencia == null || !responseFutureGetExperiencia.isDone()) {
            return false;
        }
        return true;
    }

    //region ResponseFuture
    private void startGetExperiencia() {
        if (responseFutureGetExperiencia != null) {
            responseFutureGetExperiencia.cancel(true);
        }
        responseFutureGetExperiencia = interactor.getExperienciaById(presenterStatus.getIdExperiencia()).onData(new OnData<Experiencia>() {
            @Override
            public void onData(Experiencia experiencia) {
                presenterStatus.setExperiencia(experiencia);
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