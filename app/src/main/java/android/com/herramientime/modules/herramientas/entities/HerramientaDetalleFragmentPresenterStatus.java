package android.com.herramientime.modules.herramientas.entities;

import android.com.herramientime.core.entities.BasePresenterStatus;

import com.seidor.core.utils.wrapper.BundleWrapper;

import java.util.List;

public class HerramientaDetalleFragmentPresenterStatus extends BasePresenterStatus {

    private Exception error;

    @Override
    public void saveInstance(BundleWrapper saveInstance) {

    }

    @Override
    public void restoreInstance(BundleWrapper restoreInstance) {

    }

    //region GET

    public Exception getError() {
        return error;
    }

    //endregion GET

    //region SET

    public void setError(Exception error) {
        this.error = error;
    }

    //endregion SET
}
