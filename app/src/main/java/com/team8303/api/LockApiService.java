package com.team8303.api;

import android.util.EventLog;

import com.team8303.SmartBoxApplication;
import com.team8303.api.model.LockHistoryResponse;
import com.team8303.api.model.LockPasswordResponse;
import com.team8303.api.model.LockPasswordsResponse;
import com.team8303.api.model.NoResponse;
import com.team8303.api.model.PostLockPasswordArgs;
import com.team8303.api.model.PutLockPasswordArgs;
import com.team8303.api.model.UserLockResponse;
import com.team8303.api.model.UserLockStatusResponse;
import com.team8303.api.model.PutLockStatusArgs;
import com.team8303.api.model.PutUserLockStatusResponse;
import com.team8303.api.model.UserResponse;
import com.team8303.events.DeleteLockIdEvent;
import com.team8303.events.DeleteLockPasswordEvent;
import com.team8303.events.GetLockHistoryEvent;
import com.team8303.events.GetPasswordDataEvent;
import com.team8303.events.GetUserInfoEvent;
import com.team8303.events.LockListEvent;
import com.team8303.events.LockStatusEvent;
import com.team8303.events.PostLockEvent;
import com.team8303.events.PostLockPasswordEvent;
import com.team8303.events.UpdateLockStatusEvent;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Response;
import rx.Observer;
import rx.schedulers.Schedulers;

import java.util.List;

