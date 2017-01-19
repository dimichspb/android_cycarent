package com.example.dimichspb.cypruscarrentals;


import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class PushBooking extends AsyncTask<Void, Void, Booking> {
    private final String url = "http://api.carrent.dimichspb.webfactional.com/booking/create";
    private Booking booking;

    public PushBooking(Booking booking) {
        this.booking = booking;
    }

    public BookingResponse bookingResponse = null;

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    protected Booking doInBackground(Void... params) {
        String response = "";

        try {
            URL url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);


            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(this.booking));

            writer.flush();
            writer.close();
            os.close();
            int responseCode=connection.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_CREATED) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
                JSONObject jsonObject = new JSONObject(response);
                this.booking.setId(jsonObject.getInt("id"));
            }
            else {
                response="";

            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return this.booking;
    }

    protected void onPostExecute(Booking booking) {
        bookingResponse.processFinish(this.booking);
    }

    private String getPostDataString(Booking booking) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();

        result.append(URLEncoder.encode("request_id", "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode(booking.getRequest().getId().toString(), "UTF-8"));

        result.append("&");

        result.append(URLEncoder.encode("vehicle_id", "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode(booking.getVehicle().id, "UTF-8"));

        return result.toString();
    }
}
