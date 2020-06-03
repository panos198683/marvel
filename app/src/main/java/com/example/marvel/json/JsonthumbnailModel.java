package com.example.marvel.json;

import android.os.Parcel;
import android.os.Parcelable;

public class JsonthumbnailModel implements Parcelable {
    private String path;
    private String extension;

    protected JsonthumbnailModel(Parcel in) {
        path = in.readString();
        extension = in.readString();
    }

    public static final Creator<JsonthumbnailModel> CREATOR = new Creator<JsonthumbnailModel>() {
        @Override
        public JsonthumbnailModel createFromParcel(Parcel in) {
            return new JsonthumbnailModel(in);
        }

        @Override
        public JsonthumbnailModel[] newArray(int size) {
            return new JsonthumbnailModel[size];
        }
    };

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(path);
        dest.writeString(extension);
    }
}
