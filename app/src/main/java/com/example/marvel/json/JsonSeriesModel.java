package com.example.marvel.json;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class JsonSeriesModel implements Parcelable {
    private int available;
    private String collectionURI;
    private List<SeriesItems> items;
    private int returned;

    protected JsonSeriesModel(Parcel in) {
        available = in.readInt();
        collectionURI = in.readString();
        returned = in.readInt();
    }

    public static final Creator<JsonSeriesModel> CREATOR = new Creator<JsonSeriesModel>() {
        @Override
        public JsonSeriesModel createFromParcel(Parcel in) {
            return new JsonSeriesModel(in);
        }

        @Override
        public JsonSeriesModel[] newArray(int size) {
            return new JsonSeriesModel[size];
        }
    };

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public List<SeriesItems> getItems() {
        return items;
    }

    public void setItems(List<SeriesItems> items) {
        this.items = items;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(available);
        dest.writeString(collectionURI);
        dest.writeInt(returned);
    }

    public class SeriesItems{
        private String recourceURI;
        private String name;

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
    }
}
