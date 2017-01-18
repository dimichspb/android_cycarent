package com.example.dimichspb.cypruscarrentals;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

/**
 * A placeholder fragment containing a simple view.
 */
public class BookingConfirmationActivityFragment extends Fragment {

    Request request;
    Vehicle vehicle;

    Calendar dateAndTimeStart = Calendar.getInstance();
    Calendar dateAndTimeEnd = Calendar.getInstance();
    TextView dateStart;
    TextView timeStart;
    TextView dateEnd;
    TextView timeEnd;
    TextView email;

    TextView vehicleId;
    TextView vehicleNumber;
    TextView vehicleColor;
    TextView vehicleModelName;
    TextView vehicleModelVendorName;
    TextView vehiclePriceAmount;
    TextView vehiclePriceCurrencyCode;
    TextView vehicleProviderName;


    FloatingActionButton confirmBookingButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Intent intent = getActivity().getIntent();
        this.request = (Request) intent.getSerializableExtra("request");
        this.vehicle = (Vehicle) intent.getSerializableExtra("vehicle");

        Type type = this.request.getType();
        dateAndTimeStart.setTimeInMillis(request.getStartAt() * 1000);
        dateAndTimeEnd.setTimeInMillis((request.getStartAt() + request.getDuration()) * 1000);

        View view = inflater.inflate(R.layout.fragment_date_choose, container, false);

        TextView id = (TextView) view.findViewById(R.id.textview_typeId);
        TextView code = (TextView) view.findViewById(R.id.textview_typeCode);
        ImageView icon = (ImageView) view.findViewById(R.id.imageview_typeIcon);
        TextView seats = (TextView) view.findViewById(R.id.textview_typeSeats);
        TextView doors = (TextView) view.findViewById(R.id.textview_typeDoors);

        id.setText(type.id);
        code.setText(type.code);
        Resources res = getContext().getResources();
        String mDrawableName = type.code.toLowerCase();
        int resID = res.getIdentifier(mDrawableName , "drawable", getContext().getPackageName());
        icon.setImageResource(resID);
        seats.setText(type.seats);
        doors.setText(type.doors);

        dateStart = (TextView) view.findViewById(R.id.viewText_date_start);
        timeStart = (TextView) view.findViewById(R.id.viewText_time_start);
        dateEnd   = (TextView) view.findViewById(R.id.viewText_date_end);
        timeEnd   = (TextView) view.findViewById(R.id.viewText_time_end);
        setTime();

        email = (TextView) view.findViewById(R.id.textView_account_email);
        email.setText(request.getEmail());

        vehicleId = (TextView) view.findViewById(R.id.textview_vehicleId);
        vehicleNumber = (TextView) view.findViewById(R.id.textview_vehicleNumber);
        vehicleColor = (TextView) view.findViewById(R.id.textview_vehicleColor);
        vehicleModelName = (TextView) view.findViewById(R.id.textview_vehicleModelName);
        vehicleModelVendorName = (TextView) view.findViewById(R.id.textview_vehicleModelVendorName);
        vehiclePriceAmount = (TextView) view.findViewById(R.id.textview_vehiclePriceAmount);
        vehiclePriceCurrencyCode = (TextView) view.findViewById(R.id.textview_vehiclePriceCurrencyCode);
        vehicleProviderName = (TextView) view.findViewById(R.id.textview_vehicleProviderName);

        vehicleId.setText(vehicle.id);
        vehicleNumber.setText(vehicle.number);
        vehicleColor.setText(vehicle.color);
        vehicleModelName.setText(vehicle.model_name);
        vehicleModelVendorName.setText(vehicle.model_vendor_name);
        vehiclePriceAmount.setText(vehicle.price_amount);
        vehiclePriceCurrencyCode.setText(vehicle.price_currency_code);
        vehicleProviderName.setText(vehicle.provider_name);
                
        this.confirmBookingButton = (FloatingActionButton) view.findViewById(R.id.fab_confirm_booking);

        return view;
    }

    private void setTime() {
        dateStart.setText(DateUtils.formatDateTime(getContext(),
                dateAndTimeStart.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE));
        timeStart.setText(DateUtils.formatDateTime(getContext(),
                dateAndTimeStart.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_TIME));

        dateEnd.setText(DateUtils.formatDateTime(getContext(),
                dateAndTimeEnd.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE));

        timeEnd.setText(DateUtils.formatDateTime(getContext(),
                dateAndTimeEnd.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_TIME));

    }
}
