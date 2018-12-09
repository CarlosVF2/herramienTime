package android.com.herramientime.modules.experiencias.entities;

import android.com.herramientime.modules.domain.entities.Moneda;

public class AlquilerExperiencia {
    private String descripcion;
    private String titulo;
    private String precioTexto;
    private Double precio;
    private Moneda moneda = new Moneda();

    //region GET

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

    //endregion GET

    //region SET

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

    //endregion SET
}