public class LockApiService {
    public void getLockList() {
        SmartBoxApplication.getInstance().getService().getLockList()
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<UserLockResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<UserLockResponse> userLockResponse) {
                        if (userLockResponse.isSuccessful()) {
                            EventBus.getDefault().postSticky(new LockListEvent(userLockResponse.body(), true));
                        } else {
                            EventBus.getDefault().postSticky(new LockListEvent(null, false));
                        }
                    }
                });
    }

    public void getLockStatus(String lockId) {
        SmartBoxApplication.getInstance().getService().getLockStatus(lockId)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<UserLockStatusResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<UserLockStatusResponse> userLockStatusResponse) {
                        if (userLockStatusResponse.isSuccessful()) {

                            EventBus.getDefault().postSticky(new LockStatusEvent(userLockStatusResponse.body(), true));
                        } else {
                            EventBus.getDefault().postSticky(new LockStatusEvent(null, false));
                        }
                    }
                });
    }

    public void postLock(UserLockResponse ownedLockIds) {
        SmartBoxApplication.getInstance().getService().postLock(ownedLockIds)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<UserLockResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<UserLockResponse> userLockResponse) {
                        if (userLockResponse.isSuccessful()) {
                            EventBus.getDefault().postSticky(new PostLockEvent(userLockResponse.body(), true));
                        } else {
                            EventBus.getDefault().postSticky(new PostLockEvent(null, false));
                        }
                    }
                });
    }

    public void updateLockStatus(String lockId, PutLockStatusArgs lockStatusArgs) {
        SmartBoxApplication.getInstance().getService().updateLockStatus(lockId, lockStatusArgs)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<PutLockStatusArgs>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<PutLockStatusArgs> putUserLockStatusResponse) {
                        if (putUserLockStatusResponse.isSuccessful()) {
                            EventBus.getDefault().postSticky(new UpdateLockStatusEvent(putUserLockStatusResponse.body(), true));
                        } else {
                            EventBus.getDefault().postSticky(new UpdateLockStatusEvent(null, false));
                        }
                    }
                });
    }

    public void deleteLockId(String lockId) {
        SmartBoxApplication.getInstance().getService().deleteLockId(lockId)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<UserLockResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<UserLockResponse> deleteLockIdResponse) {
                        if (deleteLockIdResponse.isSuccessful()) {
                            EventBus.getDefault().postSticky(new DeleteLockIdEvent(deleteLockIdResponse.body(), true));
                        } else {
                            EventBus.getDefault().postSticky(new DeleteLockIdEvent(null, false));
                        }
                    }
                });
    }

    public void getLockHistory(String lockId) {
        SmartBoxApplication.getInstance().getService().getLockHistory(lockId)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<LockHistoryResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<LockHistoryResponse> lockHistoryResponse) {
                        if (lockHistoryResponse.isSuccessful()) {
                            EventBus.getDefault().postSticky(new GetLockHistoryEvent(lockHistoryResponse.body(), true));
                        } else {
                            EventBus.getDefault().postSticky(new GetLockHistoryEvent(null, false));
                        }
                    }
                });
    }

    public void getPasswordData(String lockId) {
        SmartBoxApplication.getInstance().getService().getPasswordData(lockId)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<LockPasswordsResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<LockPasswordsResponse> lockPasswordsResponse) {
                        if (lockPasswordsResponse.isSuccessful()) {
                            EventBus.getDefault().postSticky(new GetPasswordDataEvent(lockPasswordsResponse.body(), true));
                        } else {
                            EventBus.getDefault().postSticky(new GetPasswordDataEvent(null, false));
                        }
                    }
                });
    }

    public void postLockPassword(String lockId, PostLockPasswordArgs lockPasswordArgs) {
        SmartBoxApplication.getInstance().getService().postLockPassword(lockId, lockPasswordArgs)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<LockPasswordResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<LockPasswordResponse> lockPasswordResponse) {
                        if (lockPasswordResponse.isSuccessful()) {
                            EventBus.getDefault().postSticky(new PostLockPasswordEvent(lockPasswordResponse.body(), true));
                        } else {
                            EventBus.getDefault().postSticky(new PostLockPasswordEvent(null, false));
                        }
                    }
                });
    }

    public void getLockPasswordData(String lockId, String passwordId) {
        SmartBoxApplication.getInstance().getService().getLockPasswordData(lockId, passwordId)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<LockPasswordResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<LockPasswordResponse> lockPasswordResponse) {
                        if (lockPasswordResponse.isSuccessful()) {
                            EventBus.getDefault().postSticky(new PostLockPasswordEvent(lockPasswordResponse.body(), true));
                        } else {
                            EventBus.getDefault().postSticky(new PostLockPasswordEvent(null, false));
                        }
                    }
                });
    }

    public void putLockPassword(String lockId, String passwordId, PutLockPasswordArgs putLockPasswordArgs) {
        SmartBoxApplication.getInstance().getService().putLockPassword(lockId, passwordId, putLockPasswordArgs)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<LockPasswordResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<LockPasswordResponse> lockPasswordResponse) {
                        if (lockPasswordResponse.isSuccessful()) {
                            EventBus.getDefault().postSticky(new PostLockPasswordEvent(lockPasswordResponse.body(), true));
                        } else {
                            EventBus.getDefault().postSticky(new PostLockPasswordEvent(null, false));
                        }
                    }
                });
    }

    public void deleteLockPassword(String lockId, String passwordId) {
        SmartBoxApplication.getInstance().getService().deleteLockPassword(lockId, passwordId)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<NoResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<NoResponse> lockPasswordResponse) {
                        if (lockPasswordResponse.isSuccessful()) {
                            EventBus.getDefault().postSticky(new DeleteLockPasswordEvent(lockPasswordResponse.body(), true));
                        } else {
                            EventBus.getDefault().postSticky(new DeleteLockPasswordEvent(null, false));
                        }
                    }
                });
    }

    public void getUserInfo() {
        SmartBoxApplication.getInstance().getService().getUserInfo()
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<UserResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<UserResponse> userResponse) {
                        if (userResponse.isSuccessful()) {
                            EventBus.getDefault().postSticky(new GetUserInfoEvent(userResponse.body(), true));
                        } else {
                            EventBus.getDefault().postSticky(new GetUserInfoEvent(null, false));
                        }
                    }
                });
    }

    public void postUserInfo() {
        SmartBoxApplication.getInstance().getService().postUserInfo()
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<UserResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<UserResponse> userResponse) {
                        if (userResponse.isSuccessful()) {
                            EventBus.getDefault().postSticky(new GetUserInfoEvent(userResponse.body(), true));
                        } else {
                            EventBus.getDefault().postSticky(new GetUserInfoEvent(null, false));
                        }
                    }
                });
    }

}
