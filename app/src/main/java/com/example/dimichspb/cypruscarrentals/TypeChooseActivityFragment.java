package com.example.dimichspb.cypruscarrentals;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import java.util.Calendar;

/**
 * A placeholder fragment containing a simple view.
 */
public class TypeChooseActivityFragment extends Fragment {

    public TypeChooseActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_type_choose, container, false);
        ListView typesListView = popuplateTypesList(view);

        typesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), DateChooseActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private ListView popuplateTypesList(View view) {
        // Construct the data source
        ArrayList<Type> types = new ArrayList<Type>();

        // Create the adapter to convert the array to views
        TypesAdapter adapter = new TypesAdapter(this.getActivity(), types);
        LoadFeedData loadFeedData = new LoadFeedData(adapter);
        // Attach the adapter to a ListView
        ListView listView = (ListView) view.findViewById(R.id.listview_types);
        listView.setAdapter(adapter);
        // Fetch the data from API
        loadFeedData.execute();

        return listView;
    }
}
