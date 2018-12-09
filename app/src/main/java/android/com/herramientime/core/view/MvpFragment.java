package android.com.herramientime.core.view;

public interface MvpFragment {


    void onInitLoading();

    void onLoaded();

    void onLoadError(String error);

    void setTitle(String title);

    void showProgressDialogWithMessage(String message);

    void hideProgressDialog();
}
