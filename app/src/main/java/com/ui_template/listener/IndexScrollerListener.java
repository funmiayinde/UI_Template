package com.ui_template.listener;

import android.support.v7.widget.RecyclerView;

import com.ui_template.interfaces.Consumer;
import com.ui_template.interfaces.Producer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by funmiayinde on 1/13/18.
 */

public class IndexScrollerListener extends RecyclerView.OnScrollListener implements Producer{

    private List<Consumer>consumerList;

    public IndexScrollerListener(){
        this.consumerList = new ArrayList<>();
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        notifyConsumer(recyclerView,dx,dy);
    }

    @Override
    public void register(Consumer newObserver) {
        consumerList.add(newObserver);
    }

    @Override
    public void unregister(Consumer existObserver) {
        consumerList.remove(existObserver);
    }

    @Override
    public void notifyConsumer(RecyclerView recyclerView, int dx, int dy) {
        for (Consumer consumer : consumerList){
            consumer.update(recyclerView,dx,dy);
        }
    }

    public void setOnScrollListener(RecyclerView rv){
        rv.setOnScrollListener(this);
    }
}
