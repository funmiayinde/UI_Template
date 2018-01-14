package com.ui_template.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.ui_template.R;
import com.ui_template.adapter.DriverAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private PagerSlidingTabStrip tabs;
    private ViewPager viewPager;
    private DriverAdapter viewPagerAdapter;
    private Boolean isPageViewWithOffset = Boolean.FALSE;

    // Activity Callbacks __________________________________________________________________________
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().setElevation(0);

        getWindow().setSharedElementExitTransition(new Slide());
        getWindow().setSharedElementEnterTransition(new Slide());

        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPagerAdapter = new DriverAdapter(this.getSupportFragmentManager(), this);

        viewPager.setAdapter(viewPagerAdapter);

        tabs.setShouldExpand(true);
        tabs.setTextColor(getResources().getColor(R.color.text_primary));
        tabs.setDividerColor(getResources().getColor(R.color.primary));
        tabs.setIndicatorColorResource(R.color.text_primary);
        tabs.setIndicatorHeight(7);

        tabs.setViewPager(viewPager);

        viewPager.setCurrentItem(1);

        setOnSearchableListener();

    }

    @Override
    public void onResume() {
        super.onResume();

        if (isPageViewWithOffset) {
            tabs.animate().translationY(0);
            viewPager.animate().translationY(0);
            isPageViewWithOffset = Boolean.FALSE;
        }

        //this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
    }

    // MENU ________________________________________________________________________________________
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_search) {
//
        }

        return super.onOptionsItemSelected(item);
    }



    private void setOnSearchableListener () {
        SearchManager searchDialog = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);
        searchDialog.setOnCancelListener(new SearchManager.OnCancelListener() {
            @Override
            public void onCancel() {
                tabs.animate().translationY(0);
                viewPager.animate().translationY(0);
            }
        });

        searchDialog.setOnDismissListener(new SearchManager.OnDismissListener() {
            @Override
            public void onDismiss() {
                tabs.animate().translationY(0);
                viewPager.animate().translationY(0);
            }
        });
    }
}
