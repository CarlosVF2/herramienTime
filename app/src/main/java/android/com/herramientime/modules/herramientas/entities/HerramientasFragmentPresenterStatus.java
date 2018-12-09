package android.com.herramientime.modules.herramientas.entities;

import android.com.herramientime.core.entities.BasePresenterStatus;

import com.seidor.core.utils.wrapper.BundleWrapper;

import java.util.List;

public class HerramientasFragmentPresenterStatus extends BasePresenterStatus {

    private List<Herramienta> herramientas;

    @Override
    public void saveInstance(BundleWrapper saveInstance) {

    }

    @Override
    public void restoreInstance(BundleWrapper restoreInstance) {

    }

    //region GET

    public List<Herramienta> getHerramientas() {
        return herramientas;
    }

    //endregion GET

    //region SET

    public void setHerramientas(List<Herramienta> herramientas) {
        this.herramientas = herramientas;
    }

    //endregion SET
}
