package com.appdev.slots2.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class NewsModel implements Serializable, Parcelable {

    public long id;
    public String title;
    public String source;
    public String link;
    public String guid;
    public String enclosure;
    public String description;
    public String pubDate;

    public NewsModel(long id, String title, String source, String link, String guid, String enclosure, String description, String pubDate) {
        this.id = id;
        this.title = title;
        this.source = source;
        this.link = link;
        this.guid = guid;
        this.enclosure = enclosure;
        this.description = description;
        this.pubDate = pubDate;
    }

    protected NewsModel(Parcel in) {
        id = in.readLong();
        title = in.readString();
        source = in.readString();
        link = in.readString();
        guid = in.readString();
        enclosure = in.readString();
        description = in.readString();
        pubDate = in.readString();
    }

    public static final Creator<NewsModel> CREATOR = new Creator<NewsModel>() {
        @Override
        public NewsModel createFromParcel(Parcel in) {
            return new NewsModel(in);
        }

        @Override
        public NewsModel[] newArray(int size) {
            return new NewsModel[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(title);
        parcel.writeString(source);
        parcel.writeString(link);
        parcel.writeString(guid);
        parcel.writeString(enclosure);
        parcel.writeString(description);
        parcel.writeString(pubDate);
    }
}
