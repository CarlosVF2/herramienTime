package android.com.herramientime.modules.experiencias.entities;

import android.com.herramientime.core.entities.BasePresenterStatus;

import com.seidor.core.utils.wrapper.BundleWrapper;

import java.util.List;

public class ExperienciasFragmentPresenterStatus extends BasePresenterStatus {

    private Exception error;
    private List<Experiencia> experiencias;

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

    public List<Experiencia> getExperiencias() {
        return experiencias;
    }

    //endregion GET

    //region SET

    public void setError(Exception error) {
        this.error = error;
    }

    public void setExperiencias(List<Experiencia> experiencias) {
        this.experiencias = experiencias;
    }

    //endregion SET
}
