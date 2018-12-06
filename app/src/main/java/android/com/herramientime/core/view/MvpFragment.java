package android.com.herramientime.core.view;

public interface MvpFragment {


    void onInitLoading();

    void onLoaded();

    void onLoadError(String error);
}
