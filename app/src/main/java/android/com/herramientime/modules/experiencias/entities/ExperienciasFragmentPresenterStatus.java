package android.com.herramientime.modules.experiencias.entities;

import android.com.herramientime.core.entities.BasePresenterStatus;
import android.com.herramientime.modules.herramientas.entities.FiltrosExperiencia;
import android.os.Bundle;

import java.util.List;

public class ExperienciasFragmentPresenterStatus extends BasePresenterStatus {

    private List<Experiencia> experiencias;
    private FiltrosExperiencia filtrosExperiencia = new FiltrosExperiencia();

    @Override
    public void saveInstance(Bundle saveInstance) {

    }

    @Override
    public void restoreInstance(Bundle restoreInstance) {

    }

    //region GET

    public List<Experiencia> getExperiencias() {
        return experiencias;
    }

    public FiltrosExperiencia getFiltrosExperiencia() {
        return filtrosExperiencia;
    }
    //endregion GET

    //region SET

    public void setExperiencias(List<Experiencia> experiencias) {
        this.experiencias = experiencias;
    }

    public void setFiltrosExperiencia(FiltrosExperiencia filtrosExperiencia) {
        this.filtrosExperiencia = filtrosExperiencia;
    }

    //endregion SET
}
