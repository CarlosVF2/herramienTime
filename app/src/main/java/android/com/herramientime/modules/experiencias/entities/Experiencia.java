package android.com.herramientime.modules.experiencias.entities;

public class Experiencia {

    private String idUsuario;
    private String id;
    private String descripcion;
    private String resumen;
    private String urlImagen;
    private String precioHora;
    private String simboloMoneda;
    private String moneda;

    //region GET

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

    //endregion GET

    //region SET


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
