package com.ui_template.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author funmi
 */

@SuppressLint("AppCompatCustomView")
public class CircleImageView extends ImageView {
    private float radius = 15f;
    private Path path;
    private RectF rectF;

    @Override
    public Rect getClipBounds() {
        return super.getClipBounds();
    }

    public CircleImageView(Context context) {
        super(context);
        init();
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        rectF = new RectF(0,0,this.getWidth(),this.getHeight());
        path.addRoundRect(rectF,radius,radius, Path.Direction.CW);
        canvas.clipPath(path);
        super.onDraw(canvas);
    }
}
