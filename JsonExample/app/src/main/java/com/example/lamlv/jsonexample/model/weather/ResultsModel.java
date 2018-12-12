package com.example.lamlv.jsonexample.model.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultsModel {

    @SerializedName("channel")
    @Expose
    private ChannelModel channelModel;

    public ChannelModel getChannelModel() {
        return channelModel;
    }

    public void setChannelModel(ChannelModel channelModel) {
        this.channelModel = channelModel;
    }

}
