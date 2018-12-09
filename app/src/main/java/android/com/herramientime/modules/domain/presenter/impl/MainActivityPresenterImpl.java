package android.com.herramientime.modules.domain.presenter.impl;

import android.com.herramientime.app.HerramienTimeApp;
import android.com.herramientime.core.entities.ErrorCause;
import android.com.herramientime.core.presenter.impl.MvpActivityPresenterImpl;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.injection.impl.NavigationManagerImpl;
import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.domain.entities.MainActivityPresenterStatus;
import android.com.herramientime.modules.domain.injection.MainActivityComponent;
import android.com.herramientime.modules.domain.interactor.MainActivityInteractor;
import android.com.herramientime.modules.domain.presenter.MainActivityPresenter;
import android.com.herramientime.modules.domain.view.MainActivity;
import android.com.herramientime.modules.usuarios.entities.Usuario;
import android.content.Intent;

import com.seidor.core.di.annotations.Inject;
import com.seidor.core.task.executor.future.OnCompleted;
import com.seidor.core.task.executor.future.OnData;
import com.seidor.core.task.executor.future.OnError;
import com.seidor.core.task.executor.future.ResponseFuture;

/**
 * Created by carlo on 06/11/2018.
 */

public class MainActivityPresenterImpl<VIEW extends MainActivity> extends MvpActivityPresenterImpl<VIEW>
        implements MainActivityPresenter, NavigationManagerImpl.NavigationListener {

    private NavigationManager navigationManager;
    private MainActivityInteractor activityInteractor;

    private ResponseFuture<Usuario> responseFutureUsuario;
    private MainActivityPresenterStatus presenterStatus = new MainActivityPresenterStatus();

    public static void newMainActivityPresenterInstance(Intent intent) {
        MainActivityPresenterImpl presenter = new MainActivityPresenterImpl();
        presenter.setupDependencies(intent);
    }

    protected void setupDependencies(Intent intent) {
        //super.setupDependencies(intentWrapper);
        //configureIntent(intentWrapper);
    }

    public MainActivityPresenterImpl() {
        super();
    }

    @Inject
    public MainActivityPresenterImpl(MainActivityComponent activityComponent) {
        activityInteractor = activityComponent.getMainActivityModule().getActivityInteractor();
        navigationManager = activityComponent.getMainActivityModule().getNavigationManager();
        navigationManager.setNavigationListener(this);
        try {
            if (!navigationManager.isFragmentAttached()) {
                navigationManager.navigateToHerramientas();
            }
        } catch (LocalException ignored) {
            // never can happen
        }
    }

    //region Lyfecicle core

    @Override
    public void onViewBinded() {
        if(navigationManager == null){
            navigationManager = HerramienTimeApp.getComponentDependencies().getNavigationManager();
            navigationManager.setNavigationListener(this);
        }
        if (activityInteractor == null) {
            activityInteractor = HerramienTimeApp.getComponentDependencies().getMainActivityComponent().getMainActivityModule().getActivityInteractor();
        }
        try {
            if (!navigationManager.isFragmentAttached()) {
                navigationManager.navigateToHerramientas();
            }
        } catch (LocalException ignored) {
            // never can happen
        }
        startResponseGetDatosUser();
    }

    @Override
    public void onViewUnbinded() {

    }

    @Override
    public void onDestroy() {
        if (responseFutureUsuario != null) {
            responseFutureUsuario.cancel(true);
        }
    }

    @Override
    public void onDataLoaded() {
        if (isLoadingFinish()) {
            VIEW view = getMvpActivity();
            if (view != null) {
                if (presenterStatus.getError() != null) {
                    //Si no se habia representado el error (porque no habia vista viva en ese momento) se representa una vez que sea ejecutable.
                    view.onLoadError(ErrorCause.getCause(presenterStatus.getError()));
                    presenterStatus.setError(null);
                    return;
                }
                view.refreshMenu();
                if (presenterStatus.getUsuario() != null) {
                    view.setIDUsuarioText(presenterStatus.getUsuario().getId());
                    view.setNombreUsuarioText(presenterStatus.getUsuario().getNombre());
                    view.setButtonIniciarSesionVisibility(false);
                    view.setDatosUsuarioVisibility(true);
                } else {
                    view.setButtonIniciarSesionVisibility(true);
                    view.setDatosUsuarioVisibility(false);
                }
            }
        }
    }

    @Override
    public boolean isLoadingFinish() {
        if (responseFutureUsuario == null || !responseFutureUsuario.isDone()) {
            return false;
        }
        return true;
    }

    //endregion Lyfecicle core

    @Override
    public void onBackPressed() {

        try {
            if (navigationManager.isRootFragment()) {
                getMvpActivity().showMessageExitConfirm();
            } else {
                navigationManager.navigateBack();
            }
        } catch (LocalException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exitConfirm() {
        try {
            navigationManager.navigateBack();
        } catch (LocalException e) {
            e.printStackTrace();
        }
    }

    //region Click Navigation Item
    @Override
    public void navigationExperienciasClicked() {
        VIEW view = getMvpActivity();
        if (view != null) {
            view.closeDrawer();
        }
        try {
            navigationManager.navigateToExperiencias();
        } catch (LocalException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void navigationHelpClicked() {
        VIEW view = getMvpActivity();
        if (view != null) {
            view.closeDrawer();
        }
    }

    @Override
    public void navigationSettingsClicked() {
        VIEW view = getMvpActivity();
        if (view != null) {
            view.closeDrawer();
        }

    }

    @Override
    public void navigationHerramientasClicked() {
        VIEW view = getMvpActivity();
        if (view != null) {
            view.closeDrawer();
        }
        try {
            navigationManager.navigateToHerramientas();
        } catch (LocalException e) {
            e.printStackTrace();
        }
    }
    //endregion Click Navigation Item

    @Override
    public void onClickIniciarSesion() {
        VIEW view = getMvpActivity();
        if (view != null) {
            view.closeDrawer();
        }
        try {
            navigationManager.navigateToLogin();
        } catch (LocalException e) {
            e.printStackTrace();
        }
    }

    //region NavigationListener
    @Override
    public void onBackstackChanged() {
        //El contenido de la pila ha cambiado, por tanto recargamos visualmente
        VIEW activity = getMvpActivity();
        if (activity != null) {
            activity.refreshMenu();
        }
        startResponseGetDatosUser();
    }
    //endregion NavigationListener

    //region ResponseFuture
    private void startResponseGetDatosUser() {
        if (responseFutureUsuario != null) {
            responseFutureUsuario.cancel(true);
        }
        responseFutureUsuario = activityInteractor.getLoggedUser().onData(new OnData<Usuario>() {
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
