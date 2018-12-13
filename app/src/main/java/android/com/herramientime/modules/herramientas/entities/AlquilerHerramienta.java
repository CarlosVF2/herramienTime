package android.com.herramientime.modules.herramientas.entities;

import android.com.herramientime.modules.domain.entities.Categoria;
import android.com.herramientime.modules.domain.entities.Moneda;

import java.io.Serializable;

public class AlquilerHerramienta implements Serializable {

    private String descripcion;
    private String titulo;
    private String precioTexto;
    private Double precio;
    private String pathPhoto;
    private Moneda moneda = new Moneda();
    private Categoria categoria = new Categoria();


    //region GET


    public String getPathPhoto() {
        return pathPhoto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getPrecioTexto() {
        return precioTexto;
    }

    public Double getPrecio() {
        return precio;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    //endregion GET

    //region SET


    public void setPathPhoto(String pathPhoto) {
        this.pathPhoto = pathPhoto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setPrecioTexto(String precioTexto) {
        this.precioTexto = precioTexto;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    //endregion SET
}
