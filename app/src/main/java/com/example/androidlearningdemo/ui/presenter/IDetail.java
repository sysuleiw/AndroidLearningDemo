package com.example.androidlearningdemo.ui.presenter;

import android.support.v4.view.ViewPager;

import com.example.androidlearningdemo.ui.adapter.base.BaseFragmentPagerAdapter;

public interface IDetail {

    BaseFragmentPagerAdapter getAdapter();
    ViewPager getViewPager();
}
