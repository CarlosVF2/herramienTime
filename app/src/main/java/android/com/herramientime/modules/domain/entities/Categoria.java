package android.com.herramientime.modules.domain.entities;

import java.util.Objects;

public class Categoria {

    private String descripcion;
    private String id;

    public Categoria() {
        descripcion = "";
        id = "";
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(id, categoria.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
