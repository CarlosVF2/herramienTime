package android.com.herramientime.modules.herramientas.entities;

public class Herramienta {

    private String descripcion;
    private boolean reservada;
    private String idUsuario;
    private String id;
    private String nombreUsuario;
    private String urlImagen;

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

    //endregion SET
}
