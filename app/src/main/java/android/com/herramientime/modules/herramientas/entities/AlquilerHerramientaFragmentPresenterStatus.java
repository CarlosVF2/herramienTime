package android.com.herramientime.modules.herramientas.entities;

import android.com.herramientime.core.entities.BasePresenterStatus;
import android.com.herramientime.modules.domain.entities.Categoria;
import android.com.herramientime.modules.domain.entities.Moneda;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class AlquilerHerramientaFragmentPresenterStatus extends BasePresenterStatus {

    private final String PARAM__ALQUILER = "PARAM__ALQUILER";

    private AlquilerHerramienta alquilerHerramienta = new AlquilerHerramienta();
    private List<Moneda> monedas = new ArrayList<>();
    private List<Categoria> categorias = new ArrayList<>();
    private boolean pendingSaveMediaCamera;

    @Override
    public void saveInstance(Bundle saveInstance) {
        saveInstance.putSerializable(PARAM__ALQUILER, alquilerHerramienta);
    }

    @Override
    public void restoreInstance(Bundle restoreInstance) {
        if (restoreInstance != null) {
            alquilerHerramienta = restoreInstance.containsKey(PARAM__ALQUILER) ? (AlquilerHerramienta) restoreInstance.getSerializable(PARAM__ALQUILER) : alquilerHerramienta;
        }
    }

    //region GET

    public boolean isPendingSaveMediaCamera() {
        return pendingSaveMediaCamera;
    }

    public List<Moneda> getMonedas() {
        return monedas;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public AlquilerHerramienta getAlquilerHerramienta() {
        return alquilerHerramienta;
    }

    //endregion GET

    //region SET

    public void setPendingSaveMediaCamera(boolean pendingSaveMediaCamera) {
        this.pendingSaveMediaCamera = pendingSaveMediaCamera;
    }

    public void setMonedas(List<Moneda> monedas) {
        this.monedas = monedas;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public void setAlquilerHerramienta(AlquilerHerramienta alquilerHerramienta) {
        this.alquilerHerramienta = alquilerHerramienta;
    }

    //endregion SET
}
