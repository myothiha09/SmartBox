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
    private String lockId;

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
//        )
        .addInterceptor(new StethoInterceptor())
        .build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://ec2-3-95-178-64.compute-1.amazonaws.com/")

                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ApiService.class);
        instance = this;
        this.lockId = "-LbK-mwrfEHGLmZLybdY";
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
        this.token = token;
    }

    public String getLockId() {
        return lockId;
    }

    public void setLockId(String lockId) {
        this.lockId = lockId;
    }
}
