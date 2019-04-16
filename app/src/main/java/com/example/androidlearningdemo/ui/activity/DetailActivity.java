package com.example.androidlearningdemo.ui.activity;

import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;

import com.example.androidlearningdemo.R;
import com.example.androidlearningdemo.data.model.Meizi;
import com.example.androidlearningdemo.ui.activity.base.BaseActivity;
import com.example.androidlearningdemo.ui.adapter.base.BaseFragmentPagerAdapter;
import com.example.androidlearningdemo.ui.presenter.DetailPresenter;
import com.example.androidlearningdemo.ui.presenter.IDetail;
import com.example.androidlearningdemo.ui.presenter.MeiziPresenter;
import com.example.androidlearningdemo.ui.widget.PhotoViewPager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DetailActivity extends BaseActivity<DetailPresenter> implements IDetail {

    @BindView(R.id.vp_detail)
    PhotoViewPager detailViewPager;
    BaseFragmentPagerAdapter adapter;


    @Override
    public void initSubViews(View view) {
        super.initSubViews(view);
        hideStatusBar();
        adapter = new BaseFragmentPagerAdapter(getSupportFragmentManager());
        detailViewPager.setAdapter(adapter);
    }

    @Override
    public void initData() {
        super.initData();
        Bundle bundle = getIntent().getExtras();
        String json = bundle.getString(MeiziPresenter.MEIZIS);
        int index = bundle.getInt(MeiziPresenter.INDEX);
        Type listType = new TypeToken<ArrayList<Meizi.ResultsBean>>() {
        }.getType();
        List<Meizi.ResultsBean> list = new Gson().fromJson(json, listType);
        if (list != null) {
            mPresenter.initData(list, index);
        }
    }

    private void hideStatusBar() {
        if (Build.VERSION.SDK_INT < 16) { // old method
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else { // Jellybean and up, new hotness
            View decorView = getWindow().getDecorView();
            // Hide the status bar.
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
            // Remember that you should never show the action bar if the
            // status bar is hidden, so hide that too if necessary.
            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.hide();
            }
        }
    }

    @Override
    protected DetailPresenter getPresenter() {
        return new DetailPresenter(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_detail;
    }

    @Override
    public BaseFragmentPagerAdapter getAdapter() {
        return adapter;
    }

    @Override
    public ViewPager getViewPager() {
        return detailViewPager;
    }
}
