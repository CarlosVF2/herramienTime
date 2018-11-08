package android.com.herramientime.core.presenter.impl;

import android.com.herramientime.core.presenter.MvpActivityPresenter;
import android.com.herramientime.core.view.MvpActivity;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

public class MvpActivityPresenterImpl<ACTIVITY extends MvpActivity> implements MvpActivityPresenter {

    private ACTIVITY mvpActivity;

    @Override
    @CallSuper
    public void onViewBinded() {

    }

    @Override
    @CallSuper
    public void onViewUnbinded() {

    }

    @Override
    @CallSuper
    public void onDestroy() {

    }

    @Override
    public void setMvpActivity(@NonNull MvpActivity mvpActivity) {
        this.mvpActivity = (ACTIVITY) mvpActivity;
    }

    @Override
    public ACTIVITY getMvpActivity() {
        return mvpActivity;
    }
}
