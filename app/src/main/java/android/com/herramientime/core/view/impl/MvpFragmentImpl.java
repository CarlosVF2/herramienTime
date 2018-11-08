package android.com.herramientime.core.view.impl;

import android.com.herramientime.core.presenter.MvpFragmentPresenter;
import android.com.herramientime.core.view.MvpFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public class MvpFragmentImpl<PRESENTER extends MvpFragmentPresenter> extends Fragment implements MvpFragment {

    PRESENTER mvpPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
