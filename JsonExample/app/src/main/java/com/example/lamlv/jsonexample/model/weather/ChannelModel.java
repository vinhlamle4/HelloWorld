package com.example.lamlv.jsonexample.model.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChannelModel {

    @SerializedName("units")
    @Expose
    private UnitsModel unitsModel;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("lastBuildDate")
    @Expose
    private String lastBuildDate;
    @SerializedName("ttl")
    @Expose
    private String ttl;
    @SerializedName("location")
    @Expose
    private LocationModel locationModel;
    @SerializedName("wind")
    @Expose
    private WindModel windModel;
    @SerializedName("atmosphere")
    @Expose
    private AtmosphereModel atmosphereModel;
    @SerializedName("astronomy")
    @Expose
    private AstronomyModel astronomyModel;
    @SerializedName("image")
    @Expose
    private ImageModel imageModel;
    @SerializedName("item")
    @Expose
    private ItemModel itemModel;

    public UnitsModel getUnitsModel() {
        return unitsModel;
    }

    public void setUnitsModel(UnitsModel unitsModel) {
        this.unitsModel = unitsModel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public LocationModel getLocationModel() {
        return locationModel;
    }

    public void setLocationModel(LocationModel locationModel) {
        this.locationModel = locationModel;
    }

    public WindModel getWindModel() {
        return windModel;
    }

    public void setWindModel(WindModel windModel) {
        this.windModel = windModel;
    }

    public AtmosphereModel getAtmosphereModel() {
        return atmosphereModel;
    }

    public void setAtmosphereModel(AtmosphereModel atmosphereModel) {
        this.atmosphereModel = atmosphereModel;
    }

    public AstronomyModel getAstronomyModel() {
        return astronomyModel;
    }

    public void setAstronomyModel(AstronomyModel astronomyModel) {
        this.astronomyModel = astronomyModel;
    }

    public ImageModel getImageModel() {
        return imageModel;
    }

    public void setImageModel(ImageModel image) {
        this.imageModel = image;
    }

    public ItemModel getItemModel() {
        return itemModel;
    }

    public void setItemModel(ItemModel item) {
        this.itemModel = item;
    }

}
