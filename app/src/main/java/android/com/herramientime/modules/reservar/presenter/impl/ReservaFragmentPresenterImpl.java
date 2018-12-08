package android.com.herramientime.modules.reservar.presenter.impl;

import android.com.herramientime.R;
import android.com.herramientime.app.HerramienTimeApp;
import android.com.herramientime.core.entities.ErrorCause;
import android.com.herramientime.core.presenter.impl.MvpFragmentPresenterImpl;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.modules.reservar.entities.ReservaFragmentPresenterStatus;
import android.com.herramientime.modules.reservar.injection.ReservaFragmentComponent;
import android.com.herramientime.modules.reservar.interactor.ReservaFragmentInteractor;
import android.com.herramientime.modules.reservar.presenter.ReservaFragmentPresenter;
import android.com.herramientime.modules.reservar.view.ReservaFragment;
import android.content.res.Resources;
import android.os.Bundle;

import com.seidor.core.di.annotations.Inject;

/**
 * Created by carlo on 06/11/2018.
 */

public class ReservaFragmentPresenterImpl<FRAGMENT extends ReservaFragment> extends MvpFragmentPresenterImpl<FRAGMENT>
        implements ReservaFragmentPresenter {

    private final String PARAM__ID_EXPER = "PARAM__ID_EXPER";
    private final String PARAM__ID_HERR = "PARAM__ID_HERR";

    private NavigationManager navigationManager;
    private Resources resources;
    private ReservaFragmentInteractor reservaFragmentInteractor;

    private ReservaFragmentPresenterStatus presenterStatus = new ReservaFragmentPresenterStatus();

    public static void newReservaFragmentPresenterInstance(Bundle bundle, String idExperiencia, String idHerramienta) {
        ReservaFragmentPresenterImpl presenter = new ReservaFragmentPresenterImpl();
        presenter.setArguments(bundle, idExperiencia, idHerramienta);
    }

    private void setArguments(Bundle bundle, String idExperiencia, String idHerramienta) {
        bundle.putString(PARAM__ID_EXPER, idExperiencia);
        bundle.putString(PARAM__ID_HERR, idHerramienta);
        super.setArguments(bundle);
    }

    @Override
    public void setParams(Bundle bundle) {
        super.setParams(bundle);
        if (presenterStatus.getIdExperiencia() == null) {
            presenterStatus.setIdExperiencia(bundle.getString(PARAM__ID_EXPER));
            presenterStatus.setIdHerramienta(bundle.getString(PARAM__ID_HERR));
        }
    }

    public ReservaFragmentPresenterImpl() {
    }

    @Inject
    public ReservaFragmentPresenterImpl(ReservaFragmentComponent reservaFragmentComponent) {
        this.navigationManager = reservaFragmentComponent.getReservaFragmentModule().getNavigationManager();
        this.reservaFragmentInteractor = reservaFragmentComponent.getReservaFragmentModule().getReservaFragmentInteractor();
        this.resources = reservaFragmentComponent.getReservaFragmentModule().getResources();
    }

    @Override
    public void onViewBinded() {
        if (navigationManager == null) {
            navigationManager = HerramienTimeApp.getComponentDependencies().getNavigationManager();
        }
        if (resources == null) {
            resources = HerramienTimeApp.getComponentDependencies().getReservaFragmentComponent().getReservaFragmentModule().getResources();
        }
        if (reservaFragmentInteractor == null) {
            reservaFragmentInteractor = HerramienTimeApp.getComponentDependencies().getReservaFragmentComponent().getReservaFragmentModule().getReservaFragmentInteractor();
        }
        getMvpFragment().onInitLoading();
        getMvpFragment().setTitle(resources.getString(R.string.title_reserva));
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

    //region ResponseFuture
    //endregion ResponseFuture
}
