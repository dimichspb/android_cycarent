package com.example.dimichspb.cypruscarrentals;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GetVehicles extends AsyncTask<Void, Void, ArrayList<Vehicle>>{
    private final String baseUrl = "http://api.carrent.dimichspb.webfactional.com/requests";
    private String url = "";

    private ArrayList<Vehicle> vehicleList = new ArrayList<>();

    private final VehiclesAdapter adapter;
    private Request request;

    public GetVehicles(VehiclesAdapter adapter, Request request) {
        this.adapter = adapter;
        this.request = request;
        this.generateUrl();
    }

    private void generateUrl() {
        this.url = this.baseUrl + "/" + this.request.getId() + "?expand=vehicles";
    }

    @Override
    protected ArrayList<Vehicle> doInBackground(Void... params) {

        try {
            URL url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream inputStream = connection.getInputStream();

            byte[] b = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            while ( inputStream.read(b) != -1)
                byteArrayOutputStream.write(b);
            String JSONResponse = new String(byteArrayOutputStream.toByteArray());

            JSONObject jsonObject = new JSONObject(JSONResponse);
            JSONArray jsonArray = jsonObject.getJSONArray("vehicles");
            for (int i=0; i < jsonArray.length(); i++) {
                Vehicle vehicle = new Vehicle();
                vehicle.fromJson(jsonArray.getJSONObject(i));
                vehicleList.add(vehicle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicleList;
    }

    protected void onPostExecute(ArrayList<Vehicle> vehicleList) {
        this.adapter.updateVehicles(vehicleList);
    }
}
