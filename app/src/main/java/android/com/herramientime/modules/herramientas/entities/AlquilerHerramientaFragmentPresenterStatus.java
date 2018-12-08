package android.com.herramientime.modules.herramientas.entities;

import android.com.herramientime.core.entities.BasePresenterStatus;

import com.seidor.core.utils.wrapper.BundleWrapper;

public class AlquilerHerramientaFragmentPresenterStatus extends BasePresenterStatus {

    private Exception error;
    private AlquilerHerramienta alquilerHerramienta = new AlquilerHerramienta();

    @Override
    public void saveInstance(BundleWrapper saveInstance) {

    }

    @Override
    public void restoreInstance(BundleWrapper restoreInstance) {

    }

    //region GET

    public AlquilerHerramienta getAlquilerHerramienta() {
        return alquilerHerramienta;
    }

    public Exception getError() {
        return error;
    }

    //endregion GET

    //region SET

    public void setAlquilerHerramienta(AlquilerHerramienta alquilerHerramienta) {
        this.alquilerHerramienta = alquilerHerramienta;
    }

    public void setError(Exception error) {
        this.error = error;
    }

    //endregion SET
}
