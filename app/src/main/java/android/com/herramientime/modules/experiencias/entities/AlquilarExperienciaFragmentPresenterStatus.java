package android.com.herramientime.modules.experiencias.entities;

import android.com.herramientime.core.entities.BasePresenterStatus;
import android.com.herramientime.modules.domain.entities.Moneda;

import com.seidor.core.utils.wrapper.BundleWrapper;

import java.util.List;

public class AlquilarExperienciaFragmentPresenterStatus extends BasePresenterStatus {

    private AlquilerExperiencia alquilerExperiencia = new AlquilerExperiencia();
    private List<Moneda> monedas;

    @Override
    public void saveInstance(BundleWrapper saveInstance) {

    }

    @Override
    public void restoreInstance(BundleWrapper restoreInstance) {

    }

    //region GET

    public AlquilerExperiencia getAlquilerExperiencia() {
        return alquilerExperiencia;
    }

    public List<Moneda> getMonedas() {
        return monedas;
    }

    //endregion GET

    //region SET

    public void setAlquilerExperiencia(AlquilerExperiencia alquilerExperiencia) {
        this.alquilerExperiencia = alquilerExperiencia;
    }

    public void setMonedas(List<Moneda> monedas) {
        this.monedas = monedas;
    }

    //endregion SET
}
