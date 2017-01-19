package com.example.dimichspb.cypruscarrentals;

import android.content.Intent;
import android.view.View;

public class BookingOnClickListener implements View.OnClickListener, BookingResponse {

    Booking booking;
    View view;

    public BookingOnClickListener(Booking booking) {
        this.booking = booking;
    }

    @Override
    public void onClick(View view) {
        this.view = view;
        sendBooking(this.booking);
    }

    private void sendBooking(Booking booking) {
        PushBooking pushBooking = new PushBooking(booking);
        pushBooking.bookingResponse = this;
        pushBooking.execute();
    }

    @Override
    public void processFinish(Booking booking) {
        Intent intent = new Intent(view.getContext(), VoucherActivity.class);
        view.getContext().startActivity(intent);
    }
}
