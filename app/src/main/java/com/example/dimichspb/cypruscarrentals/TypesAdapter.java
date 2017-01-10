package com.example.dimichspb.cypruscarrentals;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;


public class TypesAdapter extends ArrayAdapter<Type> {
    private static class ViewHolder {
        TextView seats;
        TextView doors;
    }

    public TypesAdapter(Context context, ArrayList<Type> types) {
        super(context, R.layout.item_type, types);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Type type = (Type) getItem(position);
        // Check if an existing view is being retype, otherwise inflate the view
        ViewHolder viewHolder;
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_type, parent, false);
            viewHolder.seats = (TextView) convertView.findViewById(R.id.textview_typeSeats);
            viewHolder.doors = (TextView) convertView.findViewById(R.id.textview_typeDoors);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using hte data object
        viewHolder.seats.setText(type.seats);
        viewHolder.doors.setText(type.doors);
        // Return the completed view to render on screen
        return convertView;
    }

    public void updateTypes(ArrayList<Type> types) {
        this.clear();
        this.addAll(types);
    }
}
