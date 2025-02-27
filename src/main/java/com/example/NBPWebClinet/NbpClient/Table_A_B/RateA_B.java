package com.example.NBPWebClinet.NbpClient.Table_A_B;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;
import java.util.List;

@Generated("jsonschema2pojo")
public class RateA_B {

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
    private List<RateEmbA_B> rates = null;

    public String getTable() {
        return table;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public List<RateEmbA_B> getRates() {
        return rates;
    }
}