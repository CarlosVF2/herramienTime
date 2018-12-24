package android.com.herramientime.modules.herramientas.entities;

import android.com.herramientime.core.entities.BasePresenterStatus;
import android.com.herramientime.modules.usuarios.entities.Usuario;
import android.os.Bundle;

public class HerramientaDetalleFragmentPresenterStatus extends BasePresenterStatus {

    private String idHerramienta;
    private Herramienta herramienta;
    private Usuario usuario;

    @Override
    public void saveInstance(Bundle saveInstance) {

    }

    @Override
    public void restoreInstance(Bundle restoreInstance) {

    }

    //region GET


    public Usuario getUsuario() {
        return usuario;
    }

    public String getIdHerramienta() {
        return idHerramienta;
    }

    public Herramienta getHerramienta() {
        return herramienta;
    }

    //endregion GET

    //region SET


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setIdHerramienta(String idHerramienta) {
        this.idHerramienta = idHerramienta;
    }

    public void setHerramienta(Herramienta herramienta) {
        this.herramienta = herramienta;
    }

    //endregion SET
}
