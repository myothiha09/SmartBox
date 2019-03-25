package com.team8303;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.team8303.api.ApiService;
import com.team8303.api.LockApiService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SmartBoxApplication extends Application {

    public static SmartBoxApplication instance;

    private ApiService service;
    private LockApiService lockApiService;

    private String token;

    @Override
    public void onCreate() {
        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(
//                new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        Request request = chain.request();
//                        request.newBuilder().addHeader("Authorization", token).build();
//                        return chain.proceed(request);
//                    }
//                }
//        ).addInterceptor(new StethoInterceptor())
        .build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://c1c70a79.ngrok.io/")
//                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ApiService.class);
        instance = this;
        lockApiService = new LockApiService();
        Stetho.initializeWithDefaults(this);
        super.onCreate();
    }

    public ApiService getService() {
        return service;
    }

    public LockApiService getLockApiService() {
        return lockApiService;
    }

    public synchronized static SmartBoxApplication getInstance() {
        //return getInstance(instance.token);
        return instance;
    }

    public synchronized static SmartBoxApplication getInstance(String newToken) {
        instance.token = newToken;
        return instance;
    }

    public void setToken(String token) {
        //this.token = token;
        this.token = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImZmMWRmNWExNWI1Y2Y1ODJiNjFhMjEzODVjMGNmYWVkZmRiNmE3NDgiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiSmVyZW15IEFndWlsb24iLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDQuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy05T1Z3OFpOcF9xUS9BQUFBQUFBQUFBSS9BQUFBQUFBQUUzYy83VDg4eERfSEZHYy9waG90by5qcGciLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vanVuaW9yLWRlc2lnbi1zbWFydC1sb2NrIiwiYXVkIjoianVuaW9yLWRlc2lnbi1zbWFydC1sb2NrIiwiYXV0aF90aW1lIjoxNTU0MDU2MzM3LCJ1c2VyX2lkIjoiWTdkeXFZVE1ZSU1iVXdIWFR5Z1RIdHBoekFoMiIsInN1YiI6Ilk3ZHlxWVRNWUlNYlV3SFhUeWdUSHRwaHpBaDIiLCJpYXQiOjE1NTQwNTYzMzcsImV4cCI6MTU1NDA1OTkzNywiZW1haWwiOiJqZXJhZ3VpbG9uQGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7Imdvb2dsZS5jb20iOlsiMTA4OTQ3MzM1MzY3OTg0OTAzNzMzIl0sImVtYWlsIjpbImplcmFndWlsb25AZ21haWwuY29tIl19LCJzaWduX2luX3Byb3ZpZGVyIjoicGFzc3dvcmQifX0.FiU7AFsRJMRmHPHiyugNLaRLuBTtrk6VD-FmjhuvwoiiErH8T69ztdiod-S3IYoN8r4hNbOZe4AcPFQmw_yfsWBluhXqyOd6bOMm-mLXUb0v51T1Bgrw8S-2R-EjE67ZRV8fh0T2vOvDZa-Y3ar9WUYJTZ8OuHhr3fT7mc6pTl0J9YCcGJRBNR9pNf3oT1zVdy8mG98RW1GdkIn8OdDuAremEF02WnYdwVtVhEqmP9Vpq042oo1vp1j_Xm8CwmifHham8df64kx2n_P0Eqh_V5ZRA1ZvIbg_4O7Lu68LmvpaexXDNLNTzBZ_af32gYJynGz_0VijeLK6RT7_kGOfKg";
    }
}
