package android.com.herramientime.modules.experiencias.injection;

/**
 * Created by carlo on 06/11/2018.
 */

public class ExperienciasFragmentComponent {

    private ExperienciasFragmentModule experienciasFragmentModule;

    public ExperienciasFragmentComponent(ExperienciasFragmentModule experienciasFragmentModule) {
        this.experienciasFragmentModule = experienciasFragmentModule;
    }

    public ExperienciasFragmentModule getExperienciasFragmentModule() {
        return experienciasFragmentModule;
    }

}
