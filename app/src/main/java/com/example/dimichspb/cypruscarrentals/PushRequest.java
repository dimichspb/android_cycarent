package com.example.dimichspb.cypruscarrentals;


import android.os.AsyncTask;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class PushRequest extends AsyncTask<Void, Void, ArrayList<Type>>{
    private final String url = "http://api.carrent.dimichspb.webfactional.com/request";
    private Request request;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    @Override
    protected ArrayList<Type> doInBackground(Void... params) {

        try {
            String response = "";
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

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
            }
            else {
                response="";

            }
        } catch (Throwable t) {
            t.printStackTrace();
        }

        return null;
    }

    protected void onPostExecute(ArrayList<Type> types) {
    }

    private String getPostDataString(Request request) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();

        result.append(URLEncoder.encode("code", "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode(request.getType().code, "UTF-8"));

        result.append("&");

        result.append(URLEncoder.encode("email", "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode(request.getEmail(), "UTF-8"));

        result.append("&");

        result.append(URLEncoder.encode("date_start", "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode(request.getDateStart(), "UTF-8"));

        result.append("&");

        result.append(URLEncoder.encode("time_start", "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode(request.getTimeStart(), "UTF-8"));

        result.append("&");

        result.append(URLEncoder.encode("date_end", "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode(request.getDateEnd(), "UTF-8"));

        result.append("&");

        result.append(URLEncoder.encode("time_end", "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode(request.getTimeEnd(), "UTF-8"));

        return result.toString();
    }
}
