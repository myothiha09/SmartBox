package com.team8303.api;

import android.util.EventLog;

import com.team8303.SmartBoxApplication;
import com.team8303.api.model.UserLockResponse;
import com.team8303.api.model.UserLockStatusResponse;
import com.team8303.events.LockListEvent;
import com.team8303.events.LockStatusEvent;

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

    public void addLocks(List<String> lockIds) {
        UserLockResponse request = new UserLockResponse();
        request.setOwnedLockIds(lockIds);
        SmartBoxApplication.getInstance().getService().updateOwnedLock(request)
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
                    public void onNext(Response<UserLockResponse> userLockResponseResponse) {

                    }
                });

    }
}
