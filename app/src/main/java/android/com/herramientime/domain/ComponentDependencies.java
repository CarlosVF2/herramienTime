package android.com.herramientime.domain;

import android.app.Application;
import android.com.herramientime.modules.domain.injection.MainActivityComponent;
import android.com.herramientime.modules.domain.injection.MainActivityModule;
import android.com.herramientime.modules.experiencias.injection.AlquilerExperienciaFragmentComponent;
import android.com.herramientime.modules.experiencias.injection.AlquilerExperienciaFragmentModule;
import android.com.herramientime.modules.experiencias.injection.ExperienciaDetalleFragmentComponent;
import android.com.herramientime.modules.experiencias.injection.ExperienciaDetalleFragmentModule;
import android.com.herramientime.modules.experiencias.injection.ExperienciasFragmentComponent;
import android.com.herramientime.modules.experiencias.injection.ExperienciasFragmentModule;
import android.com.herramientime.modules.herramientas.injection.AlquilerHerramientaFragmentComponent;
import android.com.herramientime.modules.herramientas.injection.AlquilerHerramientaFragmentModule;
import android.com.herramientime.modules.herramientas.injection.HerramientaDetalleFragmentComponent;
import android.com.herramientime.modules.herramientas.injection.HerramientaDetalleFragmentModule;
import android.com.herramientime.modules.herramientas.injection.HerramientasFragmentComponent;
import android.com.herramientime.modules.herramientas.injection.HerramientasFragmentModule;
import android.com.herramientime.modules.login.injection.LoginFragmentComponent;
import android.com.herramientime.modules.login.injection.LoginFragmentModule;
import android.com.herramientime.modules.map.injection.MapFragmentComponent;
import android.com.herramientime.modules.map.injection.MapFragmentModule;
import android.com.herramientime.modules.reservar.injection.ReservaFragmentComponent;
import android.com.herramientime.modules.reservar.injection.ReservaFragmentModule;
import android.com.herramientime.modules.usuarios.injection.UsuarioDetalleFragmentComponent;
import android.com.herramientime.modules.usuarios.injection.UsuarioDetalleFragmentModule;

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
    private ReservaFragmentComponent reservaFragmentComponent;
    private AlquilerHerramientaFragmentComponent alquilerHerramientaFragmentComponent;
    private AlquilerExperienciaFragmentComponent alquilerExperienciaFragmentComponent;
    private LoginFragmentComponent loginFragmentComponent;
    private MapFragmentComponent mapFragmentComponent;
    private UsuarioDetalleFragmentComponent usuarioDetalleFragmentComponent;

    public ComponentDependencies(Application application) {
        super(application);
        setupMainActivityComponent(getMainActivityModule());
        setupHerramientasFragmentComponent(getHerramientasFragmentModule());
        setupHerramientaDetalleFragmentComponent(getHerramientaDetalleFragmentModule());
        setupExperienciasFragmentComponent(getExperienciasFragmentModule());
        setupExperienciaDetalleFragmentComponent(getExperienciaDetalleFragmentModule());
        setupReservaFragmentComponent(getReservaFragmentModule());
        setupAlquilerHerramientaFragmentComponent(getAlquilerHerramientaFragmentModule());
        setupAlquilerExperienciaFragmentComponent(getAlquilerExperienciaFragmentModule());
        setupLoginFragmentComponent(getLoginFragmentModule());
        setupMapFragmentComponent(getMapFragmentModule());
        setupUsuarioDetalleFragmentComponent(getUsuarioDetalleFragmentModule());
    }

    //region Setup

    private void setupUsuarioDetalleFragmentComponent(UsuarioDetalleFragmentModule usuarioDetalleFragmentModule) {
        usuarioDetalleFragmentComponent = new UsuarioDetalleFragmentComponent(usuarioDetalleFragmentModule);
    }

    private void setupMapFragmentComponent(MapFragmentModule mapFragmentModule) {
        mapFragmentComponent = new MapFragmentComponent(mapFragmentModule);
    }

    private void setupLoginFragmentComponent(LoginFragmentModule loginFragmentModule) {
        loginFragmentComponent = new LoginFragmentComponent(loginFragmentModule);
    }

    private void setupAlquilerExperienciaFragmentComponent(AlquilerExperienciaFragmentModule alquilerExperienciaFragmentModule) {
        alquilerExperienciaFragmentComponent = new AlquilerExperienciaFragmentComponent(alquilerExperienciaFragmentModule);
    }

    private void setupAlquilerHerramientaFragmentComponent(AlquilerHerramientaFragmentModule alquilerHerramientaFragmentModule) {
        alquilerHerramientaFragmentComponent = new AlquilerHerramientaFragmentComponent(alquilerHerramientaFragmentModule);
    }

    private void setupReservaFragmentComponent(ReservaFragmentModule reservaFragmentModule) {
        reservaFragmentComponent = new ReservaFragmentComponent(reservaFragmentModule);
    }

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

    @Provides
    public ReservaFragmentComponent getReservaFragmentComponent() {
        return reservaFragmentComponent;
    }

    @Provides
    public AlquilerHerramientaFragmentComponent getAlquilerHerramientaFragmentComponent() {
        return alquilerHerramientaFragmentComponent;
    }

    @Provides
    public AlquilerExperienciaFragmentComponent getAlquilerExperienciaFragmentComponent() {
        return alquilerExperienciaFragmentComponent;
    }

    @Provides
    public LoginFragmentComponent getLoginFragmentComponent() {
        return loginFragmentComponent;
    }

    @Provides
    public MapFragmentComponent getMapFragmentComponent() {
        return mapFragmentComponent;
    }

    @Provides
    public UsuarioDetalleFragmentComponent getUsuarioDetalleFragmentComponent() {
        return usuarioDetalleFragmentComponent;
    }

    //endregion Get
}
