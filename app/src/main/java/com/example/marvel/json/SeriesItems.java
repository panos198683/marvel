package com.example.marvel.json;

import android.os.Parcel;
import android.os.Parcelable;

public class SeriesItems implements Parcelable {
    private String recourceURI;
    private String name;

    protected SeriesItems(Parcel in) {
        recourceURI = in.readString();
        name = in.readString();
    }

    public static final Creator<SeriesItems> CREATOR = new Creator<SeriesItems>() {
        @Override
        public SeriesItems createFromParcel(Parcel in) {
            return new SeriesItems(in);
        }

        @Override
        public SeriesItems[] newArray(int size) {
            return new SeriesItems[size];
        }
    };

    public String getRecourceURI() {
        return recourceURI;
    }

    public void setRecourceURI(String recourceURI) {
        this.recourceURI = recourceURI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(recourceURI);
        dest.writeString(name);
    }
}
