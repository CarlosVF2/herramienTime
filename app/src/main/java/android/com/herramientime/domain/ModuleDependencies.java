package android.com.herramientime.domain;

import android.app.Application;
import android.com.herramientime.injection.Constants;
import android.com.herramientime.injection.InteractorFactory;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.modules.domain.injection.MainActivityModule;
import android.com.herramientime.modules.experiencias.injection.AlquilerExperienciaFragmentModule;
import android.com.herramientime.modules.experiencias.injection.ExperienciaDetalleFragmentModule;
import android.com.herramientime.modules.experiencias.injection.ExperienciasFragmentModule;
import android.com.herramientime.modules.herramientas.injection.AlquilerHerramientaFragmentModule;
import android.com.herramientime.modules.herramientas.injection.HerramientaDetalleFragmentModule;
import android.com.herramientime.modules.herramientas.injection.HerramientasFragmentModule;
import android.com.herramientime.modules.login.injection.LoginFragmentModule;
import android.com.herramientime.modules.map.injection.MapFragmentModule;
import android.com.herramientime.modules.reservar.injection.ReservaFragmentModule;
import android.content.res.Resources;


/**
 * Created by carlo on 06/11/2018.
 */

public class ModuleDependencies extends DependencyInjectionImpl {

    private MainActivityModule mainActivityModule;
    private HerramientasFragmentModule herramientasFragmentModule;
    private HerramientaDetalleFragmentModule herramientaDetalleFragmentModule;
    private ExperienciasFragmentModule experienciasFragmentModule;
    private ExperienciaDetalleFragmentModule experienciaDetalleFragmentModule;
    private ReservaFragmentModule reservaFragmentModule;
    private AlquilerHerramientaFragmentModule alquilerHerramientaFragmentModule;
    private AlquilerExperienciaFragmentModule alquilerExperienciaFragmentModule;
    private LoginFragmentModule loginFragmentModule;
    private MapFragmentModule mapFragmentModule;

    public ModuleDependencies(Application application) {
        super(application);
        setupMainActivityModule(getConstantsInstance(), getInteractorFactoryInstance(), getNavigationManager());
        setupHerramientasFragmentModule(getNavigationManager(), getInteractorFactoryInstance());
        setupHerramientaDetalleFragmentModule(getInteractorFactoryInstance(), getNavigationManager());
        setupExperienciasFragmentModule(getNavigationManager(), getInteractorFactoryInstance(), getResources());
        setupExperienciaDetalleFragmentModule(getInteractorFactoryInstance(), getNavigationManager());
        setupReservaFragmentModule(getNavigationManager(), getInteractorFactoryInstance(), getResources());
        setupAlquilerHerramientaFragmentModule(getNavigationManager(), getInteractorFactoryInstance(), getResources());
        setupAlquilerExperienciaFragmentModule(getNavigationManager(), getInteractorFactoryInstance(), getResources());
        setupLoginFragmentModule(getInteractorFactoryInstance(), getResources(), getNavigationManager());
        setupMapFragmentModule(getInteractorFactoryInstance(), getNavigationManager(), getResources());
    }

    //region setup

    private void setupMapFragmentModule(InteractorFactory interactorFactoryInstance, NavigationManager navigationManager, Resources resources) {
        mapFragmentModule = new MapFragmentModule(interactorFactoryInstance, navigationManager, resources);
    }

    private void setupLoginFragmentModule(InteractorFactory interactorFactoryInstance, Resources resources, NavigationManager navigationManager) {
        loginFragmentModule = new LoginFragmentModule(interactorFactoryInstance, resources, navigationManager);
    }

    private void setupAlquilerExperienciaFragmentModule(NavigationManager navigationManager, InteractorFactory interactorFactoryInstance, Resources resources) {
        alquilerExperienciaFragmentModule = new AlquilerExperienciaFragmentModule(navigationManager, interactorFactoryInstance, resources);
    }

    private void setupAlquilerHerramientaFragmentModule(NavigationManager navigationManager, InteractorFactory interactorFactoryInstance, Resources resources) {
        alquilerHerramientaFragmentModule = new AlquilerHerramientaFragmentModule(navigationManager, interactorFactoryInstance, resources);
    }

    private void setupReservaFragmentModule(NavigationManager navigationManager, InteractorFactory interactorFactoryInstance, Resources resources) {
        reservaFragmentModule = new ReservaFragmentModule(navigationManager, interactorFactoryInstance, resources);
    }

    private void setupExperienciaDetalleFragmentModule(InteractorFactory interactorFactoryInstance, NavigationManager navigationManager) {
        experienciaDetalleFragmentModule = new ExperienciaDetalleFragmentModule(interactorFactoryInstance, navigationManager);
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

    protected AlquilerExperienciaFragmentModule getAlquilerExperienciaFragmentModule() {
        return alquilerExperienciaFragmentModule;
    }

    protected LoginFragmentModule getLoginFragmentModule() {
        return loginFragmentModule;
    }

    protected MapFragmentModule getMapFragmentModule() {
        return mapFragmentModule;
    }

    //endregion GET
}
