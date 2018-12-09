package android.com.herramientime.modules.reservar.entities;

import android.com.herramientime.core.entities.BasePresenterStatus;
import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.herramientime.modules.herramientas.entities.Herramienta;

import com.seidor.core.utils.wrapper.BundleWrapper;

import java.util.Date;

public class ReservaFragmentPresenterStatus extends BasePresenterStatus {

    private String idHerramienta;
    private String idExperiencia;
    private Date fechaInicial;
    private Date fechaFinal;
    private Experiencia experiencia;
    private Herramienta herramienta;

    @Override
    public void saveInstance(BundleWrapper saveInstance) {

    }

    @Override
    public void restoreInstance(BundleWrapper restoreInstance) {

    }

    //region GET

    public String getIdHerramienta() {
        return idHerramienta;
    }

    public String getIdExperiencia() {
        return idExperiencia;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public Experiencia getExperiencia() {
        return experiencia;
    }

    public Herramienta getHerramienta() {
        return herramienta;
    }

    //endregion GET

    //region SET

    public void setIdHerramienta(String idHerramienta) {
        this.idHerramienta = idHerramienta;
    }

    public void setIdExperiencia(String idExperiencia) {
        this.idExperiencia = idExperiencia;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public void setExperiencia(Experiencia experiencia) {
        this.experiencia = experiencia;
    }

    public void setHerramienta(Herramienta herramienta) {
        this.herramienta = herramienta;
    }

    //endregion SET
}
