package com.example.apitest;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Coin implements Parcelable {
    @SerializedName("symbol")
    private String symbol;
    @SerializedName("baseAsset")
    private String baseAsset;
    @SerializedName("openPrice")
    private String openPrice;
    @SerializedName("lowPrice")
    private String lowPrice;
    @SerializedName("highPrice")
    private String highPrice;
    @SerializedName("lastPrice")
    private String lastPrice;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getBaseAsset() {
        return baseAsset;
    }

    public void setBaseAsset(String baseAsset) {
        this.baseAsset = baseAsset;
    }

    public String getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(String openPrice) {
        this.openPrice = openPrice;
    }

    public String getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(String lowPrice) {
        this.lowPrice = lowPrice;
    }

    public String getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(String highPrice) {
        this.highPrice = highPrice;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.symbol);
        dest.writeString(this.baseAsset);
        dest.writeString(this.openPrice);
        dest.writeString(this.lowPrice);
        dest.writeString(this.highPrice);
        dest.writeString(this.lastPrice);
    }

    public void readFromParcel(Parcel source) {
        this.symbol = source.readString();
        this.baseAsset = source.readString();
        this.openPrice = source.readString();
        this.lowPrice = source.readString();
        this.highPrice = source.readString();
        this.lastPrice = source.readString();
    }

    public Coin() {
    }

    protected Coin(Parcel in) {
        this.symbol = in.readString();
        this.baseAsset = in.readString();
        this.openPrice = in.readString();
        this.lowPrice = in.readString();
        this.highPrice = in.readString();
        this.lastPrice = in.readString();
    }

    public static final Parcelable.Creator<Coin> CREATOR = new Parcelable.Creator<Coin>() {
        @Override
        public Coin createFromParcel(Parcel source) {
            return new Coin(source);
        }

        @Override
        public Coin[] newArray(int size) {
            return new Coin[size];
        }
    };
}
