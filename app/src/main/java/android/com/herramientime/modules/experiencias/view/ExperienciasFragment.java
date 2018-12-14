package android.com.herramientime.modules.experiencias.view;

import android.com.herramientime.core.view.MvpFragment;
import android.com.herramientime.modules.experiencias.adapter.ExperienciasVHListener;
import android.com.herramientime.modules.experiencias.entities.Experiencia;

import java.util.List;

/**
 * Created by carlo on 06/11/2018.
 */

public interface ExperienciasFragment extends MvpFragment {
    void setData(List<Experiencia> experiencias, ExperienciasVHListener listener);

    void toggleDrawer();
    void setRefresh(boolean visibility);
}
