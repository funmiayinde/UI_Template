package com.ui_template.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ui_template.fragment.AddTripFragment;
import com.ui_template.fragment.AllDriversFragment;

/**
 * Created by funmiayinde on 1/13/18.
 */

public class DriverAdapter extends FragmentPagerAdapter{

    private Context mContext;

    public DriverAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "ALL DRIVERS";
            case 1:
                return "ADD TRIP";
            default:
                return "UNKNOWN";
        }
    }
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AllDriversFragment();
            case 1:
                return new AddTripFragment();
            default:
                return new AllDriversFragment();
        }
    }
}
