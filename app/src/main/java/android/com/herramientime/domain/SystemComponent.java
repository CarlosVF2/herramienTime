package android.com.herramientime.domain;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ComponentDependencies.class})
public interface SystemComponent {
    void inject(ComponentDependencies componentDependencies);
}
