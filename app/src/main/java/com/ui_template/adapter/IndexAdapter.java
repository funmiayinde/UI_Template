package com.ui_template.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ui_template.R;

/**
 * Created by funmiayinde on 1/11/18.
 */

public class IndexAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private char[] dataSet;
    private RowStyle rowStyle;
    private char prev;

    public IndexAdapter(char[] dataSet, RowStyle rowStyle) {
        this.dataSet = dataSet;
        this.rowStyle = rowStyle;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.slick_details, parent, false);
        if (rowStyle != null) {
            if (rowStyle.getRowHeight() != -1) {
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = rowStyle.getRowHeight().intValue();
                layoutParams.width = rowStyle.slickWidth.intValue();
                view.setLayoutParams(layoutParams);
            }
            TextView indexTextView = (TextView) view.findViewById(R.id.slick_row_index);
            if (rowStyle.getTextColor() != -1) {
                indexTextView.setTextColor(rowStyle.getTextColor());
            }
            if (rowStyle.getTextSize().intValue() != -1) {
                indexTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, rowStyle.getTextSize());
            }
            if (rowStyle.getTextStyle() != -1) {
                indexTextView.setTypeface(null, rowStyle.getTextStyle());
            }
        }
        return new IndexViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        IndexViewHolder viewHolder = (IndexViewHolder) holder;
        viewHolder.indexTextView.setText(Character.toString(dataSet[position]));
        if (isHeader(position)){
            viewHolder.indexTextView.setVisibility(TextView.VISIBLE);
        }else{
            viewHolder.indexTextView.setVisibility(TextView.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }

    public void setDataSet(char[] dataSet) {
        this.dataSet = dataSet;
    }

    private Boolean isHeader(int position){
        if (position == 0){
            return Boolean.TRUE;
        }else if(isSameChar(dataSet[position - 1],dataSet[position])){
            return Boolean.FALSE;
        }
        return Boolean.FALSE;

    }

    private Boolean isSameChar(char prev,char curr){
        if (Character.toLowerCase(prev) == Character.toLowerCase(curr)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }



    public class IndexViewHolder extends RecyclerView.ViewHolder {
        TextView indexTextView;

        public IndexViewHolder(View itemView) {
            super(itemView);
            indexTextView = (TextView) itemView.findViewById(R.id.slick_row_index);
        }
    }

    public static class RowStyle {
        Float rowHeight;
        Float slickWidth;
        Integer textColor;
        Float textSize;
        Integer textStyle;

        public RowStyle(Float rowHeight, Float slickWidth,
                        Integer textColor, Float textSize, Integer textStyle) {

            this.rowHeight = rowHeight;
            this.slickWidth = slickWidth;
            this.textColor = textColor;
            this.textSize = textSize;
            this.textStyle = textStyle;
        }


        public Float getRowHeight() {
            return rowHeight;
        }

        public void setRowHeight(Float rowHeight) {
            this.rowHeight = rowHeight;
        }

        public Float getSlickWidth() {
            return slickWidth;
        }

        public void setSlickWidth(Float slickWidth) {
            this.slickWidth = slickWidth;
        }

        public Integer getTextColor() {
            return textColor;
        }

        public void setTextColor(Integer textColor) {
            this.textColor = textColor;
        }

        public Float getTextSize() {
            return textSize;
        }

        public void setTextSize(Float textSize) {
            this.textSize = textSize;
        }

        public Integer getTextStyle() {
            return textStyle;
        }

        public void setTextStyle(Integer textStyle) {
            this.textStyle = textStyle;
        }
    }
}
