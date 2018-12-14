package android.com.herramientime.modules.herramientas.entities;

import android.com.herramientime.core.entities.BasePresenterStatus;
import android.os.Bundle;

import java.util.List;

public class HerramientasFragmentPresenterStatus extends BasePresenterStatus {

    private List<Herramienta> herramientas;
    private FiltrosHerramientas filtrosHerramientas = new FiltrosHerramientas();

    @Override
    public void saveInstance(Bundle saveInstance) {

    }

    @Override
    public void restoreInstance(Bundle restoreInstance) {

    }

    //region GET


    public FiltrosHerramientas getFiltrosHerramientas() {
        return filtrosHerramientas;
    }

    public List<Herramienta> getHerramientas() {
        return herramientas;
    }

    //endregion GET

    //region SET


    public void setFiltrosHerramientas(FiltrosHerramientas filtrosHerramientas) {
        this.filtrosHerramientas = filtrosHerramientas;
    }

    public void setHerramientas(List<Herramienta> herramientas) {
        this.herramientas = herramientas;
    }

    //endregion SET
}
