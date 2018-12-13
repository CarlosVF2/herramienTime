package android.com.herramientime.core.entities;

import android.os.Bundle;

/**
 * Created by carlo on 05/12/2018.
 */

public abstract class BasePresenterStatus {

    private Exception error;

    public abstract void saveInstance(Bundle saveInstance);

    public abstract void restoreInstance(Bundle restoreInstance);

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
