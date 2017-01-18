package com.example.dimichspb.cypruscarrentals;

import android.content.Intent;
import android.view.View;

public class RequestOnClickListener implements View.OnClickListener, RequestResponse {

    Request request;
    View view;

    public RequestOnClickListener(Request request) {
        this.request = request;
    }

    @Override
    public void onClick(View view) {
        this.view = view;
        sendRequest(this.request);
    }

    private void sendRequest(Request request) {
        PushRequest pushRequest = new PushRequest(request);
        pushRequest.requestResponse = this;
        pushRequest.execute();
    }

    @Override
    public void processFinish(Request request) {
        Intent intent = new Intent(view.getContext(), VehicleChooseActivity.class);
        intent.putExtra("request", request);
        view.getContext().startActivity(intent);
    }
}
