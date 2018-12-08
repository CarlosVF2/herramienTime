package android.com.herramientime.modules.experiencias.entities;

import android.com.herramientime.core.entities.BasePresenterStatus;

import com.seidor.core.utils.wrapper.BundleWrapper;

public class ExperienciaDetalleFragmentPresenterStatus extends BasePresenterStatus {

    private Exception error;
    private String idExperiencia;
    private Experiencia experiencia;

    @Override
    public void saveInstance(BundleWrapper saveInstance) {

    }

    @Override
    public void restoreInstance(BundleWrapper restoreInstance) {

    }

    //region GET

    public Exception getError() {
        return error;
    }

    public String getIdExperiencia() {
        return idExperiencia;
    }

    public Experiencia getExperiencia() {
        return experiencia;
    }
    //endregion GET

    //region SET

    public void setError(Exception error) {
        this.error = error;
    }

    public void setIdExperiencia(String idExperiencia) {
        this.idExperiencia = idExperiencia;
    }

    public void setExperiencia(Experiencia experiencia) {
        this.experiencia = experiencia;
    }
    //endregion SET
}
