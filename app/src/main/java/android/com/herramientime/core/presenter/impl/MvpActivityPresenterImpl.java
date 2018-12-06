package android.com.herramientime.core.presenter.impl;

import android.com.herramientime.core.presenter.MvpActivityPresenter;
import android.com.herramientime.core.view.MvpActivity;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

public abstract class MvpActivityPresenterImpl<ACTIVITY extends MvpActivity> implements MvpActivityPresenter {

    private ACTIVITY mvpActivity;

    @Override
    public abstract void onViewBinded();

    @Override
    public void onViewUnbinded(){
        mvpActivity = null;
    }

    @Override
    public void onDestroy(){
        mvpActivity = null;
    }

    @Override
    public abstract boolean isLoadingFinish();

    @Override
    public void setMvpActivity(@NonNull MvpActivity mvpActivity) {
        this.mvpActivity = (ACTIVITY) mvpActivity;
    }

    @Override
    public ACTIVITY getMvpActivity() {
        return mvpActivity;
    }
}
