package android.com.herramientime.modules.herramientas.injection;

/**
 * Created by carlo on 06/11/2018.
 */

public class AlquilerHerramientaFragmentComponent {

    private AlquilerHerramientaFragmentModule alquilerHerramientaFragmentModule;

    public AlquilerHerramientaFragmentComponent(AlquilerHerramientaFragmentModule alquilerHerramientaFragmentModule) {
        this.alquilerHerramientaFragmentModule = alquilerHerramientaFragmentModule;
    }

    public AlquilerHerramientaFragmentModule getAlquilerHerramientaFragmentModule() {
        return alquilerHerramientaFragmentModule;
    }

}
