package android.com.herramientime.modules.usuarios.presenter.impl;

import android.com.herramientime.R;
import android.com.herramientime.app.HerramienTimeApp;
import android.com.herramientime.core.entities.ErrorCause;
import android.com.herramientime.core.presenter.impl.MvpFragmentPresenterImpl;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.usuarios.entities.Usuario;
import android.com.herramientime.modules.usuarios.entities.UsuarioDetalleFragmentPresenterStatus;
import android.com.herramientime.modules.usuarios.injection.UsuarioDetalleFragmentComponent;
import android.com.herramientime.modules.usuarios.interactor.UsuarioDetalleFragmentInteractor;
import android.com.herramientime.modules.usuarios.presenter.UsuarioDetalleFragmentPresenter;
import android.com.herramientime.modules.usuarios.view.UsuarioDetalleFragment;
import android.content.res.Resources;
import android.os.Bundle;

import com.seidor.core.di.annotations.Inject;
import com.seidor.core.task.executor.future.OnCompleted;
import com.seidor.core.task.executor.future.OnData;
import com.seidor.core.task.executor.future.OnError;
import com.seidor.core.task.executor.future.ResponseFuture;

/**
 * Created by carlo on 06/11/2018.
 */

public class UsuarioDetalleFragmentPresenterImpl<FRAGMENT extends UsuarioDetalleFragment> extends MvpFragmentPresenterImpl<FRAGMENT>
        implements UsuarioDetalleFragmentPresenter {

    private final String PARAM__ID_USER = "PARAM__ID_USER";

    private UsuarioDetalleFragmentInteractor usuarioDetalleFragmentInteractor;
    private Resources resources;
    private NavigationManager navigationManager;

    private UsuarioDetalleFragmentPresenterStatus presenterStatus = new UsuarioDetalleFragmentPresenterStatus();
    private ResponseFuture<Usuario> responseFutureGetUsuario;
    private ResponseFuture<Boolean> responseFutureCerrarSesion;

    public static void newUsuarioDetalleFragmentPresenterInstance(Bundle bundle, String idUsuario) {
        UsuarioDetalleFragmentPresenterImpl presenter = new UsuarioDetalleFragmentPresenterImpl();
        presenter.setupDependencies(bundle, idUsuario);
    }

    private void setupDependencies(Bundle bundle, String id) {
        setArguments(bundle);
        bundle.putString(PARAM__ID_USER, id);
    }

    @Override
    public void setParams(Bundle bundle) {
        if (presenterStatus.getIdUsuario() == null) {
            presenterStatus.setIdUsuario(bundle.getString(PARAM__ID_USER));
        }
    }

    public UsuarioDetalleFragmentPresenterImpl() {
    }

    @Inject
    public UsuarioDetalleFragmentPresenterImpl(UsuarioDetalleFragmentComponent usuarioDetalleFragmentComponent) {
        this.usuarioDetalleFragmentInteractor = usuarioDetalleFragmentComponent.getUsuarioDetalleFragmentModule().getUsuarioDetalleFragmentInteractor();
        this.resources = usuarioDetalleFragmentComponent.getUsuarioDetalleFragmentModule().getResources();
        this.navigationManager = usuarioDetalleFragmentComponent.getUsuarioDetalleFragmentModule().getNavigationManager();
    }

    @Override
    public void onViewBinded() {
        super.onViewBinded();
        if (resources == null) {
            resources = HerramienTimeApp.getComponentDependencies().getUsuarioDetalleFragmentComponent().getUsuarioDetalleFragmentModule().getResources();
        }
        if (usuarioDetalleFragmentInteractor == null) {
            usuarioDetalleFragmentInteractor = HerramienTimeApp.getComponentDependencies().getUsuarioDetalleFragmentComponent().getUsuarioDetalleFragmentModule().getUsuarioDetalleFragmentInteractor();
        }
        if (navigationManager == null) {
            navigationManager = HerramienTimeApp.getComponentDependencies().getUsuarioDetalleFragmentComponent().getUsuarioDetalleFragmentModule().getNavigationManager();
        }
        getMvpFragment().onInitLoading();
        startResponseGetUsuario();
    }

    @Override
    public void onDestroy() {
        if (responseFutureGetUsuario != null) {
            responseFutureGetUsuario.cancel(true);
        }
        super.onDestroy();
    }

    @Override
    public void onDataLoaded() {
        if (isLoadingFinish()) {
            super.onDataLoaded();
            FRAGMENT fragment = getMvpFragment();
            if (fragment != null) {
                fragment.setTitle(resources.getString(R.string.title_detalle_usuario));
                if (presenterStatus.getError() != null) {
                    //Si no se habia representado el error (porque no habia vista viva en ese momento) se representa una vez que sea ejecutable.
                    fragment.onLoadError(ErrorCause.getCause(presenterStatus.getError()));
                    presenterStatus.setError(null);
                    return;
                }
                fragment.setNombreApellidosUser(presenterStatus.getUsuario().getNombre() + " " + presenterStatus.getUsuario().getApellidos());
                fragment.setUsuario(presenterStatus.getUsuario().getId());
                fragment.setAcercaDeTi(presenterStatus.getUsuario().getAcercaDeTi());
                fragment.onLoaded();
            }
        }
    }

    @Override
    public boolean isLoadingFinish() {
        return true;
    }

    @Override
    public void onClickCerrarSesion() {
        startResponseCerrarSesion();
    }



    //region ResponseFuture

    private void startResponseCerrarSesion() {
        if (responseFutureCerrarSesion != null) {
            responseFutureCerrarSesion.cancel(true);
        }
        responseFutureCerrarSesion = usuarioDetalleFragmentInteractor.cerrarSesion().onData(new OnData<Boolean>() {
            @Override
            public void onData(Boolean usuario) {
                if (usuario) {
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

    private void startResponseGetUsuario() {
        if (responseFutureGetUsuario != null) {
            responseFutureGetUsuario.cancel(true);
        }
        responseFutureGetUsuario = usuarioDetalleFragmentInteractor.getUsuario(presenterStatus.getIdUsuario()).onData(new OnData<Usuario>() {
            @Override
            public void onData(Usuario usuario) {
                presenterStatus.setUsuario(usuario);
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
