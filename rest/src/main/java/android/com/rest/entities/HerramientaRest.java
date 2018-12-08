package android.com.rest.entities;


import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class HerramientaRest{

	@SerializedName("descripcion")
	private String descripcion;

    @SerializedName("simboloMoneda")
    private String simboloMoneda;

    @SerializedName("fechaInicio")
    private String fechaInicio;

	@SerializedName("reservada")
	private String reservada;

	@SerializedName("idUsuario")
	private String idUsuario;

    @SerializedName("moneda")
    private String moneda;

    @SerializedName("resumen")
    private String resumen;

	@SerializedName("id")
	private String id;

	@SerializedName("nombreUsuario")
	private String nombreUsuario;

    @SerializedName("fechaFin")
    private String fechaFin;

	@SerializedName("urlImagen")
	private String urlImagen;

    @SerializedName("precio")
    private String precio;

    public HerramientaRest(String id) {
        this.id = id;
    }

    public HerramientaRest() {
    }

	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}

	public String getDescripcion(){
		return descripcion;
	}

    public void setSimboloMoneda(String simboloMoneda) {
        this.simboloMoneda = simboloMoneda;
    }

    public String getSimboloMoneda() {
        return simboloMoneda;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

	public void setReservada(String reservada){
		this.reservada = reservada;
	}

	public String getReservada(){
		return reservada;
	}

	public void setIdUsuario(String idUsuario){
		this.idUsuario = idUsuario;
	}

	public String getIdUsuario(){
		return idUsuario;
	}

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getResumen() {
        return resumen;
    }

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setNombreUsuario(String nombreUsuario){
		this.nombreUsuario = nombreUsuario;
	}

	public String getNombreUsuario(){
		return nombreUsuario;
	}

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFechaFin() {
        return fechaFin;
    }

	public void setUrlImagen(String urlImagen){
		this.urlImagen = urlImagen;
	}

	public String getUrlImagen(){
		return urlImagen;
	}

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HerramientaRest that = (HerramientaRest) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

	@Override
 	public String toString(){
		return 
			"HerramientaRest{" + 
			"descripcion = '" + descripcion + '\'' +
                    ",simboloMoneda = '" + simboloMoneda + '\'' +
                    ",fechaInicio = '" + fechaInicio + '\'' +
			",reservada = '" + reservada + '\'' + 
			",idUsuario = '" + idUsuario + '\'' +
                    ",moneda = '" + moneda + '\'' +
                    ",resumen = '" + resumen + '\'' +
			",id = '" + id + '\'' + 
			",nombreUsuario = '" + nombreUsuario + '\'' +
                    ",fechaFin = '" + fechaFin + '\'' +
			",urlImagen = '" + urlImagen + '\'' + 
			"}";
		}
}