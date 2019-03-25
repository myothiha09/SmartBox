package com.team8303.api;

import android.util.EventLog;

import com.team8303.SmartBoxApplication;
import com.team8303.api.model.UserLockResponse;
import com.team8303.events.LockListEvent;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Response;
import rx.Observer;
import rx.schedulers.Schedulers;

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
}
