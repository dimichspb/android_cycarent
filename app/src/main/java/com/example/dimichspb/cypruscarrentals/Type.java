package com.example.dimichspb.cypruscarrentals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import java.util.ArrayList;

public class Type implements Serializable {
    public String code;
    public String icon;
    public String seats;
    public String doors;

    public Type() {
    }

    public void fromJson(JSONObject object) {
        try {
            this.code = object.getString("code");
            this.icon = "@drawable/" + this.code;
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
                 Type type = new Type();
                 type.fromJson(jsonObjects.getJSONObject(i));
                 types.add(type);
             } catch (JSONException e) {
                 e.printStackTrace();
             }
        }
        return types;
    }
}
