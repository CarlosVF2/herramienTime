package android.com.rest;

import android.util.Base64;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by cvegaf on 19/11/2018.
 */

public class AuthInterceptor implements Interceptor {

    private String mUser, mPassword;

    public AuthInterceptor(String aUser, String aPassword) {
        mUser = aUser;
        mPassword = aPassword;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        String usuarioContra = mUser + ":" + mPassword;

        Request request = chain.request().newBuilder()
                .addHeader("Authorization", "Basic " + new String(Base64.encode(usuarioContra.getBytes(), Base64.NO_WRAP)))
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("X-CSRF-Token", "FETCH").build();
        HttpUrl url = request.url()
                .newBuilder()
                .build();
        request = request.newBuilder().url(url)
                .build();
        return chain.proceed(request);
    }
}
