package com.example.lamlv.jsonexample.service.volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import com.example.lamlv.jsonexample.common.Constants;
import com.example.lamlv.jsonexample.model.weather.WeatherDataModel;
import com.google.gson.Gson;

import org.json.JSONObject;

public class WeatherAPIVolley extends BaseAPIVolley {

    private static WeatherAPIVolley weatherAPIVolley;
    private String cityName;
    private String yql;
    private String url;

    private  WeatherAPIVolley () {
        super();
        getRequestQueue();
    }

    public static WeatherAPIVolley getInstance()
    {
        if(weatherAPIVolley == null)
        {
            weatherAPIVolley = new WeatherAPIVolley();
        }
        return weatherAPIVolley;
    }

    public void loadWeatherData(final HandlerVolleyListener handlerVolleyListener) {

        cityName = "hochiminh";
        yql = String.format(Constants.YQL_VOLLEY, cityName);
        url = String.format(Constants.URL_VOLLEY, Uri.encode(yql));
        strUrl = url;
        handlerResponse(new HandlerVolleyListener() {
            @Override
            public void responseAPIVolley(RequestAPIResponseVolley requestAPIResponseVolley) {
                if (handlerVolleyListener == null) {
                    return;
                } else if (!requestAPIResponseVolley.success) {
                    handlerVolleyListener.responseAPIVolley(requestAPIResponseVolley);
                    return;
                } else {
                    WeatherDataModel weatherData = null;
                    JSONObject jsonObject = (JSONObject) requestAPIResponseVolley.data;
                    Gson gson = new Gson();

                    weatherData = gson.fromJson(jsonObject.toString(), WeatherDataModel.class);
                    requestAPIResponseVolley.data = null;

                    if (weatherData.getQueryModel().getCount() == 1) {
                        requestAPIResponseVolley.success = true;
                        requestAPIResponseVolley.message = null;
                        requestAPIResponseVolley.data = weatherData.getQueryModel().getResultsModel().getChannelModel();
                        handlerVolleyListener.responseAPIVolley(requestAPIResponseVolley);
                    } else {
                        requestAPIResponseVolley.success = false;
                        requestAPIResponseVolley.message = "Can't find city information";
                        requestAPIResponseVolley.data = null;
                        handlerVolleyListener.responseAPIVolley(requestAPIResponseVolley);
                    }
                }
            }
        });
    }

    public void loadWeatherDataByCityName(String cityName, final HandlerVolleyListener handlerVolleyListener) {
        this.cityName = cityName;
        yql = String.format(Constants.YQL_VOLLEY, cityName);
        url = String.format(Constants.URL_VOLLEY, Uri.encode(yql));
        strUrl = url;
        handlerResponse(new HandlerVolleyListener() {
            @Override
            public void responseAPIVolley(RequestAPIResponseVolley requestAPIResponseVolley) {
                if (handlerVolleyListener == null) {
                    return;
                } else if (!requestAPIResponseVolley.success) {
                    handlerVolleyListener.responseAPIVolley(requestAPIResponseVolley);
                    return;
                } else {
                    WeatherDataModel weatherData = null;
                    JSONObject jsonObject = (JSONObject) requestAPIResponseVolley.data;
                    Gson gson = new Gson();

                    weatherData = gson.fromJson(jsonObject.toString(), WeatherDataModel.class);
                    requestAPIResponseVolley.data = null;

                    if (weatherData.getQueryModel().getCount() == 1) {
                        requestAPIResponseVolley.success = true;
                        requestAPIResponseVolley.message = null;
                        requestAPIResponseVolley.data = weatherData.getQueryModel().getResultsModel().getChannelModel();
                        handlerVolleyListener.responseAPIVolley(requestAPIResponseVolley);
                    } else {
                        requestAPIResponseVolley.success = false;
                        requestAPIResponseVolley.message = "Can't find city information";
                        requestAPIResponseVolley.data = null;
                        handlerVolleyListener.responseAPIVolley(requestAPIResponseVolley);
                    }
                }
            }
        });
    }

    public void loadWeatherImage(String code,final HandlerVolleyImageListener handlerVolleyImageListener)
    {
        imageUrl = String.format(Constants.IMAGE_URL, code);
        handlerImageResponse(new HandlerVolleyImageListener() {
            @Override
            public void responseImage(Bitmap image) {
                handlerVolleyImageListener.responseImage(image);
            }
        });
    }

    public void release()
    {
        onRelease();
    }


}
