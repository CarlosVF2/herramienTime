package android.com.herramientime.modules.herramientas.entities;

import android.com.herramientime.core.entities.BasePresenterStatus;

import com.seidor.core.utils.wrapper.BundleWrapper;

public class HerramientaDetalleFragmentPresenterStatus extends BasePresenterStatus {

    private String idHerramienta;
    private Herramienta herramienta;

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

    public Herramienta getHerramienta() {
        return herramienta;
    }

    //endregion GET

    //region SET

    public void setIdHerramienta(String idHerramienta) {
        this.idHerramienta = idHerramienta;
    }

    public void setHerramienta(Herramienta herramienta) {
        this.herramienta = herramienta;
    }

    //endregion SET
}
