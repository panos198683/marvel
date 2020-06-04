package com.example.marvel.json;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class JsonMarvelModel implements Parcelable {
    private int code;
    private String status;
    private String copyright;
    private String attributionText;
    private String attributionHTML;
    private String etag;
    private JsonDataModel data;

    protected JsonMarvelModel(Parcel in) {
        code = in.readInt();
        status = in.readString();
        copyright = in.readString();
        attributionText = in.readString();
        attributionHTML = in.readString();
        etag = in.readString();
        data = in.readParcelable(JsonDataModel.class.getClassLoader());
    }

    public static final Creator<JsonMarvelModel> CREATOR = new Creator<JsonMarvelModel>() {
        @Override
        public JsonMarvelModel createFromParcel(Parcel in) {
            return new JsonMarvelModel(in);
        }

        @Override
        public JsonMarvelModel[] newArray(int size) {
            return new JsonMarvelModel[size];
        }
    };

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getAttributionText() {
        return attributionText;
    }

    public void setAttributionText(String attributionText) {
        this.attributionText = attributionText;
    }

    public String getAttributionHTML() {
        return attributionHTML;
    }

    public void setAttributionHTML(String attributionHTML) {
        this.attributionHTML = attributionHTML;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public JsonDataModel getData() {
        return data;
    }

    public void setData(JsonDataModel data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeString(status);
        dest.writeString(copyright);
        dest.writeString(attributionText);
        dest.writeString(attributionHTML);
        dest.writeString(etag);
        dest.writeParcelable(data,1);
    }

}
