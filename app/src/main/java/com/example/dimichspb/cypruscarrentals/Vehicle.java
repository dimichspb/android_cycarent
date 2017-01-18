package com.example.dimichspb.cypruscarrentals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import java.util.ArrayList;

public class Vehicle implements Serializable {
    public String id;
    public String number;
    public String color;
    public String condition;
    public String model_name;
    public String model_vendor_name;
    public String provider_name;
    public String price_amount;
    public String price_currency_code;

    public Vehicle() {
    }

    public void fromJson(JSONObject object) {
        try {
            this.id = object.getString("id");
            this.number = object.getString("number");
            this.color = object.getString("color");
            this.condition = object.getString("condition");
            setModel(object);
            setProvider(object);
            setPrice(object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setModel(JSONObject object) {
        try {
            JSONObject modelObject = object.getJSONObject("model");
            this.model_name = modelObject.getString("name");
            JSONObject vendorObject = modelObject.getJSONObject("vendor");
            this.model_vendor_name = vendorObject.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setProvider(JSONObject object) {
        try {
            JSONObject providerObject = object.getJSONObject("provider");
            this.provider_name = providerObject.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setPrice(JSONObject object) {
        try {
            JSONObject priceObject = object.getJSONObject("price");
            this.price_amount = priceObject.getString("amount");
            JSONObject currencyObject = priceObject.getJSONObject("currency");
            this.price_currency_code = currencyObject.getString("code");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
