package android.com.herramientime.modules;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by carlos 06/11/2018.
 */

public interface LayoutStatus {
    boolean IS_FOCUSABLE = true;
    boolean IS_CLICKABLE = true;
    boolean IS_ENABLED = true;

    @IntDef({ModoVisualizar.CREATE, ModoVisualizar.VISUALIZE, ModoVisualizar.EDIT})
    @Retention(RetentionPolicy.SOURCE)
    @interface ModoVisualizar {
        int CREATE = 0;
        int VISUALIZE = 1;
        int EDIT = 2;
    }

}
