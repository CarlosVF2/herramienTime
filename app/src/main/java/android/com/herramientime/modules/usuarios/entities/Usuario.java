package android.com.herramientime.modules.usuarios.entities;

public class Usuario {

    private String apellidos;
    private String idMensaje;
    private String calificacion;
    private String coordenadaY;
    private String coordenadaX;
    private String direccion;
    private String id;
    private String poblacion;
    private String nombre;
    private String password;

    //region GET

    public String getApellidos() {
        return apellidos;
    }

    public String getIdMensaje() {
        return idMensaje;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public String getCoordenadaY() {
        return coordenadaY;
    }

    public String getCoordenadaX() {
        return coordenadaX;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getId() {
        return id;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    //endregion GET

    //region SET

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setIdMensaje(String idMensaje) {
        this.idMensaje = idMensaje;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public void setCoordenadaY(String coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public void setCoordenadaX(String coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //endregion SET

}
