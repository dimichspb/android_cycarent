package com.example.dimichspb.cypruscarrentals;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class VehicleChooseActivityFragment extends Fragment {

    Request request;

    public VehicleChooseActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vehicle_choose, container, false);

        Intent intent = getActivity().getIntent();
        this.request = (Request) intent.getSerializableExtra("request");

        ListView vehiclesListView = populateVehiclesList(view, this.request);

        vehiclesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Booking booking = new Booking();

                TextView vehicleId = (TextView) view.findViewById(R.id.textview_vehicleId);
                TextView vehicleNumber = (TextView) view.findViewById(R.id.textview_vehicleNumber);
                TextView vehicleColor = (TextView) view.findViewById(R.id.textview_vehicleColor);
                TextView vehicleModelName = (TextView) view.findViewById(R.id.textview_vehicleModelName);
                TextView vehicleModelVendorName = (TextView) view.findViewById(R.id.textview_vehicleModelVendorName);
                TextView vehiclePriceAmount = (TextView) view.findViewById(R.id.textview_vehiclePriceAmount);
                TextView vehiclePriceCurrencyCode = (TextView) view.findViewById(R.id.textview_vehiclePriceCurrencyCode);
                TextView vehicleProviderName = (TextView) view.findViewById(R.id.textview_vehicleProviderName);

                Vehicle vehicle = new Vehicle();
                vehicle.id = vehicleId.getText().toString();
                vehicle.number = vehicleNumber.getText().toString();
                vehicle.color = vehicleColor.getText().toString();
                vehicle.model_name = vehicleModelName.getText().toString();
                vehicle.model_vendor_name = vehicleModelVendorName.getText().toString();
                vehicle.price_amount = vehiclePriceAmount.getText().toString();
                vehicle.price_currency_code = vehiclePriceCurrencyCode.getText().toString();
                vehicle.provider_name = vehicleProviderName.getText().toString();

                booking.setVehicle(vehicle);
                Intent intent = new Intent(getContext(), BookingConfirmationActivity.class);
                intent.putExtra("booking", booking);
                startActivity(intent);
            }
        });
        return view;
    }

    private ListView populateVehiclesList(View view, Request request) {
        // Construct the data source
        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

        // Create the adapter to convert the array to views
        VehiclesAdapter adapter = new VehiclesAdapter(this.getActivity(), vehicles);
        GetVehicles getVehicles = new GetVehicles(adapter, request);
        // Attach the adapter to a ListView
        ListView listView = (ListView) view.findViewById(R.id.listview_vehicles);
        listView.setAdapter(adapter);
        // Fetch the data from API
        getVehicles.execute();

        return listView;
    }
}
