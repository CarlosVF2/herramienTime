package android.com.herramientime.core.presenter.impl;

import android.com.herramientime.core.presenter.MvpFragmentPresenter;
import android.com.herramientime.core.presenter.MvpPresenterArgument;
import android.com.herramientime.core.view.MvpFragment;
import android.os.Bundle;
import android.support.annotation.CallSuper;

public class MvpFragmentPresenterImpl<VIEW extends MvpFragment> implements MvpFragmentPresenter, MvpPresenterArgument {

    @CallSuper
    public Bundle setArguments(Bundle args) {
        args.putString(EXTRA_PRESENTER_DEFAULT, getClass().getName());
        return args;
    }

    @Override
    public void onViewBinded() {

    }

    @Override
    public void onViewUnbinded() {

    }

    @Override
    public void onDestroy() {

    }
}
