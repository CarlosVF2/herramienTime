package android.com.herramientime.modules.experiencias.entities;

import android.com.herramientime.core.entities.BasePresenterStatus;
import android.os.Bundle;

public class ExperienciaDetalleFragmentPresenterStatus extends BasePresenterStatus {

    private String idExperiencia;
    private Experiencia experiencia;

    @Override
    public void saveInstance(Bundle saveInstance) {

    }

    @Override
    public void restoreInstance(Bundle restoreInstance) {

    }

    //region GET

    public String getIdExperiencia() {
        return idExperiencia;
    }

    public Experiencia getExperiencia() {
        return experiencia;
    }
    //endregion GET

    //region SET

    public void setIdExperiencia(String idExperiencia) {
        this.idExperiencia = idExperiencia;
    }

    public void setExperiencia(Experiencia experiencia) {
        this.experiencia = experiencia;
    }
    //endregion SET
}
