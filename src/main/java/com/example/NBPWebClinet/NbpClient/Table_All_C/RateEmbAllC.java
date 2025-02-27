package com.example.NBPWebClinet.NbpClient.Table_All_C;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class RateEmbAllC {

    @SerializedName("currency")
    @Expose
    private String currency;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("bid")
    @Expose
    private Double bid;

    @SerializedName("ask")
    @Expose
    private Double ask;

    public Double getBid() {
        return bid;
    }

    public Double getAsk() {
        return ask;
    }

    public String getCode() {
        return code;
    }

    public String getCurrency() {
        return currency;
    }
}