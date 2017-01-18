package com.example.dimichspb.cypruscarrentals;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class VehiclesAdapter extends ArrayAdapter<Vehicle> {
    private static class ViewHolder {
        TextView id;
        TextView number;
        TextView color;
        TextView model_name;
        TextView model_vendor_name;
        TextView price_amount;
        TextView price_currency_code;
        TextView provider_name;
    }

    private ArrayList<Vehicle> vehicles;

    public VehiclesAdapter(Context context, ArrayList<Vehicle> vehicles) {
        super(context, R.layout.item_vehicle, vehicles);
        //this.vehicles = vehicles;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Vehicle vehicle = (Vehicle) getItem(position);
        // Check if an existing view is being revehicle, otherwise inflate the view
        ViewHolder viewHolder;
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_vehicle, parent, false);
            viewHolder.id = (TextView) convertView.findViewById(R.id.textview_vehicleId);
            viewHolder.number = (TextView) convertView.findViewById(R.id.textview_vehicleNumber);
            viewHolder.color = (TextView) convertView.findViewById(R.id.textview_vehicleColor);
            viewHolder.model_name = (TextView) convertView.findViewById(R.id.textview_vehicleModelName);
            viewHolder.model_vendor_name = (TextView) convertView.findViewById(R.id.textview_vehicleModelVendorName);
            viewHolder.price_amount = (TextView) convertView.findViewById(R.id.textview_vehiclePriceAmount);
            viewHolder.price_currency_code = (TextView) convertView.findViewById(R.id.textview_vehiclePriceCurrencyCode);
            viewHolder.provider_name = (TextView) convertView.findViewById(R.id.textview_vehicleProviderName);

            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using hte data object
        viewHolder.id.setText(vehicle.id);
        viewHolder.number.setText(vehicle.number);
        viewHolder.color.setText(vehicle.color);
        viewHolder.model_name.setText(vehicle.model_name);
        viewHolder.model_vendor_name.setText(vehicle.model_vendor_name);
        viewHolder.price_amount.setText(vehicle.price_amount);
        viewHolder.price_currency_code.setText(vehicle.price_currency_code);
        viewHolder.provider_name.setText(vehicle.provider_name);

        // Return the completed view to render on screen
        return convertView;
    }

    public void updateVehicles(ArrayList<Vehicle> vehicles) {
        this.clear();
        this.addAll(vehicles);
    }
}
