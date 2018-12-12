package com.example.lamlv.jsonexample.model.weather;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemModel {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("long")
    @Expose
    private String _long;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("pubDate")
    @Expose
    private String pubDate;
    @SerializedName("condition")
    @Expose
    private ConditionModel conditionModel;
    @SerializedName("forecast")
    @Expose
    private List<ForecastModel> forecastModel = null;
    @SerializedName("description")
    @Expose
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLong() {
        return _long;
    }

    public void setLong(String _long) {
        this._long = _long;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public ConditionModel getConditionModel() {
        return conditionModel;
    }

    public void setConditionModel(ConditionModel conditionModel) {
        this.conditionModel = conditionModel;
    }

    public List<ForecastModel> getForecastModel() {
        return forecastModel;
    }

    public void setForecastModel(List<ForecastModel> forecastModel) {
        this.forecastModel = forecastModel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
