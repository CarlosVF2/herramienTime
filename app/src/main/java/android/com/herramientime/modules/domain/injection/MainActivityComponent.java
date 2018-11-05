package android.com.herramientime.modules.domain.injection;

/**
 * Created by carlo on 06/11/2018.
 */

public class MainActivityComponent{
    private MainActivityModule mainActivityModule;

    public MainActivityComponent(MainActivityModule mainActivityModule) {
        this.mainActivityModule = mainActivityModule;
    }

    public MainActivityModule getMainActivityModule() {
        return mainActivityModule;
    }

}
