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

public class PushRequest extends AsyncTask<Void, Void, Request> {
    private final String url = "http://api.carrent.dimichspb.webfactional.com/request/create";
    private Request request;

    public PushRequest(Request request) {
        this.request = request;
    }

    public RequestResponse requestResponse = null;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    @Override
    protected Request doInBackground(Void... params) {
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
            writer.write(getPostDataString(this.request));

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
                this.request.setId(jsonObject.getInt("id"));
            }
            else {
                response="";

            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return this.request;
    }

    protected void onPostExecute(Request request) {
        requestResponse.processFinish(this.request);
    }

    private String getPostDataString(Request request) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();

        result.append(URLEncoder.encode("type_id", "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode(request.getType().id, "UTF-8"));

        result.append("&");

        result.append(URLEncoder.encode("email", "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode(request.getEmail(), "UTF-8"));

        result.append("&");

        result.append(URLEncoder.encode("start_at", "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode(request.getStartAt().toString(), "UTF-8"));

        result.append("&");

        result.append(URLEncoder.encode("duration", "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode(request.getDuration().toString(), "UTF-8"));

        return result.toString();
    }
}
