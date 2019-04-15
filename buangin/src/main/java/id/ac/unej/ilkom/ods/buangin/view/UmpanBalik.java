package id.ac.unej.ilkom.ods.buangin.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.view.Volunteer.HomeVolunteer;

/**
 * A simple {@link Fragment} subclass.
 */
public class UmpanBalik extends Fragment {


    public UmpanBalik() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((HomeVolunteer) getActivity()).setActionBarTitle("Timbal Balik");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_umpan_balik, container, false);
    }

}
