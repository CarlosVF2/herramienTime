package android.com.herramientime.domain;

import android.app.Application;
import android.com.herramientime.modules.domain.injection.MainActivityComponent;
import android.com.herramientime.modules.domain.injection.MainActivityModule;
import android.com.herramientime.modules.experiencias.injection.ExperienciaDetalleFragmentComponent;
import android.com.herramientime.modules.experiencias.injection.ExperienciaDetalleFragmentModule;
import android.com.herramientime.modules.experiencias.injection.ExperienciasFragmentComponent;
import android.com.herramientime.modules.experiencias.injection.ExperienciasFragmentModule;
import android.com.herramientime.modules.herramientas.injection.HerramientaDetalleFragmentComponent;
import android.com.herramientime.modules.herramientas.injection.HerramientaDetalleFragmentModule;
import android.com.herramientime.modules.herramientas.injection.HerramientasFragmentComponent;
import android.com.herramientime.modules.herramientas.injection.HerramientasFragmentModule;

import com.seidor.core.di.annotations.Provides;

/**
 * Created by carlo 06/11/2018
 */

public class ComponentDependencies extends ModuleDependencies {

    private MainActivityComponent mainActivityComponent;
    private HerramientasFragmentComponent herramientasFragmentComponent;
    private HerramientaDetalleFragmentComponent herramientaDetalleFragmentComponent;
    private ExperienciasFragmentComponent experienciasFragmentComponent;
    private ExperienciaDetalleFragmentComponent experienciaDetalleFragmentComponent;

    public ComponentDependencies(Application application) {
        super(application);
        setupMainActivityComponent(getMainActivityModule());
        setupHerramientasFragmentComponent(getHerramientasFragmentModule());
        setupHerramientaDetalleFragmentComponent(getHerramientaDetalleFragmentModule());
        setupExperienciasFragmentComponent(getExperienciasFragmentModule());
        setupExperienciaDetalleFragmentComponent(getExperienciaDetalleFragmentModule());
    }

    //region Setup

    private void setupExperienciaDetalleFragmentComponent(ExperienciaDetalleFragmentModule experienciaDetalleFragmentModule) {
        experienciaDetalleFragmentComponent = new ExperienciaDetalleFragmentComponent(experienciaDetalleFragmentModule);
    }

    private void setupExperienciasFragmentComponent(ExperienciasFragmentModule experienciasFragmentModule) {
        experienciasFragmentComponent = new ExperienciasFragmentComponent(experienciasFragmentModule);
    }

    private void setupHerramientaDetalleFragmentComponent(HerramientaDetalleFragmentModule herramientaDetalleFragmentModule) {
        herramientaDetalleFragmentComponent = new HerramientaDetalleFragmentComponent(herramientaDetalleFragmentModule);
    }

    private void setupMainActivityComponent(MainActivityModule mainActivityModule) {
        mainActivityComponent = new MainActivityComponent(mainActivityModule);
    }

    private void setupHerramientasFragmentComponent(HerramientasFragmentModule herramientasFragmentModule) {
        herramientasFragmentComponent = new HerramientasFragmentComponent(herramientasFragmentModule);
    }

    //endregion Setup

    //region Get

    @Provides
    public MainActivityComponent getMainActivityComponent() {
        return mainActivityComponent;
    }

    @Provides
    public HerramientasFragmentComponent getHerramientasFragmentComponent() {
        return herramientasFragmentComponent;
    }

    @Provides
    public HerramientaDetalleFragmentComponent getHerramientaDetalleFragmentComponent() {
        return herramientaDetalleFragmentComponent;
    }

    @Provides
    public ExperienciasFragmentComponent getExperienciasFragmentComponent() {
        return experienciasFragmentComponent;
    }

    @Provides
    public ExperienciaDetalleFragmentComponent getExperienciaDetalleFragmentComponent() {
        return experienciaDetalleFragmentComponent;
    }

    //endregion Get
}
