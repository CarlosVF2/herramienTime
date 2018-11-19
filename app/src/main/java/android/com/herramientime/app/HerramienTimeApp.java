package android.com.herramientime.app;

import android.app.Application;
import android.com.herramientime.domain.ComponentDependencies;

/**
 * Created by carlo on 06/11/2018.
 */

public class HerramienTimeApp extends Application {

    private static ComponentDependencies componentDependencies;

    @Override
    public void onCreate() {
        super.onCreate();
        this.setupDependencyInjection();
    }

    private void setupDependencyInjection() {
        componentDependencies = new ComponentDependencies(this);
    }

    public static ComponentDependencies getComponentDependencies() {
        return componentDependencies;
    }
}
