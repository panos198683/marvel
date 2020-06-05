package com.example.marvel.json;

import android.os.Build;
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
        items = in.createTypedArrayList(SeriesItems.CREATOR);

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
        dest.writeTypedList(this.items);

    }

}
