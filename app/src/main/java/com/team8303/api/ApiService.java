package com.team8303.api;

import com.team8303.api.model.UserLockResponse;

import retrofit2.Response;
import retrofit2.http.GET;
import rx.Observable;

public interface ApiService {
    @GET("api/v1/locks")
    public Observable<Response<UserLockResponse>> getLockList();
}
