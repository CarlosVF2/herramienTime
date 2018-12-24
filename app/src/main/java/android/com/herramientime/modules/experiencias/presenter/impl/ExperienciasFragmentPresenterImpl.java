package android.com.herramientime.modules.experiencias.presenter.impl;

import android.com.herramientime.R;
import android.com.herramientime.app.HerramienTimeApp;
import android.com.herramientime.core.entities.ErrorCause;
import android.com.herramientime.core.presenter.impl.MvpFragmentPresenterImpl;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.domain.entities.ResultsException;
import android.com.herramientime.modules.domain.entities.UsuarioException;
import android.com.herramientime.modules.experiencias.adapter.ExperienciasVHListener;
import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.herramientime.modules.experiencias.entities.ExperienciasFragmentPresenterStatus;
import android.com.herramientime.modules.experiencias.injection.ExperienciasFragmentComponent;
import android.com.herramientime.modules.experiencias.interactor.ExperienciaFragmentInteractor;
import android.com.herramientime.modules.experiencias.presenter.ExperienciasFragmentPresenter;
import android.com.herramientime.modules.experiencias.view.ExperienciasFragment;
import android.com.herramientime.modules.herramientas.entities.FiltrosExperiencia;
import android.content.res.Resources;
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

public class ExperienciasFragmentPresenterImpl<FRAGMENT extends ExperienciasFragment> extends MvpFragmentPresenterImpl<FRAGMENT>
        implements ExperienciasFragmentPresenter, ExperienciasVHListener {

    private NavigationManager navigationManager;
    private ExperienciaFragmentInteractor experienciaFragmentInteractor;
    private Resources resources;

    private ResponseFuture<List<Experiencia>> responseFutureExperiencias;
    private ResponseFuture<Boolean> responseFutureCheckUpload;
    private final ExperienciasFragmentPresenterStatus presenterStatus = new ExperienciasFragmentPresenterStatus();

    public static void newExperienciasFragmentPresenterInstance(Bundle bundle) {
        ExperienciasFragmentPresenterImpl presenter = new ExperienciasFragmentPresenterImpl();
        presenter.setArguments(bundle);
    }

    public ExperienciasFragmentPresenterImpl() {
    }

    @Inject
    public ExperienciasFragmentPresenterImpl(ExperienciasFragmentComponent experienciasFragmentComponent) {
        this.navigationManager = experienciasFragmentComponent.getExperienciasFragmentModule().getNavigationManager();
        this.experienciaFragmentInteractor = experienciasFragmentComponent.getExperienciasFragmentModule().getExperienciaFragmentInteractor();
        this.resources = experienciasFragmentComponent.getExperienciasFragmentModule().getResources();
    }

    //region Core

    @Override
    public void onViewBinded() {
        super.onViewBinded();
        if (navigationManager == null) {
            navigationManager = HerramienTimeApp.getComponentDependencies().getNavigationManager();
        }
        if (experienciaFragmentInteractor == null) {
            experienciaFragmentInteractor = HerramienTimeApp.getComponentDependencies().getExperienciasFragmentComponent().getExperienciasFragmentModule().getExperienciaFragmentInteractor();
        }
        if (resources == null) {
            resources = HerramienTimeApp.getComponentDependencies().getExperienciasFragmentComponent().getExperienciasFragmentModule().getResources();
        }
        getMvpFragment().onInitLoading();
        getMvpFragment().setTitle(resources.getString(R.string.title_experiencias));
        startGetExperiencias();
    }

    @Override
    public void onViewUnbinded() {
        super.onViewUnbinded();
    }

    @Override
    public void onDestroy() {
        if (responseFutureExperiencias != null) {
            responseFutureExperiencias.cancel(true);
        }
        if(responseFutureCheckUpload != null) {
            responseFutureCheckUpload.cancel(true);
        }
        super.onDestroy();
    }

    @Override
    public void onDataLoaded() {
        if (isLoadingFinish()) {
            super.onDataLoaded();
            FRAGMENT fragment = getMvpFragment();
            if (fragment != null) {
                fragment.setRefresh(false);
                if (presenterStatus.getError() != null) {
                    if (presenterStatus.getError().getCause() instanceof UsuarioException) {
                        fragment.onLoadErrorUser(ErrorCause.getCause(presenterStatus.getError()));
                    } else {
                        if(presenterStatus.getError().getCause() instanceof ResultsException){
                            onClickRestaurarFilter();
                        }
                        //Si no se habia representado el error (porque no habia vista viva en ese momento) se representa una vez que sea ejecutable.
                        fragment.onLoadError(ErrorCause.getCause(presenterStatus.getError()));
                    }
                    presenterStatus.setError(null);
                    return;
                }
                fragment.setData(presenterStatus.getExperiencias(), this);
                fragment.onLoaded();
            }
        }
    }

    @Override
    public boolean isLoadingFinish() {
        if (responseFutureExperiencias == null || !responseFutureExperiencias.isDone()) {
            return false;
        }
        return true;
    }

    //endregion Core

    @Override
    public void onRefresh() {
        startGetExperiencias();
    }

    @Override
    public void onClickActionSubirExperiencia() {
        startCheckUpload();
    }

    @Override
    public void onClickFilter() {
        FRAGMENT fragment = getMvpFragment();
        if (fragment != null) {
            fragment.toggleDrawer();
        }
    }

    @Override
    public void onClickAplicarFilter() {
        //recuperamos los textos de la vista
        FRAGMENT fragment = getMvpFragment();
        if (fragment != null) {
            presenterStatus.getFiltrosExperiencia().setDescripcion(fragment.getDescriptionText());
            presenterStatus.getFiltrosExperiencia().setPrecioInicial(fragment.getPrecioInicialText());
            presenterStatus.getFiltrosExperiencia().setPrecioFinal(fragment.getPrecioFinalText());
        }
        startGetExperiencias();
    }

    @Override
    public void onClickRestaurarFilter() {
        FRAGMENT fragment = getMvpFragment();
        if (fragment != null) {
            fragment.restoreFilters();
        }
        presenterStatus.setFiltrosExperiencia(new FiltrosExperiencia());
        startGetExperiencias();
    }

    @Override
    public void onClickAceptarLogin() {
        try {
            navigationManager.navigateToLogin();
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
        Experiencia experiencia = presenterStatus.getExperiencias().get(position);
        try {
            navigationManager.navigateToDetalleExperiencia(experiencia.getId());
        } catch (LocalException e) {
            e.printStackTrace();
        }

    }
    //endregion OnClickItem

    //region ResponseFuture
    private void startCheckUpload() {
        if (responseFutureCheckUpload != null) {
            responseFutureCheckUpload.cancel(true);
        }
        responseFutureCheckUpload = experienciaFragmentInteractor.checkUpload().onData(new OnData<Boolean>() {
            @Override
            public void onData(Boolean aBoolean) {
                if (aBoolean) {
                    try {
                        navigationManager.navigateToAlquilerExperiencia();
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

    private void startGetExperiencias() {
        if (responseFutureExperiencias != null) {
            responseFutureExperiencias.cancel(true);
        }
        responseFutureExperiencias = experienciaFragmentInteractor.getExperiencias(presenterStatus.getFiltrosExperiencia()).onData(new OnData<List<Experiencia>>() {
            @Override
            public void onData(List<Experiencia> experiencias) {
                presenterStatus.setExperiencias(experiencias);
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
