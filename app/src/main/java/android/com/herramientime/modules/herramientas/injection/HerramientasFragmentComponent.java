package android.com.herramientime.modules.herramientas.injection;

/**
 * Created by carlo on 06/11/2018.
 */

public class HerramientasFragmentComponent {

    private HerramientasFragmentModule herramientasFragmentModule;

    public HerramientasFragmentComponent(HerramientasFragmentModule herramientasFragmentModule) {
        this.herramientasFragmentModule = herramientasFragmentModule;
    }

    public HerramientasFragmentModule getHerramientasFragmentModule() {
        return herramientasFragmentModule;
    }

}
