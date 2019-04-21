package com.team8303.api;

import com.team8303.api.model.LockHistoryResponse;
import com.team8303.api.model.LockPasswordResponse;
import com.team8303.api.model.LockPasswordsResponse;
import com.team8303.api.model.NoResponse;
import com.team8303.api.model.PostLockPasswordArgs;
import com.team8303.api.model.PutLockPasswordArgs;
import com.team8303.api.model.PutLockStatusArgs;
import com.team8303.api.model.UserLockResponse;
import com.team8303.api.model.UserLockStatusResponse;
import com.team8303.api.model.UserResponse;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

public interface ApiService {
    String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjVmYjMyOWRmNjdiYjY4NDVkNDk1NDNiMGM0OWIzNWM4ODg1NzllYmEiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiSmVyZW15IEFndWlsb24iLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDQuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy05T1Z3OFpOcF9xUS9BQUFBQUFBQUFBSS9BQUFBQUFBQUUzYy83VDg4eERfSEZHYy9waG90by5qcGciLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vanVuaW9yLWRlc2lnbi1zbWFydC1sb2NrIiwiYXVkIjoianVuaW9yLWRlc2lnbi1zbWFydC1sb2NrIiwiYXV0aF90aW1lIjoxNTU1NTQxNTYwLCJ1c2VyX2lkIjoiWTdkeXFZVE1ZSU1iVXdIWFR5Z1RIdHBoekFoMiIsInN1YiI6Ilk3ZHlxWVRNWUlNYlV3SFhUeWdUSHRwaHpBaDIiLCJpYXQiOjE1NTU1NDE1NjAsImV4cCI6MTU1NTU0NTE2MCwiZW1haWwiOiJqZXJhZ3VpbG9uQGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7Imdvb2dsZS5jb20iOlsiMTA4OTQ3MzM1MzY3OTg0OTAzNzMzIl0sImVtYWlsIjpbImplcmFndWlsb25AZ21haWwuY29tIl19LCJzaWduX2luX3Byb3ZpZGVyIjoicGFzc3dvcmQifX0.e9gPBf-TYyz0N2HOWpMbGLL5MX1sZ5mTxLLYQCONRT_MXUjhQYrleulW8xlAKpJ6lla_bfs7bmqbLFC7LhtNiHF-wKFpcXzg-VF03woEjXHXK-SoUkvrsupgU1yXWYi6867_jx-_XEuntZO4LwgcjlHFWboTX0V6tuqVKfSbLo4peHCfQCBGsav8yo5cyyxXSThTWLV_j2PfC9YxH_9PeG3suj-pIZBp7fBGCAVWLKDuMgloD-bq72O0YnS88o_IrZFoSp7vb2s2mnL4TLaQce0yKH88gChmbsgB6Y1zUS5gN_UW_WGl5gQCbDsx72UvlBy1f2PP5mzySXq4761zVg";

