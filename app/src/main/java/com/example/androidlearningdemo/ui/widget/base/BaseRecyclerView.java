package com.example.androidlearningdemo.ui.widget.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.example.androidlearningdemo.ui.listener.BaseRecyclerOnScrollListener;

import javax.annotation.Nullable;

public class BaseRecyclerView extends RecyclerView {
    private BaseRecyclerOnScrollListener mOnScrollListener;
    public final static int LIN_NUM = 2;

    public BaseRecyclerView(Context context) {
        super(context);
        init();
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        if (mOnScrollListener != null) {
            addOnScrollListener(mOnScrollListener);
        }
    }
}
