package android.com.herramientime.modules.domain.presenter;

import android.com.herramientime.core.presenter.MvpActivityPresenter;

/**
 * Created by carlo on 06/11/2018.
 */

public interface MainActivityPresenter extends MvpActivityPresenter {
    void onBackPressed();

    void exitConfirm();

    //region Click Navigation Item

    void navigationExperienciasClicked();

    void navigationHelpClicked();

    void navigationSettingsClicked();

    void navigationHerramientasClicked();

    //endregion Click Navigation Item

    void onClickIniciarSesion();

    void onClickImageUser();
}
