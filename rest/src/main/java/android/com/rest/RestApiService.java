package android.com.rest;

import android.com.rest.entities.CategoriaRest;
import android.com.rest.entities.ExperienciaRest;
import android.com.rest.entities.HerramientaRest;
import android.com.rest.entities.MonedaRest;
import android.com.rest.entities.UsuariosRest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by cvegaf on 19/11/2018.
 * <p>
 * Encargado de hacer las peticiones HTTP.
 */

public interface RestApiService {

    @GET("/Herramientas.json")
    Call<List<HerramientaRest>> getHerramientas();

    @GET("/Experiencias.json")
    Call<List<ExperienciaRest>> getExperiencias();

    @GET("/Usuarios.json")
    Call<List<UsuariosRest>> getUsuarios();

    @GET("/Categoria.json")
    Call<List<CategoriaRest>> getCategorias();

    @GET("/Moneda.json")
    Call<List<MonedaRest>> getMonedas();
}
