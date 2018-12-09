package android.com.herramientime.modules.experiencias.entities;

import android.com.herramientime.core.entities.BasePresenterStatus;

import com.seidor.core.utils.wrapper.BundleWrapper;

public class AlquilarExperienciaFragmentPresenterStatus extends BasePresenterStatus {

    private AlquilerExperiencia alquilerExperiencia = new AlquilerExperiencia();

    @Override
    public void saveInstance(BundleWrapper saveInstance) {

    }

    @Override
    public void restoreInstance(BundleWrapper restoreInstance) {

    }

    //region GET

    public AlquilerExperiencia getAlquilerExperiencia() {
        return alquilerExperiencia;
    }

    //endregion GET

    //region SET

    public void setAlquilerExperiencia(AlquilerExperiencia alquilerExperiencia) {
        this.alquilerExperiencia = alquilerExperiencia;
    }

    //endregion SET
}
