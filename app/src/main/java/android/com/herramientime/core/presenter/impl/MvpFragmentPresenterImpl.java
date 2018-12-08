package android.com.herramientime.core.presenter.impl;

import android.com.herramientime.core.presenter.MvpFragmentPresenter;
import android.com.herramientime.core.presenter.MvpPresenterArgument;
import android.com.herramientime.core.view.MvpFragment;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

public abstract class MvpFragmentPresenterImpl<VIEW extends MvpFragment> implements MvpFragmentPresenter, MvpPresenterArgument {

    private VIEW mvpFragment;

    @CallSuper
    public Bundle setArguments(Bundle args) {
        args.putString(EXTRA_PRESENTER_DEFAULT, getClass().getName());
        return args;
    }

    @Override
    public abstract void onViewBinded();

    @Override
    public void onViewUnbinded(){
        mvpFragment = null;
    }

    @Override
    public void onDestroy(){
        mvpFragment = null;
    }

    @Override
    public abstract boolean isLoadingFinish();

    @Override
    public void setMvpFragment(@NonNull MvpFragment mvpFragment) {
        this.mvpFragment = (VIEW) mvpFragment;
    }

    @Override
    public void setParams(Bundle bundle) {

    }

    public VIEW getMvpFragment(){
        return mvpFragment;
    }
}
