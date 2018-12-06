package android.com.rest.entities;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseHerramientas {

	@SerializedName("Herramientas")
	private List<HerramientaRest> herramientas;

	public void setHerramientas(List<HerramientaRest> herramientas){
		this.herramientas = herramientas;
	}

	public List<HerramientaRest> getHerramientas(){
		return herramientas;
	}

	@Override
 	public String toString(){
		return 
			"ResponseHerramientas{" +
			"herramientas = '" + herramientas + '\'' + 
			"}";
		}
}