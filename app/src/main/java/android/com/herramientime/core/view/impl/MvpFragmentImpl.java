package android.com.herramientime.core.view.impl;

import android.app.ProgressDialog;
import android.com.herramientime.R;
import android.com.herramientime.core.presenter.MvpFragmentPresenter;
import android.com.herramientime.core.view.MvpFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import static android.com.herramientime.core.presenter.MvpPresenterArgument.EXTRA_PRESENTER_DEFAULT;

public abstract class MvpFragmentImpl<PRESENTER extends MvpFragmentPresenter> extends Fragment implements MvpFragment {

    private PRESENTER mvpPresenter;
    private ProgressDialog progressDialog;

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
        //getMvpPresenter().onViewUnbinded();
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

    //region core lifecycle

    @Override
    public void onLoadError(String error) {
        new AlertDialog.Builder(getContext())
                .setTitle(getString(R.string.title_dialog_atencion))
                .setMessage(error)
                .setPositiveButton(getString(R.string.prompt_aceptar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                    }
                })
                .create().show();
    }

    //endregion core lifecycle

    @Override
    public void setTitle(String title) {
        getActivity().setTitle(title);
    }

    @Override
    public void showProgressDialogWithMessage(String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getContext());
        }
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setTitle(getText(R.string.message_loading));
        progressDialog.setMessage(message);
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
