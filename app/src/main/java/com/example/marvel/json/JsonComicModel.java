package com.example.marvel.json;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class JsonComicModel implements Parcelable {
    private int available;
    private String recourceURI;
    private List<ComicItems> items;
    private int returned;

    protected JsonComicModel(Parcel in) {
        available = in.readInt();
        recourceURI = in.readString();
        returned = in.readInt();
        items = in.createTypedArrayList(ComicItems.CREATOR);

    }

    public static final Creator<JsonComicModel> CREATOR = new Creator<JsonComicModel>() {
        @Override
        public JsonComicModel createFromParcel(Parcel in) {
            return new JsonComicModel(in);
        }

        @Override
        public JsonComicModel[] newArray(int size) {
            return new JsonComicModel[size];
        }
    };

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getRecourceURI() {
        return recourceURI;
    }

    public void setRecourceURI(String recourceURI) {
        this.recourceURI = recourceURI;
    }

    public List<ComicItems> getItems() {
        return items;
    }

    public void setItems(List<ComicItems> items) {
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
        dest.writeString(recourceURI);
        dest.writeInt(returned);
        dest.writeTypedList(this.items);

    }

}
