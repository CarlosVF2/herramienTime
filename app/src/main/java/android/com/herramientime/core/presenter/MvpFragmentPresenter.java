
package android.com.herramientime.core.presenter;

import android.com.herramientime.core.view.MvpFragment;
import android.support.annotation.NonNull;

/**
 * Created by carlo on 08/11/2018.
 */
public interface MvpFragmentPresenter extends MvpPresenter {
    void setMvpFragment(@NonNull MvpFragment mvpFragment);
}
