package android.com.herramientime.modules.experiencias.injection;

/**
 * Created by carlo on 06/11/2018.
 */

public class ExperienciaDetalleFragmentComponent {

    private ExperienciaDetalleFragmentModule herramientaDetalleModule;

    public ExperienciaDetalleFragmentComponent(ExperienciaDetalleFragmentModule herramientaDetalleModule) {
        this.herramientaDetalleModule = herramientaDetalleModule;
    }

    public ExperienciaDetalleFragmentModule getHerramientaDetalleModule() {
        return herramientaDetalleModule;
    }

}
