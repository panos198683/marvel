package com.example.marvel.json;

import android.os.Parcel;
import android.os.Parcelable;

public class EventsItems implements Parcelable {
    private String resourceURI;
    private String name;

    protected EventsItems(Parcel in) {
        resourceURI = in.readString();
        name = in.readString();
    }

    public static final Creator<EventsItems> CREATOR = new Creator<EventsItems>() {
        @Override
        public EventsItems createFromParcel(Parcel in) {
            return new EventsItems(in);
        }

        @Override
        public EventsItems[] newArray(int size) {
            return new EventsItems[size];
        }
    };

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
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
        dest.writeString(resourceURI);
        dest.writeString(name);
    }
}

