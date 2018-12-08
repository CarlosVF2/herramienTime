package android.com.herramientime.modules.experiencias.injection;

/**
 * Created by carlo on 06/11/2018.
 */

public class AlquilerExperienciaFragmentComponent {

    private AlquilerExperienciaFragmentModule alquilerExperienciaFragmentModule;

    public AlquilerExperienciaFragmentComponent(AlquilerExperienciaFragmentModule alquilerExperienciaFragmentModule) {
        this.alquilerExperienciaFragmentModule = alquilerExperienciaFragmentModule;
    }

    public AlquilerExperienciaFragmentModule getAlquilerExperienciaFragmentModule() {
        return alquilerExperienciaFragmentModule;
    }

}
