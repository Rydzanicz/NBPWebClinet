package com.example.NBPWebClinet.NbpClient.Table_C;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;
import java.util.List;

@Generated("jsonschema2pojo")
public class RateC {

    @SerializedName("table")
    @Expose
    private String table;

    @SerializedName("currency")
    @Expose
    private String currency;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("rates")
    @Expose
    private List<RateEmbC> rates = null;

    public String getTable() {
        return table;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCode() {
        return code;
    }

    public List<RateEmbC> getRates() {
        return rates;
    }
}