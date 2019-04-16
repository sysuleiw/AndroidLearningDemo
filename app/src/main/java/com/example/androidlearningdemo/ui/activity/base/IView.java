package com.example.androidlearningdemo.ui.activity.base;

import android.view.View;
import android.view.ViewGroup;

public interface IView {

    ViewGroup getViewGroupRoot();

    int getContentViewId();

    void initSubViews(View view);

    void initData();
}
