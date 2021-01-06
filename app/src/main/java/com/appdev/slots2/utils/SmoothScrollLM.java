package com.appdev.slots2.utils;

import android.util.*;
import android.content.Context;
import android.graphics.PointF;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

public class SmoothScrollLM extends LinearLayoutManager {

    private static final float MILLISECONDS_PER_INCH = 80f; //default is 25f (bigger = slower)

    public SmoothScrollLM(Context context) {
        super(context);
    }

    public SmoothScrollLM(Context context,int orientation,boolean reverseLayout) {
        super(context,orientation,reverseLayout);
    }

    public SmoothScrollLM(Context context,AttributeSet attrs,int defStyleAttr,int defStyleRes) {
        super(context,attrs,defStyleAttr,defStyleRes);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView,RecyclerView.State state,int position) {

        final LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {

            @Override
            public PointF computeScrollVectorForPosition(int targetPosition) {
                return super.computeScrollVectorForPosition(targetPosition);
            }

            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return MILLISECONDS_PER_INCH / displayMetrics.densityDpi;
            }
        };

        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }
}
