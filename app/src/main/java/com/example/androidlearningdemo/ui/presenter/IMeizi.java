package com.example.androidlearningdemo.ui.presenter;

import com.example.androidlearningdemo.ui.adapter.FooterRecyclerAdapter;

public interface IMeizi {

    FooterRecyclerAdapter getAdapter();
    void setRefresh(boolean isRefresh);
}
