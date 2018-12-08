package android.com.herramientime.modules.domain.view.impl;

import android.com.herramientime.R;
import android.com.herramientime.core.view.impl.MvpActivityImpl;
import android.com.herramientime.modules.domain.presenter.MainActivityPresenter;
import android.com.herramientime.modules.domain.view.MainActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

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

    @Override
    public void showMessageExitConfirm() {

        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.title_dialog_atencion))
                .setMessage(getString(R.string.promt_message_exit))
                .setPositiveButton(getString(android.R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        PRESENTER presenter = getMvpActivityPresenter();
                        if (presenter != null) {
                            presenter.exitConfirm();
                        }


                    }
                })
                .setNegativeButton(getString(android.R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();
    }
}
