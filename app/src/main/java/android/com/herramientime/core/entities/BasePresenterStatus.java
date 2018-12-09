package android.com.herramientime.core.entities;

import com.seidor.core.utils.wrapper.BundleWrapper;

/**
 * Created by carlo on 05/12/2018.
 */

public abstract class BasePresenterStatus {

    private Exception error;

    public abstract void saveInstance(BundleWrapper saveInstance);

    public abstract void restoreInstance(BundleWrapper restoreInstance);

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
