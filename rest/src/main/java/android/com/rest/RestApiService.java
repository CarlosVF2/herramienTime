package android.com.rest;

import android.com.rest.entities.HerramientaRest;

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
}
