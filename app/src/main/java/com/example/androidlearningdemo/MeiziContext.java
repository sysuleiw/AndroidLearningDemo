package com.example.androidlearningdemo;

import android.content.Context;

import com.example.androidlearningdemo.data.model.Meizi;

public class MeiziContext {
    private Context context;
    private MeiziContext()
    {

    }
    private static class SingleHolder
    {
        private static MeiziContext INSTANCE = new MeiziContext();
    }
    public static final MeiziContext getInstance()
    {
        return SingleHolder.INSTANCE;
    }
    public MeiziContext init(Context context) {
        this.context = context;
        return this;
    }

    public Context getContext() {
        return context;
    }

}
