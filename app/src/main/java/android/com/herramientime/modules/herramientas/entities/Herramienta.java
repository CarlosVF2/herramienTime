package android.com.herramientime.modules.herramientas.entities;

import java.util.Date;

public class Herramienta {

    private String descripcion;
    private boolean reservada;
    private String idUsuario;
    private String id;
    private String nombreUsuario;
    private String urlImagen;
    private String fechaFinTexto;
    private Date fechaFin;
    private String fechaInicioTexto;
    private Date fechaInicio;
    private String simboloMoneda;
    private String resumen;
    private String moneda;
    private String precioText;
    private Double precio;
    private String categoria;
    private String categoriaDescriptivo;


    //region GET

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isReservada() {
        return reservada;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getId() {
        return id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public String getFechaFinTexto() {
        return fechaFinTexto;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public String getFechaInicioTexto() {
        return fechaInicioTexto;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public String getSimboloMoneda() {
        return simboloMoneda;
    }

    public String getResumen() {
        return resumen;
    }

    public String getMoneda() {
        return moneda;
    }

    public String getPrecioText() {
        return precioText;
    }

    public Double getPrecio() {
        return precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getCategoriaDescriptivo() {
        return categoriaDescriptivo;
    }

    //endregion GET

    //region SET

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setReservada(boolean reservada) {
        this.reservada = reservada;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public void setFechaFinTexto(String fechaFinTexto) {
        this.fechaFinTexto = fechaFinTexto;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setFechaInicioTexto(String fechaInicioTexto) {
        this.fechaInicioTexto = fechaInicioTexto;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setSimboloMoneda(String simboloMoneda) {
        this.simboloMoneda = simboloMoneda;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public void setPrecioText(String precioText) {
        this.precioText = precioText;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setCategoriaDescriptivo(String categoriaDescriptivo) {
        this.categoriaDescriptivo = categoriaDescriptivo;
    }

    //endregion SET
}
