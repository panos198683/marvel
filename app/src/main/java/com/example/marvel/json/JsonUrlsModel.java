package com.example.marvel.json;

import android.os.Parcel;
import android.os.Parcelable;

public class JsonUrlsModel implements Parcelable {
    private String type;
    private String url;

    protected JsonUrlsModel(Parcel in) {
        type = in.readString();
        url = in.readString();
    }

    public static final Creator<JsonUrlsModel> CREATOR = new Creator<JsonUrlsModel>() {
        @Override
        public JsonUrlsModel createFromParcel(Parcel in) {
            return new JsonUrlsModel(in);
        }

        @Override
        public JsonUrlsModel[] newArray(int size) {
            return new JsonUrlsModel[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(url);
    }
}

