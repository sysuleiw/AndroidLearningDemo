package com.example.androidlearningdemo.ui.fragment.base;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidlearningdemo.ui.activity.base.IView;
import com.example.androidlearningdemo.ui.presenter.base.BasePresenter;

import butterknife.ButterKnife;

/**
 * @author wanglei
 */
public class BaseFragment<T extends BasePresenter> extends Fragment implements IView {

    protected T mPresenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.initData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View containerView = inflater.inflate(getContentViewId(), container, false);
        ButterKnife.bind(this,containerView);
        initPresenter();
        initSubViews(containerView);
        return containerView;
    }

     @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
        {
            mPresenter.deatchViewRef();
        }
    }
    private void initPresenter()
    {
        this.mPresenter =  getPresenter();
    }

    protected T getPresenter()
    {
        return null;
    }
    /***IView 接口**/
    @Override
    public int getContentViewId(){
        return 0;
    };

    @Override
    public ViewGroup getViewGroupRoot()
    {
        return null;
    };

    @Override
    public void initData() {

    }
    @Override
    public void initSubViews(View view) {

    }
}
