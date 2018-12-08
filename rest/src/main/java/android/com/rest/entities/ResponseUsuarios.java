package android.com.rest.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseUsuarios {

    @SerializedName("Usuarios")
    private List<UsuariosRest> usuarios;

    public void setUsuarios(List<UsuariosRest> usuarios) {
        this.usuarios = usuarios;
    }

    public List<UsuariosRest> getUsuarios() {
        return usuarios;
    }

    @Override
    public String toString() {
        return
                "ResponseUsuarios{" +
                        "usuarios = '" + usuarios + '\'' +
                        "}";
    }
}