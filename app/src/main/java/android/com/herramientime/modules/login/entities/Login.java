package android.com.herramientime.modules.login.entities;

public class Login {

    private String nombre;
    private String apellido;
    private String password;
    private String user;
    private String acercaDeTi;

    //region GET

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getPassword() {
        return password;
    }

    public String getUser() {
        return user;
    }

    public String getAcercaDeTi() {
        return acercaDeTi;
    }

    //endregion GET

    //region SET

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setAcercaDeTi(String acercaDeTi) {
        this.acercaDeTi = acercaDeTi;
    }

    //endregion SET
}
