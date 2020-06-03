package com.example.marvel.json;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class JsonDataModel implements Parcelable {
    private int offset;
    private int limit;
    private int total;
    private int count;
    private List<MarvelResultsModel> results;

    protected JsonDataModel(Parcel in) {
        offset = in.readInt();
        limit = in.readInt();
        total = in.readInt();
        count = in.readInt();
        results = in.createTypedArrayList(MarvelResultsModel.CREATOR);
    }

    public static final Creator<JsonDataModel> CREATOR = new Creator<JsonDataModel>() {
        @Override
        public JsonDataModel createFromParcel(Parcel in) {
            return new JsonDataModel(in);
        }

        @Override
        public JsonDataModel[] newArray(int size) {
            return new JsonDataModel[size];
        }
    };

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<MarvelResultsModel> getResults() {
        return results;
    }

    public void setResults(List<MarvelResultsModel> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(offset);
        dest.writeInt(limit);
        dest.writeInt(total);
        dest.writeInt(count);
        dest.writeTypedList(results);
    }
}
