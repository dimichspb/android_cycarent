package com.example.dimichspb.cypruscarrentals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Type {
    public String seats;
    public String doors;

    public Type(JSONObject object) {
        try {
            this.seats = object.getString("seats");
            this.doors = object.getString("doors");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Type> fromJson(JSONArray jsonObjects) {
        ArrayList<Type> types = new ArrayList<Type>();
        for (int i =0; i < jsonObjects.length(); i++) {
             try {
                types.add(new Type(jsonObjects.getJSONObject(i)));
             } catch (JSONException e) {
                e.printStackTrace();
             }
        }
        return types;
    }
}
