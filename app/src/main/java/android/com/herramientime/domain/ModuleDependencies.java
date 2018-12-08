package android.com.herramientime.domain;

import android.app.Application;
import android.com.herramientime.injection.Constants;
import android.com.herramientime.injection.InteractorFactory;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.modules.domain.injection.MainActivityModule;
import android.com.herramientime.modules.experiencias.injection.ExperienciaDetalleFragmentModule;
import android.com.herramientime.modules.experiencias.injection.ExperienciasFragmentModule;
import android.com.herramientime.modules.herramientas.injection.HerramientaDetalleFragmentModule;
import android.com.herramientime.modules.herramientas.injection.HerramientasFragmentModule;
import android.content.res.Resources;


/**
 * Created by carlo on 06/11/2018.
 */

public abstract class ModuleDependencies extends DependencyInjectionImpl {

    private MainActivityModule mainActivityModule;
    private HerramientasFragmentModule herramientasFragmentModule;
    private HerramientaDetalleFragmentModule herramientaDetalleFragmentModule;
    private ExperienciasFragmentModule experienciasFragmentModule;
    private ExperienciaDetalleFragmentModule experienciaDetalleFragmentModule;

    public ModuleDependencies(Application application) {
        super(application);
        setupMainActivityModule(getConstantsInstance(), getInteractorFactoryInstance(), getNavigationManager());
        setupHerramientasFragmentModule(getNavigationManager(), getInteractorFactoryInstance());
        setupHerramientaDetalleFragmentModule(getInteractorFactoryInstance());
        setupExperienciasFragmentModule(getNavigationManager(), getInteractorFactoryInstance(), getApplicationContext().getResources());
        setupExperienciaDetalleFragmentModule(getInteractorFactoryInstance());

    }

    //region setup

    private void setupExperienciaDetalleFragmentModule(InteractorFactory interactorFactoryInstance) {
        experienciaDetalleFragmentModule = new ExperienciaDetalleFragmentModule(interactorFactoryInstance);
    }

    private void setupExperienciasFragmentModule(NavigationManager navigationManager, InteractorFactory interactorFactoryInstance, Resources resources) {
        experienciasFragmentModule = new ExperienciasFragmentModule(navigationManager, interactorFactoryInstance, resources);
    }

    private void setupMainActivityModule(Constants constants, InteractorFactory interactorFactory, NavigationManager navigationManager) {
        mainActivityModule = new MainActivityModule(constants, interactorFactory, navigationManager);
    }

    private void setupHerramientasFragmentModule(NavigationManager navigationManager, InteractorFactory interactorFactory) {
        herramientasFragmentModule = new HerramientasFragmentModule(navigationManager, interactorFactory);
    }

    private void setupHerramientaDetalleFragmentModule(InteractorFactory interactorFactory) {
        herramientaDetalleFragmentModule = new HerramientaDetalleFragmentModule(interactorFactory);
    }

    //endregion setup

    //region GET

    protected MainActivityModule getMainActivityModule() {
        return mainActivityModule;
    }

    protected HerramientasFragmentModule getHerramientasFragmentModule() {
        return herramientasFragmentModule;
    }

    protected HerramientaDetalleFragmentModule getHerramientaDetalleFragmentModule(){
        return herramientaDetalleFragmentModule;
    }

    protected ExperienciasFragmentModule getExperienciasFragmentModule() {
        return experienciasFragmentModule;
    }

    protected ExperienciaDetalleFragmentModule getExperienciaDetalleFragmentModule() {
        return experienciaDetalleFragmentModule;
    }

    //endregion GET
}
