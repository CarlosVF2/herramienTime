package android.com.herramientime.modules.login.injection;

/**
 * Created by carlo on 06/11/2018.
 */

public class LoginFragmentComponent {

    private LoginFragmentModule loginFragmentModule;

    public LoginFragmentComponent(LoginFragmentModule loginFragmentModule) {
        this.loginFragmentModule = loginFragmentModule;
    }

    public LoginFragmentModule getLoginFragmentModule() {
        return loginFragmentModule;
    }

}
