package com.ui_template.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.ui_template.R;

import java.util.ArrayList;
import java.util.List;

public class AddTripFragment extends Fragment {

    private List<String> catList = new ArrayList<>();
    private List<String> subCatList = new ArrayList<>();
    private Spinner catSpinner;
    private Spinner catSpinner2;
    private Spinner catSpinner3;
    private Spinner catSpinner4;
    private ArrayAdapter<String> adapter;

    public AddTripFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_trip, container, false);
        catSpinner = (Spinner) view.findViewById(R.id.spinner1);
        catSpinner2 = (Spinner) view.findViewById(R.id.spinner2);
        catSpinner3 = (Spinner) view.findViewById(R.id.spinner3);
        catSpinner4 = (Spinner) view.findViewById(R.id.spinner4);

        catList.add("Select Category");
        adapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, catList);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        catSpinner.setAdapter(adapter);

        catList.add("Select Category");
        adapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, catList);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        catSpinner2.setAdapter(adapter);

        catList.add("Select Category");
        adapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, catList);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        catSpinner3.setAdapter(adapter);

        catList.add("Select Category");
        adapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, catList);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        catSpinner4.setAdapter(adapter);

        return view;
    }


}
