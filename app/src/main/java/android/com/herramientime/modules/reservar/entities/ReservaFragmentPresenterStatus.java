package android.com.herramientime.modules.reservar.entities;

import android.com.herramientime.core.entities.BasePresenterStatus;

import com.seidor.core.utils.wrapper.BundleWrapper;

public class ReservaFragmentPresenterStatus extends BasePresenterStatus {

    private String idHerramienta;
    private String idExperiencia;

    @Override
    public void saveInstance(BundleWrapper saveInstance) {

    }

    @Override
    public void restoreInstance(BundleWrapper restoreInstance) {

    }

    //region GET

    public String getIdHerramienta() {
        return idHerramienta;
    }

    public String getIdExperiencia() {
        return idExperiencia;
    }

    //endregion GET

    //region SET

    public void setIdHerramienta(String idHerramienta) {
        this.idHerramienta = idHerramienta;
    }

    public void setIdExperiencia(String idExperiencia) {
        this.idExperiencia = idExperiencia;
    }

    //endregion SET
}
