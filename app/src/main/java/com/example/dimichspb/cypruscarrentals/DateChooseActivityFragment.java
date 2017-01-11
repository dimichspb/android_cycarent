package com.example.dimichspb.cypruscarrentals;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A placeholder fragment containing a simple view.
 */
public class DateChooseActivityFragment extends Fragment {


    TextView dateStart;
    TextView timeStart;
    TextView dateEnd;
    TextView timeEnd;
    
    Button dateStartButton;
    Button timeStartButton;
    Button dateEndButton;
    Button timeEndButton;

    Calendar dateAndTimeStart = Calendar.getInstance();
    Calendar dateAndTimeEnd = Calendar.getInstance();

    DatePickerDialog datePickerDialogStart;
    TimePickerDialog timePickerDialogStart;
    DatePickerDialog datePickerDialogEnd;
    TimePickerDialog timePickerDialogEnd;



    public DateChooseActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_choose, container, false);
        dateStart = (TextView) view.findViewById(R.id.viewText_date_start);
        timeStart = (TextView) view.findViewById(R.id.viewText_time_start);
        dateEnd   = (TextView) view.findViewById(R.id.viewText_date_end);
        timeEnd   = (TextView) view.findViewById(R.id.viewText_time_end);
        setInitialDateTime();
        
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


    private void setInitialDateTime() {
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
