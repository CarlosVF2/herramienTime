package android.com.herramientime.modules.herramientas.entities;

import android.com.herramientime.core.entities.BasePresenterStatus;
import android.com.herramientime.modules.domain.entities.Categoria;
import android.com.herramientime.modules.domain.entities.Moneda;

import com.seidor.core.utils.wrapper.BundleWrapper;

import java.util.ArrayList;
import java.util.List;

public class AlquilerHerramientaFragmentPresenterStatus extends BasePresenterStatus {

    private AlquilerHerramienta alquilerHerramienta = new AlquilerHerramienta();
    private List<Moneda> monedas = new ArrayList<>();
    private List<Categoria> categorias = new ArrayList<>();

    @Override
    public void saveInstance(BundleWrapper saveInstance) {

    }

    @Override
    public void restoreInstance(BundleWrapper restoreInstance) {

    }

    //region GET

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
