package com.example.marvel.json;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class JsonStoriesModel implements Parcelable {
    private int available;
    private String collectionURI;
    private List<StoriesItems> items;

    protected JsonStoriesModel(Parcel in) {
        available = in.readInt();
        collectionURI = in.readString();
        items = in.createTypedArrayList(StoriesItems.CREATOR);

    }

    public static final Creator<JsonStoriesModel> CREATOR = new Creator<JsonStoriesModel>() {
        @Override
        public JsonStoriesModel createFromParcel(Parcel in) {
            return new JsonStoriesModel(in);
        }

        @Override
        public JsonStoriesModel[] newArray(int size) {
            return new JsonStoriesModel[size];
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

    public List<StoriesItems> getItems() {
        return items;
    }

    public void setItems(List<StoriesItems> items) {
        this.items = items;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(available);
        dest.writeString(collectionURI);
        dest.writeTypedList(this.items);

    }

}
