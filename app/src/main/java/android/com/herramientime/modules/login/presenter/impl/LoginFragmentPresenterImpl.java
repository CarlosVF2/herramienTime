package android.com.herramientime.modules.login.presenter.impl;

import android.com.herramientime.app.HerramienTimeApp;
import android.com.herramientime.core.entities.ErrorCause;
import android.com.herramientime.core.presenter.impl.MvpFragmentPresenterImpl;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.login.entities.LoginFragmentPresenterStatus;
import android.com.herramientime.modules.login.injection.LoginFragmentComponent;
import android.com.herramientime.modules.login.interactor.LoginFragmentInteractor;
import android.com.herramientime.modules.login.presenter.LoginFragmentPresenter;
import android.com.herramientime.modules.login.view.LoginFragment;
import android.com.herramientime.modules.usuarios.entities.Usuario;
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

public class LoginFragmentPresenterImpl<FRAGMENT extends LoginFragment> extends MvpFragmentPresenterImpl<FRAGMENT>
        implements LoginFragmentPresenter {

    private LoginFragmentInteractor loginFragmentInteractor;
    private Resources resources;
    private NavigationManager navigationManager;

    private LoginFragmentPresenterStatus presenterStatus = new LoginFragmentPresenterStatus();
    private ResponseFuture<Usuario> responseFutureUsuario;
    private ResponseFuture<Usuario> responseFutureRegistrarUsuario;

    public static void newLoginFragmentPresenterInstance(Bundle bundle) {
        LoginFragmentPresenterImpl presenter = new LoginFragmentPresenterImpl();
        presenter.setArguments(bundle);
    }

    public LoginFragmentPresenterImpl() {
    }

    @Inject
    public LoginFragmentPresenterImpl(LoginFragmentComponent loginFragmentComponent) {
        this.loginFragmentInteractor = loginFragmentComponent.getLoginFragmentModule().getLoginFragmentInteractor();
        this.resources = loginFragmentComponent.getLoginFragmentModule().getResources();
        this.navigationManager = loginFragmentComponent.getLoginFragmentModule().geNavigationManager();
    }

    //region Core

    @Override
    public void onViewBinded() {
        super.onViewBinded();
        if (loginFragmentInteractor == null) {
            loginFragmentInteractor = HerramienTimeApp.getComponentDependencies().getLoginFragmentComponent().getLoginFragmentModule().getLoginFragmentInteractor();
        }
        if (resources == null) {
            resources = HerramienTimeApp.getComponentDependencies().getLoginFragmentComponent().getLoginFragmentModule().getResources();
        }
        if (navigationManager == null) {
            navigationManager = HerramienTimeApp.getComponentDependencies().getLoginFragmentComponent().getLoginFragmentModule().geNavigationManager();
        }
        getMvpFragment().onInitLoading();
        onDataLoaded();
    }

    @Override
    public void onViewUnbinded() {
        super.onViewUnbinded();
    }

    @Override
    public void onDestroy() {
        if (responseFutureRegistrarUsuario != null) {
            responseFutureRegistrarUsuario.cancel(true);
        }
        if (responseFutureUsuario != null) {
            responseFutureUsuario.cancel(true);
        }
        super.onDestroy();
    }

    @Override
    public void onDataLoaded() {
        if (isLoadingFinish()) {
            super.onDataLoaded();
            FRAGMENT fragment = getMvpFragment();
            if (fragment != null) {
                fragment.setTitle("Login");
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

    //endregion  Core

    @Override
    public void onClickIniciarSesion() {
        startResponseIniciarSesion();
    }

    @Override
    public void onClickRegistrarse() {
        if (!presenterStatus.isRegistrar()) {
            presenterStatus.setRegistrar(true);
            FRAGMENT fragment = getMvpFragment();
            if (fragment != null) {
                fragment.setNombreVisibility(true);
                fragment.setApellidosVisibility(true);
                fragment.setAcercaDeTiVisibility(true);
                fragment.setButtonIniciarSesionVisibility(false);
            }
        } else {
            startResponseRegistrar();
        }
    }

    //region set fields
    @Override
    public void setNombre(String nombre) {
        presenterStatus.getLogin().setNombre(nombre);
    }

    @Override
    public void setApellidos(String apellidos) {
        presenterStatus.getLogin().setApellido(apellidos);
    }

    @Override
    public void setUsuario(String usuario) {
        presenterStatus.getLogin().setUser(usuario);
    }

    @Override
    public void setPassword(String password) {
        presenterStatus.getLogin().setPassword(password);
    }

    @Override
    public void setAcercaDeTi(String acercaDeTi) {
        presenterStatus.getLogin().setAcercaDeTi(acercaDeTi);
    }

    //endregion  set fields

    //region ResponseFuture
    private void startResponseIniciarSesion() {
        if (responseFutureUsuario != null) {
            responseFutureUsuario.cancel(true);
        }
        FRAGMENT fragment = getMvpFragment();
        if (fragment != null) {
            fragment.showProgressDialogWithMessage("Iniciando sesion...");
        }
        responseFutureUsuario = loginFragmentInteractor.iniciarSesion(presenterStatus.getLogin()).onData(new OnData<Usuario>() {
            @Override
            public void onData(Usuario usuario) {
                if (usuario != null) {
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
                FRAGMENT fragment = getMvpFragment();
                if (fragment != null) {
                    fragment.hideProgressDialog();
                }
            }
        });

    }

    private void startResponseRegistrar() {
        if (responseFutureRegistrarUsuario != null) {
            responseFutureRegistrarUsuario.cancel(true);
        }
        FRAGMENT fragment = getMvpFragment();
        if (fragment != null) {
            fragment.showProgressDialogWithMessage("Registrando usuario...");
        }
        responseFutureRegistrarUsuario = loginFragmentInteractor.registrar(presenterStatus.getLogin()).onData(new OnData<Usuario>() {
            @Override
            public void onData(Usuario usuario) {
                if (usuario != null) {
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
                FRAGMENT fragment = getMvpFragment();
                if (fragment != null) {
                    fragment.hideProgressDialog();
                }
            }
        });

    }
    //endregion ResponseFuture
}
