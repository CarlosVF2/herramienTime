package android.com.rest.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class UsuariosRest {

    @SerializedName("apellidos")
    private String apellidos;

    @SerializedName("idMensaje")
    private String idMensaje;

    @SerializedName("calificacion")
    private String calificacion;

    @SerializedName("coordenadaY")
    private String coordenadaY;

    @SerializedName("coordenadaX")
    private String coordenadaX;

    @SerializedName("direccion")
    private String direccion;

    @SerializedName("id")
    private String id;

    @SerializedName("poblacion")
    private String poblacion;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("password")
    private String password;

    public UsuariosRest(String id) {
        this.id = id;
    }

    public UsuariosRest() {
        apellidos = "";
        idMensaje = "";
        calificacion = "";
        coordenadaY = "";
        coordenadaX = "";
        direccion = "";
        id = "";
        poblacion = "";
        nombre = "";
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setIdMensaje(String idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getIdMensaje() {
        return idMensaje;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCoordenadaY(String coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public String getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaX(String coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public String getCoordenadaX() {
        return coordenadaX;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return
                "UsuariosRest{" +
                        "apellidos = '" + apellidos + '\'' +
                        ",idMensaje = '" + idMensaje + '\'' +
                        ",calificacion = '" + calificacion + '\'' +
                        ",coordenadaY = '" + coordenadaY + '\'' +
                        ",coordenadaX = '" + coordenadaX + '\'' +
                        ",direccion = '" + direccion + '\'' +
                        ",id = '" + id + '\'' +
                        ",poblacion = '" + poblacion + '\'' +
                        ",nombre = '" + nombre + '\'' +
                        "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuariosRest that = (UsuariosRest) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}