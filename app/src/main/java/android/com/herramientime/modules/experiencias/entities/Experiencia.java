package android.com.herramientime.modules.experiencias.entities;

public class Experiencia {

    private String idUsuario;
    private String id;
    private String titulo;

    //region GET

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }


    //endregion GET

    //region SET

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    //endregion SET

}
