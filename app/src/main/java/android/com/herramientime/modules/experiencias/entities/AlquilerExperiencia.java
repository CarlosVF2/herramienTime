package android.com.herramientime.modules.experiencias.entities;

public class AlquilerExperiencia {
    private String descripcion;
    private String titulo;
    private String precioTexto;
    private Double precio;
    private String simboloMoneda;
    private String moneda;

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

    public String getSimboloMoneda() {
        return simboloMoneda;
    }

    public String getMoneda() {
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

    public void setSimboloMoneda(String simboloMoneda) {
        this.simboloMoneda = simboloMoneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }


    //endregion SET
}
