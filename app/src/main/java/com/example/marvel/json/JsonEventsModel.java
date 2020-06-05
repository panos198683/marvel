package com.example.marvel.json;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class JsonEventsModel implements Parcelable {
    private int available;
    private String collectionURI;
    private List<EventsItems> items;
    private int returned;

    protected JsonEventsModel(Parcel in) {
        available = in.readInt();
        collectionURI = in.readString();
        returned = in.readInt();
        items = in.createTypedArrayList(EventsItems.CREATOR);

    }

    public static final Creator<JsonEventsModel> CREATOR = new Creator<JsonEventsModel>() {
        @Override
        public JsonEventsModel createFromParcel(Parcel in) {
            return new JsonEventsModel(in);
        }

        @Override
        public JsonEventsModel[] newArray(int size) {
            return new JsonEventsModel[size];
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

    public List<EventsItems> getItems() {
        return items;
    }

    public void setItems(List<EventsItems> items) {
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
        dest.writeTypedList(items);

    }


}
