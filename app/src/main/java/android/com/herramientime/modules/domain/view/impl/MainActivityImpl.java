package android.com.herramientime.modules.domain.view.impl;

import android.com.herramientime.R;
import android.com.herramientime.core.view.impl.MvpActivityImpl;
import android.com.herramientime.modules.domain.presenter.MainActivityPresenter;
import android.com.herramientime.modules.domain.view.MainActivity;
import android.os.Bundle;

/**
 * Created by carlo on 06/11/2018.
 */

public class MainActivityImpl
        <PRESENTER extends MainActivityPresenter> extends MvpActivityImpl<PRESENTER>
        implements MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //region Lifecycle core

    @Override
    public void onInitLoading() {
        super.onInitLoading();
    }

    @Override
    public void onLoaded() {
        super.onLoaded();
    }

    @Override
    public void onLoadError(String s) {
        super.onLoadError(s);
    }

    //endregion Lifecycle core


    @Override
    public void onBackPressed() {
        getMvpActivityPresenter().onBackPressed();
    }
}
