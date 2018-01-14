package com.ui_template.fragment;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ui_template.R;
import com.ui_template.activities.DriverActivity;
import com.ui_template.adapter.RecyclerViewAdapter;
import com.ui_template.model.Drivers;
import com.ui_template.util.FastScroller;
import com.ui_template.util.RecyclerViewOnItemClickListener;
import com.ui_template.util.SlickIndexUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class AllDriversFragment extends Fragment {
    private static final String TAG = "AllDriversFragment";
    private FastScroller fastScroller;
    private Activity mActivity;
    private RecyclerView recyclerView;
    private List<Drivers> driversList;
    private FloatingActionButton fab;
    public AllDriversFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        long ini = new Date().getTime();
        driversList = getDriversList();
        long aft = new Date().getTime();

        Log.i(TAG, "spent time: ".concat(Long.toString(aft-ini)));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_drivers, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
//        fastScroller  = (FastScroller) view.findViewById(R.id.fast_scroller);
//        SlickIndexUtil indexContainer = (SlickIndexUtil) view.findViewById(R.id.sticky_index_container);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(linearLayoutManager);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter (new ArrayList<>(driversList), mActivity);
        recyclerView.setAdapter(adapter);

        implementFabListener();

//        indexContainer.setDataSet(getIndexList(driversList));
//        indexContainer.setOnScrollListener(recyclerView);
//        indexContainer.subscribeForScrollListener(fastScroller);

//        fastScroller.setRecyclerView(recyclerView);
//        fastScroller.setStickyIndex(indexContainer.getStickyIndex());
        return view;
    }


    private void implementFabListener () {
        final Activity helper = getActivity();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(helper, DriverActivity.class));
//                Toast.makeText(helper, "FAB was clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private char[] getIndexList (List<Drivers> list) {
        char[] result = new char[list.size()];
        int i = 0;

        for (Drivers c : list) {
            result[i] = Character.toUpperCase(c.getName().charAt(0));
            i++;
        }
        return result;
    }

    public List<Drivers> getDriversList(){
        List<Drivers> driversList = new ArrayList<>();
        final  String[] Textlist = { "Text1", "Text2", "Text3"};
        for (int i = 0; i <=10; i++){
            Random random = new Random();
            String randomText = Textlist[random.nextInt(Textlist.length)];
            Drivers drivers = new Drivers();
            drivers.setName(randomText);
            drivers.setImage("https://api.androidhive.info/images/glide/medium/deadpool.jpg");
            driversList.add(drivers);
        }
        return driversList;
    }

}
