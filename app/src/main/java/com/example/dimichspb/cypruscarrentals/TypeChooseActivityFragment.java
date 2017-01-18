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
public class TypeChooseActivityFragment extends Fragment {

    public TypeChooseActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_type_choose, container, false);
        ListView typesListView = populateTypesList(view);

        typesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Request request = new Request();

                TextView typeId = (TextView) view.findViewById(R.id.textview_typeId);
                TextView typeCode = (TextView) view.findViewById(R.id.textview_typeCode);
                TextView typeDoors = (TextView) view.findViewById(R.id.textview_typeDoors);
                TextView typeSeats = (TextView) view.findViewById(R.id.textview_typeSeats);

                Type type = new Type();
                type.id = typeId.getText().toString();
                type.code = typeCode.getText().toString();
                type.doors = typeDoors.getText().toString();
                type.seats = typeSeats.getText().toString();

                request.setType(type);
                Intent intent = new Intent(getContext(), DateChooseActivity.class);
                intent.putExtra("request", request);
                startActivity(intent);
            }
        });
        return view;
    }

    private ListView populateTypesList(View view) {
        // Construct the data source
        ArrayList<Type> types = new ArrayList<Type>();

        // Create the adapter to convert the array to views
        TypesAdapter adapter = new TypesAdapter(this.getActivity(), types);
        GetTypes getTypes = new GetTypes(adapter);
        // Attach the adapter to a ListView
        ListView listView = (ListView) view.findViewById(R.id.listview_types);
        listView.setAdapter(adapter);
        // Fetch the data from API
        getTypes.execute();

        return listView;
    }
}
