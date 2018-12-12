package com.example.lamlv.jsonexample.service.retrofit;

import com.example.lamlv.jsonexample.common.Constants;
import com.example.lamlv.jsonexample.model.weather.WeatherDataModel;
import com.example.lamlv.jsonexample.service.api.APIInterface.IWeatherAPI;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import rx.Observable;

public class WeatherAPI extends BaseAPI{

    private static WeatherAPI weatherAPI;
    private IWeatherAPI iWeatherAPI;
    RequestAPIResponse response;

    private WeatherAPI() {
        super();
        url = Constants.URL_WEATHER;
        iWeatherAPI = CreateRetrofit(IWeatherAPI.class);
    }

    public static WeatherAPI getInstance()
    {
        if(weatherAPI == null)
        {
            weatherAPI = new WeatherAPI();
        }
        return weatherAPI;
    }

    public void loadWeatherData(final HandlerObservableListener handlerObservable)
    {
        Observable observable = iWeatherAPI.getWeatherData(Constants.YQL, Constants.FORMAT, Constants.ENV);

        handlerResponse(observable, new HandlerObservableListener() {
            @Override
            public void responseData(RequestAPIResponse response) {
                if (handlerObservable == null) { return; }
                if (!response.success) {
                    handlerObservable.responseData(response);
                    return;
                }

                WeatherDataModel weatherData = null;
                JsonObject result = (JsonObject) response.result;
                JsonObject query = result.getAsJsonObject("query");
                if (query.isJsonNull()) {
                    response.result = weatherData;
                    handlerObservable.responseData(response);
                    return;
                }
                weatherData = new Gson().fromJson(result, WeatherDataModel.class);
                response.result = weatherData.getQueryModel().getResultsModel().getChannelModel();
                handlerObservable.responseData(response);
            }
        });
    }

    public void loadWeatherDataByCityName(String cityName, final HandlerObservableListener handlerObservable)
    {

        String q = "select * from weather.forecast where u='c' and woeid in (select woeid from geo.places(1) where text=\"" + cityName + "\")";
        Observable observable = iWeatherAPI.getWeatherData(q, Constants.FORMAT, Constants.ENV);

        handlerResponse(observable, new HandlerObservableListener() {
            @Override
            public void responseData(RequestAPIResponse response) {
                if (handlerObservable == null) { return; }
                if (!response.success) {
                    handlerObservable.responseData(response);
                    return;
                }

                WeatherDataModel weatherData = null;
                JsonObject result = (JsonObject) response.result;
                JsonObject query = result.getAsJsonObject("query");
                if (query.isJsonNull()) {
                    response.result = weatherData;
                    handlerObservable.responseData(response);
                    return;
                }
                weatherData = new Gson().fromJson(result, WeatherDataModel.class);

                if (weatherData.getQueryModel().getCount() == 1) {
                    response.success = true;
                    response.message = null;
                    response.result = weatherData.getQueryModel().getResultsModel().getChannelModel();
                    handlerObservable.responseData(response);
                }
                else {
                    response.success = false;
                    response.message = "can't find city information";
                    handlerObservable.responseData(response);
                }

            }
        });
    }

    //region Old code
//    public void loadWeatherData(final HandlerObservableListener handler) {
//        response = new RequestAPIResponse();
//
//        iWeatherAPI.getWeatherData(Constants.DEFAULTYQL, Constants.DEFAULTFORMAT, Constants.ENV)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Subscriber<WeatherData>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        response.message = e.getMessage();
//                    }
//
//                    @Override
//                    public void onNext(WeatherData weatherData) {
//                        if (handler != null) {
//                            response.success = true;
//                            response.message = null;
//                            response.result = weatherData;
//                            handler.response(response);
//                            Log.d("---weather data---", weatherData.toString());
//                        }
//                    }
//                });
//    }
//
//    public void loadWeatherDataByCityName(String cityName, final HandlerObservableListener handler) {
//        response = new RequestAPIResponse();
//
//        String q = "select * from weather.forecast where u='c' and woeid in (select woeid from geo.places(1) where text=\"" + cityName + "\")";
//        iWeatherAPI.getWeatherDataByCityName(q, Constants.DEFAULTFORMAT, Constants.ENV)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Subscriber<WeatherData>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        response.message = e.getMessage();
//                    }
//
//                    @Override
//                    public void onNext(WeatherData weatherData) {
//                        if (handler != null) {
//                            response.success = true;
//                            response.message = null;
//                            response.result = weatherData;
//                            handler.response(response);
//                            Log.d("---weather data---", weatherData.toString());
//                        }
//                    }
//                });
//    }
//
//    public interface HandlerObservableListener {
//        void response(RequestAPIResponse response);
//    }
//
//    public class RequestAPIResponse {
//        public boolean success;
//        public String message;
//        public WeatherData result;
//    }
    //endregion
}
