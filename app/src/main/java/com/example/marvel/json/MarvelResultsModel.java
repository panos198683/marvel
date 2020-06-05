package com.example.marvel.json;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MarvelResultsModel implements Parcelable {
    private int id;
    private String name;
    private String description;
    private Date modified;
    private JsonthumbnailModel thumbnail;
    private String recourceURI;
    private JsonComicModel comics;
    private JsonSeriesModel series;
    private JsonStoriesModel stories;
    private JsonEventsModel events;
    private List<JsonUrlsModel> urls;

    protected MarvelResultsModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        recourceURI = in.readString();
        thumbnail = in.readParcelable( JsonthumbnailModel.class.getClassLoader());
        comics = in.readParcelable( JsonComicModel.class.getClassLoader());
        series = in.readParcelable( JsonSeriesModel.class.getClassLoader());
        stories = in.readParcelable( JsonStoriesModel.class.getClassLoader());
        events = in.readParcelable( JsonEventsModel.class.getClassLoader());
        urls = in.createTypedArrayList(JsonUrlsModel.CREATOR);

    }

    public static final Creator<MarvelResultsModel> CREATOR = new Creator<MarvelResultsModel>() {
        @Override
        public MarvelResultsModel createFromParcel(Parcel in) {
            return new MarvelResultsModel(in);
        }

        @Override
        public MarvelResultsModel[] newArray(int size) {
            return new MarvelResultsModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public JsonthumbnailModel getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(JsonthumbnailModel thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getRecourceURI() {
        return recourceURI;
    }

    public void setRecourceURI(String recourceURI) {
        this.recourceURI = recourceURI;
    }

    public JsonComicModel getComics() {
        return comics;
    }

    public void setComics(JsonComicModel comics) {
        this.comics = comics;
    }

    public JsonSeriesModel getSeries() {
        return series;
    }

    public void setSeries(JsonSeriesModel series) {
        this.series = series;
    }

    public JsonStoriesModel getStories() {
        return stories;
    }

    public void setStories(JsonStoriesModel stories) {
        this.stories = stories;
    }

    public JsonEventsModel getEvents() {
        return events;
    }

    public void setEvents(JsonEventsModel events) {
        this.events = events;
    }

    public List<JsonUrlsModel> getUrls() {
        return urls;
    }

    public void setUrls(List<JsonUrlsModel> urls) {
        this.urls = urls;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(recourceURI);
        dest.writeParcelable(thumbnail,3);
        dest.writeParcelable(comics,3);
        dest.writeParcelable(series,3);
        dest.writeParcelable(stories,3);
        dest.writeParcelable(events,3);
        dest.writeTypedList(this.urls);


    }





}
