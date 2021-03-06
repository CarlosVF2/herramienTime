package android.com.herramientime.modules.experiencias.presenter.impl;

import android.com.herramientime.app.HerramienTimeApp;
import android.com.herramientime.core.entities.ErrorCause;
import android.com.herramientime.core.presenter.impl.MvpFragmentPresenterImpl;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.domain.entities.ResultsException;
import android.com.herramientime.modules.domain.entities.UsuarioException;
import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.herramientime.modules.experiencias.entities.ExperienciaDetalleFragmentPresenterStatus;
import android.com.herramientime.modules.experiencias.injection.ExperienciaDetalleFragmentComponent;
import android.com.herramientime.modules.experiencias.interactor.ExperienciaDetalleFragmentInteractor;
import android.com.herramientime.modules.experiencias.presenter.ExperienciaDetalleFragmentPresenter;
import android.com.herramientime.modules.experiencias.view.ExperienciaDetalleFragment;
import android.com.herramientime.modules.usuarios.entities.Usuario;
import android.os.Bundle;
import android.text.TextUtils;

import com.seidor.core.di.annotations.Inject;
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
    private NavigationManager navigationManager;

    private ResponseFuture<Experiencia> responseFutureGetExperiencia;
    private ResponseFuture<Usuario> responseFutureGetUsuario;
    private ResponseFuture<Boolean> responseFutureCheckReservar;
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

    public ExperienciaDetalleFragmentPresenterImpl() {
    }

    @Inject
    public ExperienciaDetalleFragmentPresenterImpl(ExperienciaDetalleFragmentComponent alquilerExperienciaFragmentComponent) {
        this.navigationManager = alquilerExperienciaFragmentComponent.getHerramientaDetalleModule().getNavigationManager();
        this.interactor = alquilerExperienciaFragmentComponent.getHerramientaDetalleModule().getExperienciaDetalleFragmentInteractor();
    }

    //region Core

    @Override
    public void onViewBinded() {
        super.onViewBinded();
        if (interactor == null) {
            interactor = HerramienTimeApp.getComponentDependencies().getExperienciaDetalleFragmentComponent().getHerramientaDetalleModule().getExperienciaDetalleFragmentInteractor();
        }
        if (navigationManager == null) {
            navigationManager = HerramienTimeApp.getComponentDependencies().getExperienciaDetalleFragmentComponent().getHerramientaDetalleModule().getNavigationManager();
        }
        getMvpFragment().setTitle("Detalle experiencia");
        getMvpFragment().onInitLoading();
        startGetExperiencia();
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
                fragment.setImageExperiencia(presenterStatus.getExperiencia().getUrlImagen());
                fragment.setDescripcion(presenterStatus.getExperiencia().getDescripcion());
                fragment.setPrecio(presenterStatus.getExperiencia().getPrecioHora() + " " + presenterStatus.getExperiencia().getSimboloMoneda());
                fragment.setResumen(presenterStatus.getExperiencia().getResumen());
                //Usuario
                fragment.setIdUsuario(presenterStatus.getUsuario().getId());
                fragment.setAcerca(presenterStatus.getUsuario().getAcercaDeTi());
                if(!TextUtils.isEmpty(presenterStatus.getUsuario().getCalificacion())){
                    fragment.setCalificacion(Float.valueOf(presenterStatus.getUsuario().getCalificacion()));
                }
                fragment.onLoaded();
            }
        }
    }

    @Override
    public boolean isLoadingFinish() {
        if (responseFutureGetExperiencia == null || !responseFutureGetExperiencia.isDone()) {
            return false;
        }
        if(responseFutureGetUsuario == null || !responseFutureGetUsuario.isDone()){
            return false;
        }
        return true;
    }

    @Override
    public void onDestroy() {
        if(responseFutureGetExperiencia != null){
            responseFutureGetExperiencia.cancel(true);
        }
        if(responseFutureGetUsuario != null){
            responseFutureGetUsuario.cancel(true);
        }
        super.onDestroy();
    }


    //endregion Core

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
                        navigationManager.navigateToReservaExperiencia(presenterStatus.getIdExperiencia(), null, -1);
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

    private void startGetExperiencia() {
        if (responseFutureGetExperiencia != null) {
            responseFutureGetExperiencia.cancel(true);
        }
        responseFutureGetExperiencia = interactor.getExperienciaById(presenterStatus.getIdExperiencia()).onData(new OnData<Experiencia>() {
            @Override
            public void onData(Experiencia experiencia) {
                presenterStatus.setExperiencia(experiencia);
                startResponseGetUsuario(experiencia.getIdUsuario());
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

    private void startResponseGetUsuario(String idUsuario) {
        if(responseFutureGetUsuario != null){
            responseFutureGetUsuario.cancel(true);
        }
        responseFutureGetUsuario = interactor.getUsuario(idUsuario).onData(new OnData<Usuario>() {
            @Override
            public void onData(Usuario usuario) {
                presenterStatus.setUsuario(usuario);
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
