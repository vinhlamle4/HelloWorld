package com.example.lamlv.jsonexample.screens;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lamlv.jsonexample.R;
import com.example.lamlv.jsonexample.app.WeatherApplication;
import com.example.lamlv.jsonexample.model.weather.ChannelModel;
import com.example.lamlv.jsonexample.service.retrofit.BaseAPI;
import com.example.lamlv.jsonexample.service.retrofit.WeatherAPI;
import com.example.lamlv.jsonexample.service.volley.BaseAPIVolley;
import com.example.lamlv.jsonexample.service.volley.WeatherAPIVolley;

public class MainActivity extends AppCompatActivity {


    private TextView tv_location, tv_humidity, tv_temperature, tv_text,tv_wind;
    private ImageView imv_weather_image;
    private EditText edt_city_name;

    private ChannelModel channelModel;
    private WeatherAPI weatherAPI;

    private WeatherAPIVolley weatherAPIVolley;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

        settingProgressDialog();
        //loadWeatherData();
        loadWeatherDataVolley();
    }

    @Override
    protected void onDestroy() {
        channelModel = null;
        weatherAPIVolley.release();
        weatherAPIVolley = null;

        super.onDestroy();
    }

    //region init View and initData
    private void initView() {

        tv_location = findViewById(R.id.tv_location);
        tv_humidity = findViewById(R.id.tv_humidity);
        tv_temperature = findViewById(R.id.tv_temperature);
        tv_text = findViewById(R.id.tv_text);
        tv_wind = findViewById(R.id.tv_wind);

        imv_weather_image = findViewById(R.id.img_weather_image);

        edt_city_name = findViewById(R.id.edt_city_name);
    }

    private void initData() {
        weatherAPI = WeatherAPI.getInstance();
        weatherAPIVolley = WeatherAPIVolley.getInstance();
        channelModel = new ChannelModel();
    }
    //endregion

    //region setting progress dialog
    private void settingProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }
    //endregion

    //region Get weather data with retrofit
    public void loadWeatherData() {

        weatherAPI.loadWeatherData(new BaseAPI.HandlerObservableListener() {
            @Override
            public void responseData(BaseAPI.RequestAPIResponse response) {
                channelModel =(ChannelModel) response.result;

                uploadDataToView();
                progressDialog.dismiss();
            }
        });
    }

    public void loadWeatherDataByCityName()
    {
        progressDialog.show();

        String cityName = edt_city_name.getText().toString().trim();
        if (TextUtils.isEmpty(cityName)) {
            loadWeatherData();
            return;
        }

        weatherAPI.loadWeatherDataByCityName(cityName, new BaseAPI.HandlerObservableListener() {
            @Override
            public void responseData(BaseAPI.RequestAPIResponse response) {

                if (!response.success) {
                    Toast.makeText(MainActivity.this, response.message, Toast.LENGTH_SHORT).show();
                    edt_city_name.setText("");
                    progressDialog.dismiss();
                    return;
                }

                channelModel = (ChannelModel) response.result;
                uploadDataToView();
                edt_city_name.setText("");
                progressDialog.dismiss();

            }
        });

    }
    //endregion

    //region Get weather data with Volley
    public void loadWeatherDataVolley() {
        weatherAPIVolley.loadWeatherData(new BaseAPIVolley.HandlerVolleyListener() {
            @Override
            public void responseAPIVolley(BaseAPIVolley.RequestAPIResponseVolley requestAPIResponseVolley) {

                if (!requestAPIResponseVolley.success) {
                    Toast.makeText(MainActivity.this, requestAPIResponseVolley.message, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }

                channelModel = (ChannelModel) requestAPIResponseVolley.data;
                uploadDataToView();
                loadWeatherImage();
                progressDialog.dismiss();
            }
        });
    }

    public void loadWeatherDataByCityNameVolley()
    {

        String cityName = edt_city_name.getText().toString().trim();

        if (!TextUtils.isEmpty(cityName)) {
            weatherAPIVolley.loadWeatherDataByCityName(cityName, new BaseAPIVolley.HandlerVolleyListener() {
                @Override
                public void responseAPIVolley(BaseAPIVolley.RequestAPIResponseVolley requestAPIResponseVolley) {

                    if (!requestAPIResponseVolley.success) {
                        edt_city_name.setText("");
                        Toast.makeText(MainActivity.this, requestAPIResponseVolley.message, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        return;
                    }

                    channelModel = (ChannelModel) requestAPIResponseVolley.data;
                    uploadDataToView();
                    progressDialog.dismiss();
                    edt_city_name.setText("");
                    loadWeatherImage();
                }
            });
        }
        else
        {
            loadWeatherDataVolley();
        }
    }
    //endregion

    //region load image in API to image view
    public void loadWeatherImage()
    {
        String code = channelModel.getItemModel().getConditionModel().getCode();
        weatherAPIVolley.loadWeatherImage(code, new BaseAPIVolley.HandlerVolleyImageListener() {
            @Override
            public void responseImage(Bitmap image) {
                imv_weather_image.setImageBitmap(image);
                Log.d("image data", image.toString());
            }
        });
    }
    //endregion

    //region button click event
    public void onClickBtnGetData(View view) {
        progressDialog.show();
        loadWeatherDataByCityNameVolley();
    }
    //endregion

    //region set data to view
    public void uploadDataToView() {
        String degreeC = "\u00b0" + "C";
        String percent = "%";
        String speedUnit = channelModel.getUnitsModel().getSpeed();

        //load image with picasso library
//        String code = channelModel.getItemModel().getConditionModel().getCode();
//        String imgUrl = String.format("http://l.yimg.com/a/i/us/we/52/%s.gif",code);
//        Picasso.with(getApplicationContext()).load(imgUrl).into(img_weather_image);


        tv_location.setText(String.format("%s, %s", channelModel.getLocationModel().getCity(), channelModel.getLocationModel().getCountry()));
        tv_humidity.setText(String.format("%s%s", channelModel.getAtmosphereModel().getHumidity(), percent));
        tv_temperature.setText(String.format("%s%s", channelModel.getItemModel().getConditionModel().getTemp(), degreeC));
        tv_text.setText(channelModel.getItemModel().getConditionModel().getText());
        tv_wind.setText(String.format("%s %s", channelModel.getWindModel().getSpeed(), speedUnit));
    }
    //endregion
}
