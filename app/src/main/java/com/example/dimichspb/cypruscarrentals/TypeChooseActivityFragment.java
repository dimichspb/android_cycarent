package com.example.dimichspb.cypruscarrentals;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
        popuplateUsersList(view);
        return view;
    }


    private void popuplateUsersList(View view) {
        // Construct the data source
        ArrayList<User> arrayOfUsers = User.getUsers();
        // Create the adapter to convert the array to views
        UsersAdapter adapter = new UsersAdapter(this.getActivity(), arrayOfUsers);
        // Attach the adapter to a ListView
        ListView listView = (ListView) view.findViewById(R.id.listview_users);
        listView.setAdapter(adapter);

    }
}
