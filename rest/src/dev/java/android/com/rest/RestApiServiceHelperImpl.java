package android.com.rest;

import android.annotation.SuppressLint;
import android.com.rest.entities.CategoriaRest;
import android.com.rest.entities.ExperienciaRest;
import android.com.rest.entities.FiltrosExperienciaRest;
import android.com.rest.entities.FiltrosHerramientasRest;
import android.com.rest.entities.HerramientaRest;
import android.com.rest.entities.InternetException;
import android.com.rest.entities.MonedaRest;
import android.com.rest.entities.UsuariosRest;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;

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
    private final Context context;

    public RestApiServiceHelperImpl(Retrofit.Builder retroFitBuilder, Context context) {
        this.retroFitBuilder = retroFitBuilder;
        this.context = context;
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
        if (!isConnectingToInternet()) {
            throw new InternetException("No esta conectado a internet");
        }
    }

    @Override
    public List<HerramientaRest> getHerramientas() throws InternetException {
        return getHerramientas(null);
    }

    @Override
    public List<HerramientaRest> getHerramientas(FiltrosHerramientasRest filtrosHerramientasRest) throws InternetException {
        checkConnectivity();
        try {
            Response<List<HerramientaRest>> response = restApiService.getHerramientas().execute();

            if (response.isSuccessful()) {
                if (response.body() != null) {
                    //Miramos si hay algun filtro relleno
                    boolean isSomeFilter = filtrosHerramientasRest != null &&
                            (!TextUtils.isEmpty(filtrosHerramientasRest.getDescripcion()) ||
                                    !TextUtils.isEmpty(filtrosHerramientasRest.getPrecioFinal()) ||
                                    !TextUtils.isEmpty(filtrosHerramientasRest.getPrecioInicial()));
                    if (!isSomeFilter) {
                        return response.body();
                    } else {
                        //Filtramos el listado
                        List<HerramientaRest> rests = new ArrayList<>();
                        for (HerramientaRest herramientaRest : response.body()) {
                            if (containsString(herramientaRest.getDescripcion(), filtrosHerramientasRest.getDescripcion())) {
                                rests.add(herramientaRest);
                            }
                            if (TextUtils.isEmpty(filtrosHerramientasRest.getPrecioFinal()) && isPriceMayor(herramientaRest.getPrecio(), filtrosHerramientasRest.getPrecioInicial())) {
                                rests.add(herramientaRest);
                            } else if (TextUtils.isEmpty(filtrosHerramientasRest.getPrecioInicial()) && isPriceMenor(herramientaRest.getPrecio(), filtrosHerramientasRest.getPrecioFinal())) {
                                rests.add(herramientaRest);
                            } else if (isPriceMayor(herramientaRest.getPrecio(), filtrosHerramientasRest.getPrecioInicial()) && isPriceMenor(herramientaRest.getPrecio(), filtrosHerramientasRest.getPrecioFinal())) {
                                rests.add(herramientaRest);
                            }
                        }
                        return rests;
                    }
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
        return getExperiencias(null);
    }

    @Override
    public List<ExperienciaRest> getExperiencias(FiltrosExperienciaRest filtrosExperienciaRest) throws InternetException {
        checkConnectivity();
        try {
            Response<List<ExperienciaRest>> response = restApiService.getExperiencias().execute();

            if (response.isSuccessful()) {
                if (response.body() != null) {
                    //Miramos si hay algun filtro relleno
                    boolean isSomeFilter = filtrosExperienciaRest != null &&
                            (!TextUtils.isEmpty(filtrosExperienciaRest.getDescripcion()) ||
                                    !TextUtils.isEmpty(filtrosExperienciaRest.getPrecioFinal()) ||
                                    !TextUtils.isEmpty(filtrosExperienciaRest.getPrecioInicial()));
                    if (!isSomeFilter) {
                        return response.body();
                    } else {
                        //Filtramos el listado
                        List<ExperienciaRest> rests = new ArrayList<>();
                        for (ExperienciaRest experienciaRest : response.body()) {
                            if (containsString(experienciaRest.getDescripcion(), filtrosExperienciaRest.getDescripcion())) {
                                rests.add(experienciaRest);
                            }
                            if (TextUtils.isEmpty(filtrosExperienciaRest.getPrecioFinal()) && isPriceMayor(experienciaRest.getPrecioHora(), filtrosExperienciaRest.getPrecioInicial())) {
                                rests.add(experienciaRest);
                            } else if (TextUtils.isEmpty(filtrosExperienciaRest.getPrecioInicial()) && isPriceMenor(experienciaRest.getPrecioHora(), filtrosExperienciaRest.getPrecioFinal())) {
                                rests.add(experienciaRest);
                            } else if (isPriceMayor(experienciaRest.getPrecioHora(), filtrosExperienciaRest.getPrecioInicial()) && isPriceMenor(experienciaRest.getPrecioHora(), filtrosExperienciaRest.getPrecioFinal())) {
                                rests.add(experienciaRest);
                            }
                        }
                        return rests;
                    }
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
        try {
            Thread.sleep(1000); //Porque no funciona correctamente y tiene que tener un tiempo de espera para luego hacer otra petición actualizada
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    @Override
    public void postUsuario(List<UsuariosRest> usuariosRests) throws InternetException {
        checkConnectivity();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("Usuarios");
        Map<String, UsuariosRest> users = new HashMap<>();
        int i = 0;
        for (UsuariosRest usuariosRest : usuariosRests) {
            users.put(String.valueOf(i), usuariosRest);
            i++;
        }
        databaseReference.setValue(users);
    }

    @Override
    public List<CategoriaRest> getCategorias() throws InternetException {
        checkConnectivity();
        try {
            Response<List<CategoriaRest>> response = restApiService.getCategorias().execute();

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
    public List<MonedaRest> getMonedas() throws InternetException {
        checkConnectivity();
        try {
            Response<List<MonedaRest>> response = restApiService.getMonedas().execute();

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

    private boolean isConnectingToInternet() {
        boolean status = false;
        @SuppressLint("WrongConstant") ConnectivityManager connectivityManager = (ConnectivityManager) this.context.getSystemService("connectivity");
        int var6;
        if (Build.VERSION.SDK_INT >= 21) {
            Network[] networks = connectivityManager.getAllNetworks();
            Network[] var5 = networks;
            var6 = networks.length;

            for (int var7 = 0; var7 < var6; ++var7) {
                Network mNetwork = var5[var7];
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(mNetwork);
                if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                    status = true;
                }
            }
        } else if (connectivityManager != null) {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info != null) {
                NetworkInfo[] var10 = info;
                int var11 = info.length;

                for (var6 = 0; var6 < var11; ++var6) {
                    NetworkInfo anInfo = var10[var6];
                    if (anInfo != null && anInfo.isAvailable() && anInfo.isConnected()) {
                        status = true;
                    }
                }
            }
        }
        return status;
    }

    private boolean containsString(String valueOriginal, String field) {
        return !TextUtils.isEmpty(field) && !TextUtils.isEmpty(valueOriginal) && valueOriginal.toUpperCase().contains(field.toUpperCase());
    }

    private boolean isPriceMayor(String priceOriginal, String field) {
        if (TextUtils.isEmpty(priceOriginal) || TextUtils.isEmpty(field)) {
            return false;
        }
        double priceO = Double.parseDouble(priceOriginal);
        double fieldPrice = Double.parseDouble(field);
        return priceO >= fieldPrice;
    }

    private boolean isPriceMenor(String priceOriginal, String field) {
        if (TextUtils.isEmpty(priceOriginal) || TextUtils.isEmpty(field)) {
            return false;
        }
        double priceO = Double.parseDouble(priceOriginal);
        double fieldPrice = Double.parseDouble(field);
        return priceO <= fieldPrice;

    }
}
