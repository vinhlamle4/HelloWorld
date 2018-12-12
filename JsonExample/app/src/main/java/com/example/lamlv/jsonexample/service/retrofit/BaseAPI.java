package com.example.lamlv.jsonexample.service.retrofit;

import android.util.Log;

import com.google.gson.JsonObject;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BaseAPI {

    private static final String TAG = "BaseAPI";

    public String url;

    public <I>I CreateRetrofit(Class<I> iClass)
    {
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(this.url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();
        return retrofit.create(iClass);
    }

    protected void handlerResponse(Observable<Response<JsonObject>> observable, final HandlerObservableListener handler) {
        final RequestAPIResponse response = new RequestAPIResponse();
        response.url = url;

        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Response<JsonObject>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (handler != null) {
                            response.success = false;
                            response.message = e.getLocalizedMessage();
                            response.result = null;

                            handler.responseData(response);
                            Log.d(TAG, "Error: " + e.getLocalizedMessage());
                        }
                    }

                    @Override
                    public void onNext(Response<JsonObject> jsonObjectResponse) {
                        if (handler != null) {
                            response.success = true;
                            response.message = null;
                            response.result = jsonObjectResponse.body();
                        }
                        handler.responseData(response);
                        Log.d(TAG, "OnNext: " + jsonObjectResponse.body());
                    }
                });
    }


    public interface HandlerObservableListener {
        void responseData(RequestAPIResponse response);
    }

    public interface HandlerBooleanListener {
        void responseData(boolean flag);
    }

    public class RequestAPIResponse {
        public String url;

        public boolean success = false;
        public Object result;
        public String message;
    }
}
