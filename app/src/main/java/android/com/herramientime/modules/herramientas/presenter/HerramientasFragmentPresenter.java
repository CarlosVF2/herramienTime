package android.com.herramientime.modules.herramientas.presenter;

import android.com.herramientime.core.presenter.MvpFragmentPresenter;

/**
 * Created by carlo on 06/11/2018.
 */

public interface HerramientasFragmentPresenter extends MvpFragmentPresenter {
    void onRefresh();

    void onClickSubirHerramienta();

}
