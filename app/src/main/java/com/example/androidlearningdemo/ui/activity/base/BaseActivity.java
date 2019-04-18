package com.example.androidlearningdemo.ui.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import com.example.androidlearningdemo.ui.presenter.base.BasePresenter;
import butterknife.ButterKnife;

/**
 * @author wanglei
 * 初始化ContentView、subview、Presenter及数据
 *
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IView {
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View containerView = getLayoutInflater().inflate(getContentViewId(), getViewGroupRoot());
        setContentView(containerView);
        ButterKnife.bind(this);
        initPresenter();
        initSubViews(containerView);
        initData();
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

