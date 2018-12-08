package android.com.rest.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class ExperienciaRest {

    public ExperienciaRest(String id) {
        this.id = id;
    }

    @SerializedName("idUsuario")
    private String idUsuario;

    @SerializedName("id")
    private String id;

    @SerializedName("descripcion")
    private String descripcion;

    @SerializedName("resumen")
    private String resumen;

    @SerializedName("urlImagen")
    private String urlImagen;

    @SerializedName("precioHora")
    private String precioHora;

    @SerializedName("simboloMoneda")
    private String simboloMoneda;

    @SerializedName("moneda")
    private String moneda;

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getPrecioHora() {
        return precioHora;
    }

    public void setPrecioHora(String precioHora) {
        this.precioHora = precioHora;
    }

    public String getSimboloMoneda() {
        return simboloMoneda;
    }

    public void setSimboloMoneda(String simboloMoneda) {
        this.simboloMoneda = simboloMoneda;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExperienciaRest that = (ExperienciaRest) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return
                "ExperienciaRest{" +
                        "idUsuario = '" + idUsuario + '\'' +
                        ",id = '" + id + '\'' +
                        ",descripcion = '" + descripcion + '\'' +
                        "}";
    }
}