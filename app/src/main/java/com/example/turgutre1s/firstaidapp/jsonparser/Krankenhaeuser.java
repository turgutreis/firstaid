package com.example.turgutre1s.firstaidapp.jsonparser;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TurgutRe1s on 29.12.2016.
 */

public class Krankenhaeuser implements Parcelable {


    public Attributes attributes;
    public Geometry geometry;


    public Krankenhaeuser(Parcel source) {
        this.attributes = new Attributes();
        this.attributes.postzustellbezirk = source.readString();
        this.attributes.stadtviertel = source.readString();
        this.attributes.stadteil = source.readString();
        this.attributes.stadtbezirk = source.readString();
        this.attributes.adresse = source.readString();
        this.attributes.adresse_nr = source.readString();
        this.attributes.nutzung = source.readString();
        this.geometry = new Geometry();
        this.geometry.lat = source.readDouble();
        this.geometry.lng = source.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.attributes.postzustellbezirk);
        dest.writeString(this.attributes.stadtviertel);
        dest.writeString(this.attributes.stadteil);
        dest.writeString(this.attributes.stadtbezirk);
        dest.writeString(this.attributes.adresse);
        dest.writeString(this.attributes.adresse_nr);
        dest.writeString(this.attributes.nutzung);
        dest.writeString(this.attributes.name);
        dest.writeDouble(this.geometry.lat);
        dest.writeDouble(this.geometry.lng);
    }

    public class Attributes {
        public String postzustellbezirk;
        public String stadtviertel;
        public String stadteil;
        public String stadtbezirk;
        public String adresse;
        public String adresse_nr;
        public String nutzung;
        public String name;
    }


    public class Geometry {
        public double lat;
        public double lng;

    }

    public String getName() {

        return attributes.name;
    }

    public double getLat() {
        return geometry.lat;
    }

    public double getLng() {
        return geometry.lng;
    }


    public static final Parcelable.Creator<Krankenhaeuser> CREATOR = new Parcelable.Creator<Krankenhaeuser>() {
        @Override
        public Krankenhaeuser createFromParcel(Parcel source) {
            return new Krankenhaeuser(source);
        }

        @Override
        public Krankenhaeuser[] newArray(int size) {
            return new Krankenhaeuser[size];
        }

    };
}
