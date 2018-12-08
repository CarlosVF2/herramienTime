package android.com.herramientime.core.view.impl;

import android.com.herramientime.core.presenter.MvpFragmentPresenter;
import android.com.herramientime.core.view.MvpFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import static android.com.herramientime.core.presenter.MvpPresenterArgument.EXTRA_PRESENTER_DEFAULT;

public abstract class MvpFragmentImpl<PRESENTER extends MvpFragmentPresenter> extends Fragment implements MvpFragment {

    private PRESENTER mvpPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMvpPresenter(getArguments());
    }

    public void onResume() {
        super.onResume();
        this.setMvpFragmentPresenter(this.mvpPresenter);
        this.mvpPresenter.onViewBinded();
    }

    @Override
    public void onPause() {
        getMvpPresenter().onViewUnbinded();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        getMvpPresenter().onDestroy();
        super.onDestroy();
    }

    private void setMvpPresenter(Bundle args) {
        try {
            Class c = Class.forName(args.getString(EXTRA_PRESENTER_DEFAULT));
            setMvpFragmentPresenter((PRESENTER) c.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMvpFragmentPresenter(@NonNull MvpFragmentPresenter mvpActivityPresenter) {
        this.mvpPresenter = (PRESENTER) mvpActivityPresenter;
        this.mvpPresenter.setMvpFragment(this);
        this.mvpPresenter.setParams(this.getArguments() != null ? new Bundle(this.getArguments()) : new Bundle(new Bundle()));
    }

    protected PRESENTER getMvpPresenter() {
        return mvpPresenter;
    }
}
