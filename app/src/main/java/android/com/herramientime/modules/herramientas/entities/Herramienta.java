package android.com.herramientime.modules.herramientas.entities;

public class Herramienta {

    private String id;
    private String descripcion;

    //region GET

    public String getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    //endregion GET

    //region SET

    public void setId(String id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //endregion SET
}
