package android.com.herramientime.modules.experiencias.entities;

import java.util.Date;

public class Experiencia {

    private String idUsuario;
    private String id;
    private String descripcion;
    private String resumen;
    private String urlImagen;
    private String precioHora;
    private String simboloMoneda;
    private String moneda;
    private String reservada;
    private boolean isReservada;
    private String fechaInicialTexto;
    private String fechaFinalTexto;
    private Date fechaInicial;
    private Date fechaFinal;

    //region GET

    public String getReservada() {
        return reservada;
    }

    public boolean isReservada() {
        return isReservada;
    }

    public String getFechaInicialTexto() {
        return fechaInicialTexto;
    }

    public String getFechaFinalTexto() {
        return fechaFinalTexto;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getResumen() {
        return resumen;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public String getPrecioHora() {
        return precioHora;
    }

    public String getSimboloMoneda() {
        return simboloMoneda;
    }

    public String getMoneda() {
        return moneda;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    //endregion GET

    //region SET


    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public void setReservada(String reservada) {
        this.reservada = reservada;
    }

    public void setReservada(boolean reservada) {
        isReservada = reservada;
    }

    public void setFechaInicialTexto(String fechaInicialTexto) {
        this.fechaInicialTexto = fechaInicialTexto;
    }

    public void setFechaFinalTexto(String fechaFinalTexto) {
        this.fechaFinalTexto = fechaFinalTexto;
    }

    public void setPrecioHora(String precioHora) {
        this.precioHora = precioHora;
    }

    public void setSimboloMoneda(String simboloMoneda) {
        this.simboloMoneda = simboloMoneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    //endregion SET

}
