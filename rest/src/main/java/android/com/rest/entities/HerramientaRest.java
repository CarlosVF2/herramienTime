package android.com.rest.entities;


import com.google.gson.annotations.SerializedName;

public class HerramientaRest{

	@SerializedName("descripcion")
	private String descripcion;

	@SerializedName("reservada")
	private String reservada;

	@SerializedName("idUsuario")
	private String idUsuario;

	@SerializedName("id")
	private String id;

	@SerializedName("nombreUsuario")
	private String nombreUsuario;

	@SerializedName("urlImagen")
	private String urlImagen;

	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}

	public String getDescripcion(){
		return descripcion;
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

	public void setUrlImagen(String urlImagen){
		this.urlImagen = urlImagen;
	}

	public String getUrlImagen(){
		return urlImagen;
	}

	@Override
 	public String toString(){
		return 
			"HerramientaRest{" + 
			"descripcion = '" + descripcion + '\'' + 
			",reservada = '" + reservada + '\'' + 
			",idUsuario = '" + idUsuario + '\'' + 
			",id = '" + id + '\'' + 
			",nombreUsuario = '" + nombreUsuario + '\'' + 
			",urlImagen = '" + urlImagen + '\'' + 
			"}";
		}
}