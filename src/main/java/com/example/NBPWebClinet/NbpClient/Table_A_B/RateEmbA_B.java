package com.example.NBPWebClinet.NbpClient.Table_A_B;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class RateEmbA_B {

    @SerializedName("no")
    @Expose
    private String no;

    @SerializedName("effectiveDate")
    @Expose
    private String effectiveDate;

    @SerializedName("mid")
    @Expose
    private Double mid;

    public String getNo() {
        return no;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public Double getMid() {
        return mid;
    }
}