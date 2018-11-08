
package android.com.herramientime.core.presenter;

import android.com.herramientime.core.view.MvpActivity;
import android.support.annotation.NonNull;

/**
 * Created by carlo on 08/11/2018.
 */
public interface MvpActivityPresenter extends MvpPresenter, MvpPresenterArgument {

    void setMvpActivity(@NonNull MvpActivity mvpActivity);

    MvpActivity getMvpActivity();
}
