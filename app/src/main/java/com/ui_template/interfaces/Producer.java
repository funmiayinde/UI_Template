package com.ui_template.interfaces;

import android.support.v7.widget.RecyclerView;

/**
 * Created by funmiayinde on 1/11/18.
 */

public interface Producer {
    public void register(Consumer newObserver);
    public void unregister(Consumer existObserver);
    public void notifyConsumer(RecyclerView recyclerView,int dx,int dy);
}
