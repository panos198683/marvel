package com.example.marvel.json;

import android.os.Parcel;
import android.os.Parcelable;

public class ComicItems implements Parcelable {
    private String recourceURI;
    private String name;

    protected ComicItems(Parcel in) {
        recourceURI = in.readString();
        name = in.readString();
    }

    public static final Creator<ComicItems> CREATOR = new Creator<ComicItems>() {
        @Override
        public ComicItems createFromParcel(Parcel in) {
            return new ComicItems(in);
        }

        @Override
        public ComicItems[] newArray(int size) {
            return new ComicItems[size];
        }
    };

    public String getRecourceURI(){
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
