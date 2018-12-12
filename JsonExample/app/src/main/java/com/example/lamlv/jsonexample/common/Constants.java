package com.example.lamlv.jsonexample.common;

public class Constants {
    public static final int TIME_OUT_SERVER = 30;

    public static final int    HTTP_SUCCESS_CODE               = 200;

    public static final String PARAM_CODE                      = "code";

    public static final String PARAM_RESULT           = "result";

    public static final String PARAM_API_KEY               = "apikey";

    //region Constant Variable of retrofit
    public static final String URL_WEATHER = "https://query.yahooapis.com/v1/";
    public static final String YQL = "select * from weather.forecast where u='c' and woeid in (select woeid from geo.places(1) where text=\"hochiminh, vn\")";
    public static final String FORMAT = "json";
    public static final String ENV = "store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
    //endregion

    public static final String YQL_VOLLEY = "select * from weather.forecast where u='c' and woeid in (select woeid from geo.places(1) where text=\"%s\")";
    public static final String URL_VOLLEY = "https://query.yahooapis.com/v1/public/yql?q=%s&format=json";

    public static final String IMAGE_URL = "http://l.yimg.com/a/i/us/we/52/%s.gif";



}
