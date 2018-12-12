package com.example.lamlv.jsonexample.model.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QueryModel {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("results")
    @Expose
    private ResultsModel resultsModel;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public ResultsModel getResultsModel() {
        return resultsModel;
    }

    public void setResultsModel(ResultsModel resultsModel) {
        this.resultsModel = resultsModel;
    }

}
