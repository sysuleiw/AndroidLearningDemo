package com.example.androidlearningdemo.ui.presenter.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author wanglei
 * presenter持有一个View弱引用
 */
public class BasePresenter<T> {
    protected Reference<T> mViewRef;

    public BasePresenter(T view)
    {
        mViewRef = new WeakReference<>(view);
    }

    protected T getViewRef()
    {
        return mViewRef == null ? null : mViewRef.get();
    }

    public boolean isViewAttached()
    {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void deatchViewRef()
    {
        if (mViewRef.get() != null)
        {
            mViewRef.clear();
        }
        mViewRef = null;
    }
}
