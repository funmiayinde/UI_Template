package com.ui_template.manager;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ui_template.R;
import com.ui_template.interfaces.Consumer;
import com.ui_template.interfaces.Producer;

/**
 * Created by funmiayinde on 1/11/18.
 */

public class IndexLayoutManager implements Consumer{
    private TextView slickIndexTextView;
    private RecyclerView indexRecycleView;

    public IndexLayoutManager(RelativeLayout relativeLayout){
        this.slickIndexTextView = (TextView) relativeLayout.findViewById(R.id.slick_row_index);
    }

    private Boolean isHeader(TextView prev, TextView act){
        if (isSameChar(prev.getText().charAt(0),act.getText().charAt(0))){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private Boolean isSameChar(char prev,char curr){
        if (Character.toLowerCase(prev) == Character.toLowerCase(curr)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private void updatePositionBasedOnReferenceList(RecyclerView rv){
        View firstVisibleView = rv.getChildAt(0);
        int actual = rv.getChildLayoutPosition(firstVisibleView);
        ((LinearLayoutManager) indexRecycleView.getLayoutManager()).
                scrollToPositionWithOffset(actual,firstVisibleView.getTop() + 0);
    }


    @Override
    public void update(RecyclerView recyclerView, float dx, float dy) {
        if (indexRecycleView != null){
            updatePositionBasedOnReferenceList(recyclerView);

            View firstVisibleView = indexRecycleView.getChildAt(0);
            View secondVisibleView = indexRecycleView.getChildAt(1);

            TextView firstRowIndex = (TextView) firstVisibleView.findViewById(R.id.slick_row_index);
            TextView secondRowIndex = (TextView) secondVisibleView.findViewById(R.id.slick_row_index);

            int visibleRange = indexRecycleView.getChildCount();
            int actual = indexRecycleView.getChildPosition(firstRowIndex);
            int next = actual + 1;
            int last = actual + visibleRange;

            slickIndexTextView.setText(String.valueOf(getIndexContext(firstRowIndex)).toLowerCase());
            slickIndexTextView.setVisibility(TextView.VISIBLE);
            firstRowIndex.setAlpha(1);

            if (dy > 0){
                if (next <= last){
                    if (isHeader(firstRowIndex,secondRowIndex)){
                        slickIndexTextView.setVisibility(TextView.INVISIBLE);
                        firstRowIndex.setVisibility(TextView.VISIBLE);
                        firstRowIndex.setAlpha(1 - (Math.abs(firstRowIndex.getY())/
                                firstRowIndex.getHeight()));

                    }else {
                        if (next <= last){
                            firstRowIndex.setVisibility(TextView.VISIBLE);
                            if ((isHeader(firstRowIndex, secondRowIndex) || (getIndexContext(firstRowIndex)
                                    != getIndexContext(secondRowIndex))) &&
                                    isHeader(firstRowIndex, secondRowIndex)){

                                slickIndexTextView.setVisibility(TextView.INVISIBLE);
                                firstRowIndex.setVisibility(TextView.VISIBLE);
                                firstRowIndex.setAlpha(1 - (Math.abs(firstRowIndex.getY())/
                                        firstRowIndex.getHeight()));
                                secondRowIndex.setVisibility(TextView.VISIBLE);

                            }else{
                                secondRowIndex.setVisibility(TextView.INVISIBLE);
                            }
                        }
                    }
                    if (slickIndexTextView.getVisibility() ==  TextView.VISIBLE){
                        firstRowIndex.setVisibility(TextView.INVISIBLE);
                    }
                }
            }
        }
    }

    public TextView getSlickIndexTextView() {
        return slickIndexTextView;
    }

    private char getIndexContext(TextView slickIndexTextView){
        return slickIndexTextView.getText().charAt(0);
    }

    public void setSlickIndexTextView(RecyclerView indexRecycleView){
        this.indexRecycleView = indexRecycleView;
    }

}
