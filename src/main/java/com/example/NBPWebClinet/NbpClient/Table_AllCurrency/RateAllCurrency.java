package com.example.NBPWebClinet.NbpClient.Table_AllCurrency;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class RateAllCurrency {

    @SerializedName("table")
    @Expose
    private String table;

    @SerializedName("no")
    @Expose
    private String no;

    @SerializedName("effectiveDate")
    @Expose
    private String effectiveDate;

    @SerializedName("rates")
    @Expose
    private RateEmbAllCurrency[] rates = null;

    public String getTable() {
        return table;
    }

    public String getNo() {
        return no;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public RateEmbAllCurrency[] getRates() {
        return rates;
    }
}