package com.example.lamlv.jsonexample.service.volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.lamlv.jsonexample.app.WeatherApplication;

import org.json.JSONObject;

public class BaseAPIVolley {

    public String strUrl;
    public String imageUrl;
    private RequestQueue mRequestQueue;

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(WeatherApplication.getAppContext());
        }
        return mRequestQueue;
    }

    public void handlerResponse(final HandlerVolleyListener handlerVolleyListener) {

        final RequestAPIResponseVolley responseVolley = new RequestAPIResponseVolley();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, strUrl,null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                if (handlerVolleyListener != null) {
                    responseVolley.data = response;
                    responseVolley.success = true;
                    responseVolley.message = null;
                    handlerVolleyListener.responseAPIVolley(responseVolley);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Response Error", error.getMessage());
                responseVolley.success = false;
                responseVolley.data = null;
                responseVolley.message = error.getMessage();
                handlerVolleyListener.responseAPIVolley(responseVolley);
            }
        });
        mRequestQueue.add(jsonObjectRequest);
    }

    public void handlerImageResponse(final HandlerVolleyImageListener handlerVolleyImageListener)
    {
        ImageRequest imageRequest = new ImageRequest(imageUrl, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                handlerVolleyImageListener.responseImage(response);

            }
        }, 0, 0,ImageView.ScaleType.CENTER_CROP, Bitmap.Config.ARGB_8888, null);
        mRequestQueue.add(imageRequest);
    }

    public void onRelease()
    {
        mRequestQueue = null;
        strUrl = null;
        imageUrl = null;
    }

    public interface HandlerVolleyListener {
        void responseAPIVolley(RequestAPIResponseVolley requestAPIResponseVolley);
    }

    public interface HandlerVolleyImageListener{
        void responseImage(Bitmap image);
    }

    public class RequestAPIResponseVolley {
        public boolean success;
        public Object data;
        public String message;
    }


}
