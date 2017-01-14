package com.example.dimichspb.cypruscarrentals;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class LoadFeedData extends AsyncTask<Void, Void, ArrayList<Type>>{
    private final String url = "http://api.carrent.dimichspb.webfactional.com/type";

    private final TypesAdapter adapter;

    public LoadFeedData(TypesAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected ArrayList<Type> doInBackground(Void... params) {
        ArrayList<Type> types = new ArrayList<Type>();

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

            JSONArray jsonArray = new JSONArray(JSONResponse);
            for (int i=0; i < jsonArray.length(); i++) {
                Type type = new Type();
                type.fromJson(jsonArray.getJSONObject(i));
                types.add(type);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return types;
    }

    protected void onPostExecute(ArrayList<Type> types) {
        this.adapter.updateTypes(types);
    }
}
