package android.com.herramientime.modules.experiencias.view;

import android.com.herramientime.core.view.MvpFragment;

import java.util.List;

/**
 * Created by carlo on 06/11/2018.
 */

public interface AlquilarExperienciaFragment extends MvpFragment {
    void setAdapterSpinnerMoneda(List<String> valuesAdapterMoneda);
}
