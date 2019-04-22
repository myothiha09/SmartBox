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
    String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjY1NmMzZGQyMWQwZmVmODgyZTA5ZTBkODY5MWNhNWM3ZjJiMGQ2MjEiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vanVuaW9yLWRlc2lnbi1zbWFydGxvY2stcHJvZCIsImF1ZCI6Imp1bmlvci1kZXNpZ24tc21hcnRsb2NrLXByb2QiLCJhdXRoX3RpbWUiOjE1NTU5NDE0NjAsInVzZXJfaWQiOiJRbjN0ekhqaXpEZ3NhMkdDcmVXN3E2VHFMZ3MxIiwic3ViIjoiUW4zdHpIaml6RGdzYTJHQ3JlVzdxNlRxTGdzMSIsImlhdCI6MTU1NTk0MTQ2MCwiZXhwIjoxNTU1OTQ1MDYwLCJlbWFpbCI6ImplcmFndWlsb25AZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7ImVtYWlsIjpbImplcmFndWlsb25AZ21haWwuY29tIl19LCJzaWduX2luX3Byb3ZpZGVyIjoicGFzc3dvcmQifX0.0DbWoUJeLxDAKX3IvweTvSlEFgrZcaQ43ffg7GLtbBCdWaLQCVRI_iDXuSxRTa9t7p56M66_9PK7dpUUGyEyAi-Pg08pLOKq76qY8AQrgQAIBBAlI0SCwGcdCnqKUgtO04OVx9iGR7XuqIn8Gbrjdhf67jcJs3XIdkRvOLHXsKwlGpVChwzm-d1kRd6y6b3yJy8YwT0yThX1W6NOCq170yYAXpK0QPSBXlDDEbYPOSdngrbisB5ujBKALIYcbhMxkOOdjLYHJP8SW2FMSFTEC9IgZ6T4idUjjagLx2IkIbpV2mVwCGsjO_PEuVo1nITK55PO0pCwScf6Eeot1DaDfg";

    @GET("api/v1/locks")
    @Headers("Authorization: " + token)

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
