package android.com.herramientime.core.view;

public interface MvpActivity {

    //region core view visibility management

    public void onInitLoading();

    public void onLoaded();

    public void onLoadError(String s);

}
