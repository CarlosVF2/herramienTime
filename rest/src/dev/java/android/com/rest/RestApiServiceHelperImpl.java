package android.com.rest;

import android.com.rest.entities.ExperienciaRest;
import android.com.rest.entities.HerramientaRest;
import android.com.rest.entities.InternetException;
import android.com.rest.entities.UsuariosRest;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RestApiServiceHelperImpl implements RestApiServiceHelper {
    private Retrofit.Builder retroFitBuilder;
    private RestApiService restApiService;
    private FirebaseDatabase mFirebaseInstance;

    public RestApiServiceHelperImpl(Retrofit.Builder retroFitBuilder) {
        this.retroFitBuilder = retroFitBuilder;
        mFirebaseInstance = FirebaseDatabase.getInstance();
    }

    private OkHttpClient getclientToken() {
        return new OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .build();
    }

    private void checkConnectivity() throws InternetException {
        if (restApiService == null) {
            restApiService = retroFitBuilder.client(getclientToken()).build().create(RestApiService.class);
        }
    }

    @Override
    public List<HerramientaRest> getHerramientas() throws InternetException {
        checkConnectivity();
        try {
            Response<List<HerramientaRest>> response = restApiService.getHerramientas().execute();

            if (response.isSuccessful()) {
                if (response.body() != null) {
                    return response.body();
                } else {
                    return new ArrayList<>();
                }
            } else {
                return null;
            }
        } catch (IOException ex) {
        }
        return new ArrayList<>();
    }

    @Override
    public List<ExperienciaRest> getExperiencias() throws InternetException {
        checkConnectivity();
        try {
            Response<List<ExperienciaRest>> response = restApiService.getExperiencias().execute();

            if (response.isSuccessful()) {
                if (response.body() != null) {
                    return response.body();
                } else {
                    return new ArrayList<>();
                }
            } else {
                return null;
            }
        } catch (IOException ex) {
        }
        return new ArrayList<>();
    }

    @Override
    public List<UsuariosRest> getUsuarios() throws InternetException {
        checkConnectivity();
        try {
            Response<List<UsuariosRest>> response = restApiService.getUsuarios().execute();

            if (response.isSuccessful()) {
                if (response.body() != null) {
                    return response.body();
                } else {
                    return new ArrayList<>();
                }
            } else {
                return null;
            }
        } catch (IOException ex) {
        }
        return new ArrayList<>();
    }

    @Override
    public void postHerramienta(List<HerramientaRest> herramientaResponse) throws InternetException {
        checkConnectivity();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("Herramientas");
        Map<String, HerramientaRest> users = new HashMap<>();
        int i = 0;
        for (HerramientaRest herramientaRest : herramientaResponse) {
            users.put(String.valueOf(i), herramientaRest);
            i++;
        }
        databaseReference.setValue(users);
    }

    @Override
    public void postExperiencia(List<ExperienciaRest> experienciaRests) throws InternetException {
        checkConnectivity();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("Experiencias");
        Map<String, ExperienciaRest> users = new HashMap<>();
        int i = 0;
        for (ExperienciaRest experienciaRest : experienciaRests) {
            users.put(String.valueOf(i), experienciaRest);
            i++;
        }
        databaseReference.setValue(users);
    }

    private void generateError(Response response) throws InternetException {
        StringBuilder messageErrorGateway = new StringBuilder();
        if (response != null) {
            switch (response.code()) {
                //Listamos los errores conocidos
                case 400:
                    //400 Bad Request
                    //messageErrorGateway.append(context.getString(R.string.error_code_400, response.code()));
                    break;
                case 401:
                    //401 Unauthorized
                    //messageErrorGateway.append(context.getString(R.string.error_code_401, response.code()));
                    break;
                case 403:
                    //403 Forbidden
                    //messageErrorGateway.append(context.getString(R.string.error_code_403, response.code()));
                    //404 Not Found
                    //messageErrorGateway.append(context.getString(R.string.error_code_404, response.code()));
                    break;
                case 408:
                    //408 Request Timeout
                    //messageErrorGateway.append(context.getString(R.string.error_code_408, response.code()));
                    break;
                case 500:
                    //500 Error servidor
                    //messageErrorGateway.append(context.getString(R.string.error_code_500, response.code()));
                    break;
                default:
                    //messageErrorGateway.append(context.getString(R.string.error_default, response.code()));
                    break;

            }
            messageErrorGateway
                    .append(System.lineSeparator())
                    .append(System.lineSeparator());

            String res = "";
            try {
                InputStream inputStream = response.errorBody().byteStream();
                res = toString(inputStream);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private String toString(InputStream inputStream) throws IOException {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //System.out.println(LoggerBase.getCause(e.getCause()));
                }
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                //System.out.println(LoggerBase.getCause(e.getCause()));
            }
        }
        return sb.toString();
    }
}
