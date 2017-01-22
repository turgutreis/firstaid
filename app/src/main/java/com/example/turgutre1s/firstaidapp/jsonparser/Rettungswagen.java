package com.example.turgutre1s.firstaidapp.jsonparser;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TurgutRe1s on 13.01.2017.
 */

public class Rettungswagen implements Parcelable {

    public String _id;
    public String email;
    public String passwort;


    public Rettungswagen(Parcel source) {

        this._id = source.readString();
        this.email = source.readString();
        this.passwort = source.readString();


    }

    public String get_id() {
        return _id;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswort() {
        return passwort;
    }

    public static final Creator<Rettungswagen> CREATOR = new Creator<Rettungswagen>() {
        @Override
        public Rettungswagen createFromParcel(Parcel source) {
            return new Rettungswagen(source);
        }

        @Override
        public Rettungswagen[] newArray(int size) {
            return new Rettungswagen[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {

        dest.writeString(this._id);
        dest.writeString(this.email);
        dest.writeString(this.passwort);

    }
}
