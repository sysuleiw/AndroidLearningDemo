package com.example.androidlearningdemo.ui.activity.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidlearningdemo.ui.presenter.base.BasePresenter;

import butterknife.ButterKnife;

/**
 * @author wanglei
 * 采用MVP架构
 *
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IView {

    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        bindContentView();
        initPresenter();
        initData();
    }

    private void bindContentView()
    {
        View containerView = getLayoutInflater().inflate(this.getContentViewId(),getViewGroupRoot());
        setContentView(containerView);
        ButterKnife.bind(this);
        initSubViews(containerView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
        {
            mPresenter.deatchViewRef();
        }
    }
    private void initPresenter()
    {
        mPresenter =  getPresenter();
    }

    protected T getPresenter()
    {
        return null;
    }

    /***IView 接口**/
    @Override
    public abstract int getContentViewId();

    @Override
    public ViewGroup getViewGroupRoot(){
        return null;
    };


    @Override
    public void initData() {

    }
    @Override
    public void initSubViews(View view) {

    }
}

