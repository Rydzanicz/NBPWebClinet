package com.example.NBPWebClinet.NbpClient.Table_C;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class RateEmbC {

    @SerializedName("no")
    @Expose
    private String no;

    @SerializedName("effectiveDate")
    @Expose
    private String effectiveDate;

    @SerializedName("bid")
    @Expose
    private Double bid;

    @SerializedName("ask")
    @Expose
    private Double ask;

    public String getNo() {
        return no;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public Double getBid() {
        return bid;
    }

    public Double getAsk() {
        return ask;
    }

}