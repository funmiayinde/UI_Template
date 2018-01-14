package com.ui_template.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ui_template.R;
import com.ui_template.adapter.IndexAdapter;
import com.ui_template.interfaces.Consumer;
import com.ui_template.listener.IndexScrollerListener;
import com.ui_template.manager.IndexLayoutManager;

/**
 * Created by funmiayinde on 1/13/18.
 */

public class SlickIndexUtil extends RelativeLayout {
    private RecyclerView indexList;
    private IndexScrollerListener scrollListener;

    private IndexAdapter adapter;
    private IndexLayoutManager stickyIndex;

    // Constructors ________________________________________________________________________________
    public SlickIndexUtil(Context context) {
        super(context);
        initialize(context, null);
    }

    public SlickIndexUtil(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize (context, attrs);
    }

    public SlickIndexUtil(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize (context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SlickIndexUtil(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize (context, attrs);
    }

    private void initialize (Context context, AttributeSet attrs) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.slick_index, this, true);

        // Creates RecyclerView and its layout
        this.indexList = (RecyclerView) this.findViewById(R.id.index_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

        this.indexList.setLayoutManager(linearLayoutManager);
        this.indexList.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });

        char[] dataSet = {};

        IndexAdapter.RowStyle styles = getRowStyle(context, attrs);

        // Change sticky width
        View stickyWrapper = (LinearLayout) this.findViewById(R.id.sticky_index_wrapper);
        android.view.ViewGroup.LayoutParams params = stickyWrapper.getLayoutParams();
        params.width = styles.getSlickWidth().intValue();
        stickyWrapper.setLayoutParams(params);
        this.invalidate();

        this.adapter = new IndexAdapter(dataSet, styles);
        this.indexList.setAdapter(adapter);

        this.scrollListener = new IndexScrollerListener();
        this.scrollListener.setOnScrollListener(indexList);

        this.stickyIndex = new IndexLayoutManager(this);
        this.stickyIndex.setSlickIndexTextView(indexList);
        scrollListener.register(stickyIndex);

        setSlickIndexStyle(styles);
    }

    // UTIL ________________________________________________________________________________________
    private IndexAdapter.RowStyle getRowStyle (Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SlickIndexUtil);

            float textSize = typedArray.getDimension(R.styleable.SlickIndexUtil_android_textSize, -1);
            int textColor = typedArray.getColor(R.styleable.SlickIndexUtil_android_textColor, -1);

            textSize = (textSize != -1) ? textSize : 26;
            textColor = (textColor != -1) ? textColor : getResources().getColor(R.color.index_text_color);

            return new IndexAdapter.RowStyle(
                    typedArray.getDimension(R.styleable.SlickIndexUtil_rowHeight, -1f),
                    typedArray.getDimension(R.styleable.SlickIndexUtil_slickWidth, -1f),
                    textColor,
                    textSize,
                    typedArray.getInt(R.styleable.SlickIndexUtil_android_textStyle, -1)
            );

        } else {
            return null;
        }
    }

    private void setSlickIndexStyle(IndexAdapter.RowStyle styles) {
        if (styles.getRowHeight() != -1) {
            LinearLayout stickyIndexWrapper = (LinearLayout) this.findViewById(R.id.sticky_index_wrapper);
            android.view.ViewGroup.LayoutParams params = stickyIndexWrapper.getLayoutParams();
            params.height = styles.getRowHeight().intValue();
            stickyIndexWrapper.setLayoutParams(params);
        }

        if (styles.getTextSize() != -1) {
            stickyIndex.getSlickIndexTextView().setTextSize(TypedValue.COMPLEX_UNIT_PX, styles.getTextSize());
        }

        if (styles.getTextColor() != null) {
            stickyIndex.getSlickIndexTextView().setTextColor(styles.getTextColor());
        }

        if (styles.getTextStyle() != -1) {
            stickyIndex.getSlickIndexTextView().setTypeface(null, styles.getTextStyle());
        }
    }


    public void subscribeForScrollListener(Consumer nexSubscriber) {
        scrollListener.register(nexSubscriber);
    }

    public void removeScrollListenerSubscription(Consumer oldSubscriber) {
        scrollListener.unregister(oldSubscriber);
    }


    public void setDataSet(char[] dataSet) {
        this.adapter.setDataSet(dataSet);
        this.adapter.notifyDataSetChanged();
    }

    public IndexLayoutManager getStickyIndex() {
        return stickyIndex;
    }

    public void setOnScrollListener(RecyclerView rl) {
        scrollListener.setOnScrollListener(rl);
    }
}
