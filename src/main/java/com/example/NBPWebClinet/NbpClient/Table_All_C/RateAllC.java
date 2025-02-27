package com.example.NBPWebClinet.NbpClient.Table_All_C;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class RateAllC {

    @SerializedName("table")
    @Expose
    private String table;

    @SerializedName("no")
    @Expose
    private String no;

    @SerializedName("tradingDate")
    @Expose
    private String tradingDate;

    @SerializedName("effectiveDate")
    @Expose
    private String effectiveDate;

    @SerializedName("rates")
    @Expose
    private RateEmbAllC[] rates = null;

    public String getTable() {
        return table;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public String getNo() {
        return no;
    }

    public String getTradingDate() {
        return tradingDate;
    }

    public RateEmbAllC[] getRates() {
        return rates;
    }

}