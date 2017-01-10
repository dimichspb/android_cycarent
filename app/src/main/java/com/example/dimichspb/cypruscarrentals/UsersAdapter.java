package com.example.dimichspb.cypruscarrentals;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class UsersAdapter extends ArrayAdapter {
    public UsersAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User user = (User) getItem(position);
        // Check if an exisiting view is being reuser, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }
        // Lookup view for data population
        TextView textview_userName = (TextView) convertView.findViewById(R.id.textview_userName);
        TextView textview_userCity = (TextView) convertView.findViewById(R.id.textview_userCity);
        // Populate the data into the template view using hte data object
        textview_userName.setText(user.name);
        textview_userCity.setText(user.city);
        // Return the completed view to render on screen
        return convertView;
    }
}
