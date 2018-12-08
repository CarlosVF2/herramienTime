package android.com.herramientime.domain;

import android.app.Application;
import android.com.herramientime.injection.Constants;
import android.com.herramientime.injection.InteractorFactory;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.modules.domain.injection.MainActivityModule;
import android.com.herramientime.modules.experiencias.injection.ExperienciaDetalleFragmentModule;
import android.com.herramientime.modules.experiencias.injection.ExperienciasFragmentModule;
import android.com.herramientime.modules.herramientas.injection.AlquilerHerramientaFragmentModule;
import android.com.herramientime.modules.herramientas.injection.HerramientaDetalleFragmentModule;
import android.com.herramientime.modules.herramientas.injection.HerramientasFragmentModule;
import android.com.herramientime.modules.reservar.injection.ReservaFragmentModule;
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
    private ReservaFragmentModule reservaFragmentModule;
    private AlquilerHerramientaFragmentModule alquilerHerramientaFragmentModule;

    public ModuleDependencies(Application application) {
        super(application);
        setupMainActivityModule(getConstantsInstance(), getInteractorFactoryInstance(), getNavigationManager());
        setupHerramientasFragmentModule(getNavigationManager(), getInteractorFactoryInstance());
        setupHerramientaDetalleFragmentModule(getInteractorFactoryInstance(), getNavigationManager());
        setupExperienciasFragmentModule(getNavigationManager(), getInteractorFactoryInstance(), getApplicationContext().getResources());
        setupExperienciaDetalleFragmentModule(getInteractorFactoryInstance());
        setupReservaFragmentModule(getNavigationManager(), getInteractorFactoryInstance(), getApplicationContext().getResources());
        setupAlquilerHerramientaFragmentModule(getNavigationManager(), getInteractorFactoryInstance(), getApplicationContext().getResources());
    }

    //region setup

    private void setupAlquilerHerramientaFragmentModule(NavigationManager navigationManager, InteractorFactory interactorFactoryInstance, Resources resources) {
        alquilerHerramientaFragmentModule = new AlquilerHerramientaFragmentModule(navigationManager, interactorFactoryInstance, resources);
    }

    private void setupReservaFragmentModule(NavigationManager navigationManager, InteractorFactory interactorFactoryInstance, Resources resources) {
        reservaFragmentModule = new ReservaFragmentModule(navigationManager, interactorFactoryInstance, resources);
    }

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

    private void setupHerramientaDetalleFragmentModule(InteractorFactory interactorFactory, NavigationManager navigationManager) {
        herramientaDetalleFragmentModule = new HerramientaDetalleFragmentModule(interactorFactory, navigationManager);
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

    protected ReservaFragmentModule getReservaFragmentModule() {
        return reservaFragmentModule;
    }

    protected AlquilerHerramientaFragmentModule getAlquilerHerramientaFragmentModule() {
        return alquilerHerramientaFragmentModule;
    }

    //endregion GET
}
