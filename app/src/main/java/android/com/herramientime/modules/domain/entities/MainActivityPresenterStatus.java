package android.com.herramientime.modules.domain.entities;

import android.com.herramientime.core.entities.BasePresenterStatus;
import android.com.herramientime.modules.usuarios.entities.Usuario;

import com.seidor.core.utils.wrapper.BundleWrapper;

public class MainActivityPresenterStatus extends BasePresenterStatus {

    private Exception error;
    private Usuario usuario;

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

    public Usuario getUsuario() {
        return usuario;
    }
    //endregion GET

    //region SET

    public void setError(Exception error) {
        this.error = error;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    //endregion SET
}
