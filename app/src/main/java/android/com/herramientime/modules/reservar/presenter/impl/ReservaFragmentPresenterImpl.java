package android.com.herramientime.modules.reservar.presenter.impl;

import android.com.herramientime.R;
import android.com.herramientime.app.HerramienTimeApp;
import android.com.herramientime.core.entities.ErrorCause;
import android.com.herramientime.core.presenter.impl.MvpFragmentPresenterImpl;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.herramientime.modules.reservar.entities.ReservaFragmentPresenterStatus;
import android.com.herramientime.modules.reservar.injection.ReservaFragmentComponent;
import android.com.herramientime.modules.reservar.interactor.ReservaFragmentInteractor;
import android.com.herramientime.modules.reservar.presenter.ReservaFragmentPresenter;
import android.com.herramientime.modules.reservar.view.ReservaFragment;
import android.com.rest.utils.Utilidades;
import android.content.res.Resources;
import android.os.Bundle;

import com.seidor.core.di.annotations.Inject;
import com.seidor.core.task.executor.future.OnCompleted;
import com.seidor.core.task.executor.future.OnData;
import com.seidor.core.task.executor.future.OnError;
import com.seidor.core.task.executor.future.ResponseFuture;

import java.util.Calendar;
import java.util.Date;

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
    private ResponseFuture<Herramienta> responseFutureHerramienta;
    private ResponseFuture<Experiencia> responseFutureExperiencia;
    private ResponseFuture<Boolean> responseFutureSave;

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
        super.onViewBinded();
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
        startResponseGetHerramienta();
        startResponseGetExperiencia();
    }

    @Override
    public void onDestroy() {
        if (responseFutureHerramienta != null) {
            responseFutureHerramienta.cancel(true);
        }
        if (responseFutureExperiencia != null) {
            responseFutureExperiencia.cancel(true);
        }
        if (responseFutureSave != null) {
            responseFutureSave.cancel(true);
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
                if (presenterStatus.getHerramienta() != null) {
                    fragment.setDescripcionText(presenterStatus.getHerramienta().getDescripcion());
                    fragment.setHintText("Herramienta");
                } else if (presenterStatus.getExperiencia() != null) {
                    fragment.setDescripcionText(presenterStatus.getExperiencia().getDescripcion());
                    fragment.setHintText("Experiencia");
                } else {
                    fragment.onLoadError("No se ha podido recuperar ningun valor para la reserva");
                }
                fragment.onLoaded();
            }
        }
    }

    @Override
    public boolean isLoadingFinish() {
        if (responseFutureExperiencia == null || !responseFutureExperiencia.isDone()) {
            return false;
        }
        if (responseFutureHerramienta == null || !responseFutureHerramienta.isDone()) {
            return false;
        }
        return true;
    }

    @Override
    public void onClickFechaInicial() {
        Date basicInitDate = presenterStatus.getFechaInicial();
        Date fechaElegida;
        if (basicInitDate != null) {
            fechaElegida = basicInitDate;
        } else {
            fechaElegida = Calendar.getInstance().getTime();
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(fechaElegida);
        getMvpFragment().showDatePickerFechaInicial(instance);
    }

    @Override
    public void onClickFechaFinal() {
        Date basicFinishDate = presenterStatus.getFechaFinal();
        Date fechaElegida;
        if (basicFinishDate != null) {
            fechaElegida = basicFinishDate;
        } else {
            fechaElegida = Calendar.getInstance().getTime();
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(fechaElegida);
        getMvpFragment().showDatePickerFechaFinal(instance);
    }

    @Override
    public void onFechaInicialSelected(Date time) {
        presenterStatus.setFechaInicial(time);
        FRAGMENT fragment = getMvpFragment();
        if (fragment != null) {
            fragment.setFechaInicial((time == null ? "" : Utilidades.getStringFormatddMMyyyyGuiones(time)));
        }
    }

    @Override
    public void onFechaFinalSelected(Date time) {
        presenterStatus.setFechaFinal(time);
        FRAGMENT fragment = getMvpFragment();
        if (fragment != null) {
            fragment.setFechaFinal((time == null ? "" : Utilidades.getStringFormatddMMyyyyGuiones(time)));
        }
    }

    @Override
    public void onClickConfirmar() {
        startResponseFutureSave();
    }

    //region ResponseFuture


    private void startResponseGetExperiencia() {
        if (responseFutureExperiencia != null) {
            responseFutureExperiencia.cancel(true);
        }
        responseFutureExperiencia = reservaFragmentInteractor.getExperienciaById(presenterStatus.getIdExperiencia()).onData(new OnData<Experiencia>() {
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

    private void startResponseGetHerramienta() {
        if (responseFutureHerramienta != null) {
            responseFutureHerramienta.cancel(true);
        }
        responseFutureHerramienta = reservaFragmentInteractor.getHerramientaById(presenterStatus.getIdHerramienta()).onData(new OnData<Herramienta>() {
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

            }
        });
    }

    private void startResponseFutureSave() {
        if (responseFutureSave != null) {
            responseFutureSave.cancel(true);
        }
        responseFutureSave = reservaFragmentInteractor.save(presenterStatus.getExperiencia(), presenterStatus.getHerramienta(), presenterStatus.getFechaInicial(), presenterStatus.getFechaFinal()).onData(new OnData<Boolean>() {
            @Override
            public void onData(Boolean aBoolean) {
                if (aBoolean) {
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
