package android.com.herramientime.modules.experiencias.injection;

import android.com.herramientime.injection.InteractorFactory;
import android.com.herramientime.modules.experiencias.interactor.ExperienciaDetalleFragmentInteractor;

/**
 * Created by carlo on 06/11/2018.
 */

public class ExperienciaDetalleFragmentModule {

    private final ExperienciaDetalleFragmentInteractor experienciaDetalleFragmentInteractor;

    public ExperienciaDetalleFragmentModule(InteractorFactory interactorFactory) {
        this.experienciaDetalleFragmentInteractor = interactorFactory.getExperienciaDetalleFragmentInteractor();
    }

    public ExperienciaDetalleFragmentInteractor getExperienciaDetalleFragmentInteractor() {
        return experienciaDetalleFragmentInteractor;
    }
}
