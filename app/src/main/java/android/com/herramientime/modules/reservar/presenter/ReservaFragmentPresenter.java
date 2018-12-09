package android.com.herramientime.modules.reservar.presenter;

import android.com.herramientime.core.presenter.MvpFragmentPresenter;

import java.util.Date;

/**
 * Created by carlo on 06/11/2018.
 */

public interface ReservaFragmentPresenter extends MvpFragmentPresenter {
    void onClickFechaInicial();

    void onClickFechaFinal();

    void onFechaInicialSelected(Date time);

    void onFechaFinalSelected(Date time);

    void onClickConfirmar();
}
