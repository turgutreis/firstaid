package com.example.turgutre1s.firstaidapp.jsonparser;

import android.os.Parcel;
import android.os.Parcelable;

import com.mongodb.client.model.geojson.Geometry;

/**
 * Created by TurgutRe1s(Ali Mazlum) on 07.11.2016.
 */
public class Notfall implements Parcelable {
    // Parcelable dafür da das man Objekte der aktivity mitgeben kann.

    // Vom Json die Informationen in eine Klasse gepackt.
    public String _id;
    public String date;
    public String name;
    public String vorname;
    public int telefon;
    public Geometry geometry;

    public class Geometry {
        public double lat;
        public double lng;
    }

    public Notfall(Parcel source) {
        this._id = source.readString();
        this.date = source.readString();
        this.name = source.readString();
        this.vorname = source.readString();
        this.telefon = source.readInt();
        this.geometry = new Geometry();
        this.geometry.lat = source.readDouble();
        this.geometry.lng = source.readDouble();
    }

    public double getLat() {
        return geometry.lat;
    }

    public double getLng() {
        return geometry.lng;
    }

    public String getName() {
        return name;
    }

    public String getVorname() {
        return vorname;
    }

    public String getDate() {
        return date;
    }

    public int getTelefon() {
        return telefon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.date);
        dest.writeString(this.name);
        dest.writeString(this.vorname);
        dest.writeInt(this.telefon);
        // Geometry wird noch Eingebaut
        dest.writeDouble(this.geometry.lat);
        dest.writeDouble(this.geometry.lng);
    }

    public static final Parcelable.Creator<Notfall> CREATOR = new Parcelable.Creator<Notfall>() {
        @Override
        public Notfall createFromParcel(Parcel source) {
            return new Notfall(source);
        }

        @Override
        public Notfall[] newArray(int size) {
            return new Notfall[size];
        }

    };
}
