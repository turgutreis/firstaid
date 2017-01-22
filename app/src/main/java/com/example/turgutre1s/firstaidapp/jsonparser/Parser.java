package com.example.turgutre1s.firstaidapp.jsonparser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by TurgutRe1s on 21.11.2016.
 */

public class Parser {

    public Gson gson;

    public Parser() {
        gson = new Gson();
    }

    public List<Notfall> getNotfallList(String json) {
        return gson.fromJson(json,
                new TypeToken<List<Notfall>>() {
                }.getType());
    }

    public List<Krankenhaeuser> getKrankenhausList(String json){

        return gson.fromJson(json,
                new TypeToken<List<Krankenhaeuser>>(){

                }.getType());

    }

    public List<Rettungswagen> getRettungswagenList(String json){

        return gson.fromJson(json,
                new TypeToken<List<Rettungswagen>>(){

                }.getType());

    }


}
