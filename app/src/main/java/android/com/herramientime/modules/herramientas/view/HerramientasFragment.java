package android.com.herramientime.modules.herramientas.view;

import android.com.herramientime.core.view.MvpFragment;
import android.com.herramientime.modules.herramientas.adapter.HerramientasVHListener;
import android.com.herramientime.modules.herramientas.entities.Herramienta;

import java.util.List;

/**
 * Created by carlo on 06/11/2018.
 */

public interface HerramientasFragment extends MvpFragment {
    void setData(List<Herramienta> herramientas, HerramientasVHListener listener);

    void toggleDrawer();
    void setRefresh(boolean visibility);

    String getDescriptionText();

    String getPrecioInicialText();

    String getPrecioFinalText();

    void restoreFilters();
}
