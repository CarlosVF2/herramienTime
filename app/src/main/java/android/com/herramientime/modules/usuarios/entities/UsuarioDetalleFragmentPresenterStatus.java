package android.com.herramientime.modules.usuarios.entities;

import android.com.herramientime.core.entities.BasePresenterStatus;
import android.os.Bundle;

public class UsuarioDetalleFragmentPresenterStatus extends BasePresenterStatus {

    private String idUsuario;
    private Usuario usuario;

    @Override
    public void saveInstance(Bundle saveInstance) {

    }

    @Override
    public void restoreInstance(Bundle restoreInstance) {

    }

    //region GET

    public String getIdUsuario() {
        return idUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }


    //endregion GET

    //region SET

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    //endregion SET
}
