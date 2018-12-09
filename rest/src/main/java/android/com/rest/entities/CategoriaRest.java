package android.com.rest.entities;


import com.google.gson.annotations.SerializedName;

public class CategoriaRest {

    @SerializedName("descripcion")
    private String descripcion;

    @SerializedName("id")
    private String id;

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return
                "CategoriaRest{" +
                        "descripcion = '" + descripcion + '\'' +
                        ",id = '" + id + '\'' +
                        "}";
    }
}