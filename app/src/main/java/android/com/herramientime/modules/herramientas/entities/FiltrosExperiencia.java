package android.com.herramientime.modules.herramientas.entities;

public class FiltrosExperiencia {

    private String descripcion;
    private String precioInicial;
    private String precioFinal;

    //region GET

    public String getDescripcion() {
        return descripcion;
    }

    public String getPrecioInicial() {
        return precioInicial;
    }

    public String getPrecioFinal() {
        return precioFinal;
    }

    //endregion GET

    //region SET

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecioInicial(String precioInicial) {
        this.precioInicial = precioInicial;
    }

    public void setPrecioFinal(String precioFinal) {
        this.precioFinal = precioFinal;
    }

    //endregion SET

}

