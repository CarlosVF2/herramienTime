package android.com.herramientime.modules.domain.entities;

import android.com.herramientime.core.entities.BasePresenterStatus;
import android.com.herramientime.modules.usuarios.entities.Usuario;

import com.seidor.core.utils.wrapper.BundleWrapper;

public class MainActivityPresenterStatus extends BasePresenterStatus {

    private Usuario usuario;

    @Override
    public void saveInstance(BundleWrapper saveInstance) {

    }

    @Override
    public void restoreInstance(BundleWrapper restoreInstance) {

    }

    //region GET

    public Usuario getUsuario() {
        return usuario;
    }
    //endregion GET

    //region SET

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    //endregion SET
}
