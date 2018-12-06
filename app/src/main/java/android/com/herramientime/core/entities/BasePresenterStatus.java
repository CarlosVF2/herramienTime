package android.com.herramientime.core.entities;

import com.seidor.core.utils.wrapper.BundleWrapper;

/**
 * Created by carlo on 05/12/2018.
 */

public abstract class BasePresenterStatus {

    public abstract void saveInstance(BundleWrapper saveInstance);

    public abstract void restoreInstance(BundleWrapper restoreInstance);



}
