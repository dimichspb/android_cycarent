package com.example.dimichspb.cypruscarrentals;

import android.accounts.AccountManager;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.AccountPicker;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

import static android.app.Activity.RESULT_OK;

/**
 * A placeholder fragment containing a simple view.
 */
public class DateChooseActivityFragment extends Fragment {

    TextView dateStart;
    TextView timeStart;
    TextView dateEnd;
    TextView timeEnd;

    TextView email;
    
    Button dateStartButton;
    Button timeStartButton;
    Button dateEndButton;
    Button timeEndButton;

    Button emailButton;

    Calendar dateAndTimeStart = Calendar.getInstance();
    Calendar dateAndTimeEnd = Calendar.getInstance();

    DatePickerDialog datePickerDialogStart;
    TimePickerDialog timePickerDialogStart;
    DatePickerDialog datePickerDialogEnd;
    TimePickerDialog timePickerDialogEnd;

    Request request;

    FloatingActionButton sendRequestButton;

    private static final int REQUEST_CODE_EMAIL = 1;

    public DateChooseActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dateAndTimeStart.setTimeInMillis(dateAndTimeStart.getTimeInMillis() + 604800000);
        dateAndTimeEnd.setTimeInMillis(dateAndTimeStart.getTimeInMillis() + 604800000);

        Intent intent = getActivity().getIntent();
        this.request = (Request) intent.getSerializableExtra("request");

        Type type = this.request.getType();

        View view = inflater.inflate(R.layout.fragment_date_choose, container, false);

        this.sendRequestButton = (FloatingActionButton) view.findViewById(R.id.fab_send_request);

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
        setInitialDateTime();

        email = (TextView) view.findViewById(R.id.textView_account_email);
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() != 0) {
                    sendRequestButton.setVisibility(FloatingActionButton.VISIBLE);
                }
            }
        });

        dateStartButton = (Button) view.findViewById(R.id.button_date_start);
        dateStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDateStart(view);
            }
        });

        timeStartButton = (Button) view.findViewById(R.id.button_time_start);
        timeStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTimeStart(view);
            }
        });

        dateEndButton = (Button) view.findViewById(R.id.button_date_end);
        dateEndButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDateEnd(view);
            }
        });

        timeEndButton = (Button) view.findViewById(R.id.button_time_end);
        timeEndButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTimeEnd(view);
            }
        });

        emailButton = (Button) view.findViewById(R.id.button_account_email);
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAccountEmail(view);
            }
        });

        sendRequestButton.setOnClickListener(new RequestOnClickListener(this.request));

        return view;
    }


    public void setDateStart(View view) {
        new DatePickerDialog(getContext(), ds,
                dateAndTimeStart.get(Calendar.YEAR),
                dateAndTimeStart.get(Calendar.MONTH),
                dateAndTimeStart.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    public void setTimeStart(View view) {
        new TimePickerDialog(getContext(), ts,
                dateAndTimeStart.get(Calendar.HOUR_OF_DAY),
                dateAndTimeStart.get(Calendar.MINUTE), true)
                .show();
    }

    public void setDateEnd(View view) {
        new DatePickerDialog(getContext(), de,
                dateAndTimeEnd.get(Calendar.YEAR),
                dateAndTimeEnd.get(Calendar.MONTH),
                dateAndTimeEnd.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    public void setTimeEnd(View view) {
        new TimePickerDialog(getContext(), te,
                dateAndTimeEnd.get(Calendar.HOUR_OF_DAY),
                dateAndTimeEnd.get(Calendar.MINUTE), true)
                .show();
    }

    public void setAccountEmail(View view) {
        try {
            Intent intent = AccountPicker.newChooseAccountIntent(null, null,
                    new String[] {GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE}, false, null, null, null, null);
            startActivityForResult(intent, REQUEST_CODE_EMAIL);
        } catch (ActivityNotFoundException e) {
            //// TODO: 12.01.2017 exception handler
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_CODE_EMAIL && resultCode == RESULT_OK) {
            String accountName = intent.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
            email.setText(accountName);
            request.setEmail(accountName);
        }
    }

    private void setInitialDateTime() {
        dateStart.setText(DateUtils.formatDateTime(getContext(),
                dateAndTimeStart.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE));
        request.setDateStart(dateStart.getText().toString());

        timeStart.setText(DateUtils.formatDateTime(getContext(),
                dateAndTimeStart.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_TIME));
        request.setTimeStart(timeStart.getText().toString());

        Long startAt = dateAndTimeStart.getTimeInMillis() / 1000;

        request.setStartAt(startAt);

        dateEnd.setText(DateUtils.formatDateTime(getContext(),
                dateAndTimeEnd.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE));
        request.setDateEnd(dateEnd.getText().toString());

        timeEnd.setText(DateUtils.formatDateTime(getContext(),
                dateAndTimeEnd.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_TIME));
        request.setTimeEnd(timeEnd.getText().toString());

        Long endAt = dateAndTimeEnd.getTimeInMillis() / 1000;

        request.setDuration(endAt - startAt);
    }
    
    DatePickerDialog.OnDateSetListener ds=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTimeStart.set(Calendar.YEAR, year);
            dateAndTimeStart.set(Calendar.MONTH, monthOfYear);
            dateAndTimeStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };

    TimePickerDialog.OnTimeSetListener ts=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTimeStart.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTimeStart.set(Calendar.MINUTE, minute);
            setInitialDateTime();
        }
    };

    DatePickerDialog.OnDateSetListener de=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTimeEnd.set(Calendar.YEAR, year);
            dateAndTimeEnd.set(Calendar.MONTH, monthOfYear);
            dateAndTimeEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };

    TimePickerDialog.OnTimeSetListener te=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTimeEnd.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTimeEnd.set(Calendar.MINUTE, minute);
            setInitialDateTime();
        }
    };
}