    @GET("api/v1/locks")
    @Headers("Authorization: " +
            "eyJhbGciOiJSUzI1NiIsImtpZCI6ImZmMWRmNWExNWI1Y2Y1ODJiNjFhMjEzODVjMGNmYWVkZmRiNmE3NDgiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiSmVyZW15IEFndWlsb24iLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDQuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy05T1Z3OFpOcF9xUS9BQUFBQUFBQUFBSS9BQUFBQUFBQUUzYy83VDg4eERfSEZHYy9waG90by5qcGciLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vanVuaW9yLWRlc2lnbi1zbWFydC1sb2NrIiwiYXVkIjoianVuaW9yLWRlc2lnbi1zbWFydC1sb2NrIiwiYXV0aF90aW1lIjoxNTU0MDU2MzM3LCJ1c2VyX2lkIjoiWTdkeXFZVE1ZSU1iVXdIWFR5Z1RIdHBoekFoMiIsInN1YiI6Ilk3ZHlxWVRNWUlNYlV3SFhUeWdUSHRwaHpBaDIiLCJpYXQiOjE1NTQwNTYzMzcsImV4cCI6MTU1NDA1OTkzNywiZW1haWwiOiJqZXJhZ3VpbG9uQGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7Imdvb2dsZS5jb20iOlsiMTA4OTQ3MzM1MzY3OTg0OTAzNzMzIl0sImVtYWlsIjpbImplcmFndWlsb25AZ21haWwuY29tIl19LCJzaWduX2luX3Byb3ZpZGVyIjoicGFzc3dvcmQifX0.FiU7AFsRJMRmHPHiyugNLaRLuBTtrk6VD-FmjhuvwoiiErH8T69ztdiod-S3IYoN8r4hNbOZe4AcPFQmw_yfsWBluhXqyOd6bOMm-mLXUb0v51T1Bgrw8S-2R-EjE67ZRV8fh0T2vOvDZa-Y3ar9WUYJTZ8OuHhr3fT7mc6pTl0J9YCcGJRBNR9pNf3oT1zVdy8mG98RW1GdkIn8OdDuAremEF02WnYdwVtVhEqmP9Vpq042oo1vp1j_Xm8CwmifHham8df64kx2n_P0Eqh_V5ZRA1ZvIbg_4O7Lu68LmvpaexXDNLNTzBZ_af32gYJynGz_0VijeLK6RT7_kGOfKg")

    public Observable<Response<UserLockResponse>> getLockList();

    @GET("api/v1/locks/{lockId}/status")
    @Headers("Authorization: " + token)
    public Observable<Response<UserLockStatusResponse>> getLockStatus(@Path("lockId") String lockId);


    @POST("api/v1/locks")
    @Headers("Authorization: " + token)
    public Observable<Response<UserLockResponse>> postLock(@Body UserLockResponse ownedLockIds);

    @PUT("api/v1/locks/{lockId}/status")
    @Headers("Authorization: " + token)
    public Observable<Response<PutLockStatusArgs>> updateLockStatus(@Path("lockId") String lockId, @Body PutLockStatusArgs lockStatusArgs);

    @DELETE("api/v1/locks/{lockId}")
    @Headers("Authorization: " + token)
    public Observable<Response<UserLockResponse>> deleteLockId(@Path("lockId") String lockId);

    @GET("api/v1/locks/{lockId}/history")
    @Headers("Authorization: " + token)
    public Observable<Response<LockHistoryResponse>> getLockHistory(@Path("lockId") String lockId);

    @GET("api/v1/locks/{lockId}/passwords")
    @Headers("Authorization: " + token)
    public Observable<Response<LockPasswordsResponse>> getPasswordData(@Path("lockId") String lockId);

    @POST("api/v1/locks/{lockId}/passwords")
    @Headers("Authorization: " + token)
    public Observable<Response<LockPasswordResponse>> postLockPassword(@Path("lockId") String lockId, @Body PostLockPasswordArgs lockPasswordArgs);

    @GET("api/v1/locks/{lockId}/passwords/{passwordId}")
    @Headers("Authorization: " + token)
    public Observable<Response<LockPasswordResponse>> getLockPasswordData(@Path("lockId") String lockId, @Path("passwordId") String passwordId);

    @PUT("api/v1/locks/{lockId}/passwords/{passwordId}")
    @Headers("Authorization: " + token)
    public Observable<Response<LockPasswordResponse>> putLockPassword(@Path("lockId") String lockId, @Path("passwordId") String passwordId,
                                                                      @Body PutLockPasswordArgs putLockPasswordArgs);

    @DELETE("api/v1/locks/{lockId}/passwords/{passwordId}")
    @Headers("Authorization: " + token)
    public Observable<Response<NoResponse>> deleteLockPassword(@Path("lockId") String lockId, @Path("passwordId") String passwordId);

    @GET("api/v1/user")
    @Headers("Authorization: " + token)
    public Observable<Response<UserResponse>> getUserInfo();

    @POST("api/v1/user")
    @Headers("Authorization: " + token)
    public Observable<Response<UserResponse>> postUserInfo();



}
