package android.com.herramientime.core.view.impl;

import android.annotation.SuppressLint;
import android.com.herramientime.core.presenter.MvpActivityPresenter;
import android.com.herramientime.core.view.MvpActivity;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class MvpActivityImpl<PRESENTER extends MvpActivityPresenter> extends AppCompatActivity implements MvpActivity {

    private PRESENTER mvpPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.setMvpActivityPresenter(this.mvpPresenter);
        this.mvpPresenter.onViewBinded();
    }

    @SuppressLint("WrongConstant")
    private void init() {
        if (getMvpActivityPresenter() == null) {
            ActivityInfo ai = null;
            try {
                ai = this.getPackageManager().getActivityInfo(this.getComponentName(), PackageManager.GET_ACTIVITIES | PackageManager.GET_META_DATA);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            String presenterName = ai.metaData.getString(PRESENTER.EXTRA_PRESENTER_DEFAULT);
            try {
                Class c = Class.forName(presenterName);
                setMvpActivityPresenter((PRESENTER) c.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setMvpActivityPresenter(@NonNull MvpActivityPresenter mvpActivityPresenter) {
        this.mvpPresenter = (PRESENTER) mvpActivityPresenter;
        this.mvpPresenter.setMvpActivity(this);
    }

    public PRESENTER getMvpActivityPresenter() {
        return mvpPresenter;
    }

    @Override
    @CallSuper
    public void onInitLoading() {

    }

    @Override
    @CallSuper
    public void onLoaded() {

    }

    @Override
    @CallSuper
    public void onLoadError(String s) {

    }
}
