package android.com.herramientime.modules.experiencias.presenter;

import android.com.herramientime.core.presenter.MvpFragmentPresenter;

/**
 * Created by carlo on 06/11/2018.
 */

public interface ExperienciasFragmentPresenter extends MvpFragmentPresenter {
    void onRefresh();

    void onClickActionSubirExperiencia();

    void onClickFilter();

    void onClickAplicarFilter();

    void onClickRestaurarFilter();

    void onClickAceptarLogin();
}
