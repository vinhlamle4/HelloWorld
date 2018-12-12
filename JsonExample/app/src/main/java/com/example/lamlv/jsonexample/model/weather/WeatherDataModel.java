package com.example.lamlv.jsonexample.model.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherDataModel {

    @SerializedName("query")
    @Expose
    private QueryModel queryModel;

    public QueryModel getQueryModel() {
        return queryModel;
    }

    public void setQueryModel(QueryModel queryModel) {
        this.queryModel = queryModel;
    }
}