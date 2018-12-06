package android.com.herramientime.modules.herramientas.injection;

/**
 * Created by carlo on 06/11/2018.
 */

public class HerramientaDetalleFragmentComponent {

    private HerramientaDetalleFragmentModule herramientaDetalleModule;

    public HerramientaDetalleFragmentComponent(HerramientaDetalleFragmentModule herramientaDetalleModule) {
        this.herramientaDetalleModule = herramientaDetalleModule;
    }

    public HerramientaDetalleFragmentModule getHerramientaDetalleModule() {
        return herramientaDetalleModule;
    }

}
