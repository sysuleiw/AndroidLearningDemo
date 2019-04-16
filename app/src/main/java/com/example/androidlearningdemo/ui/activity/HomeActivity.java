package com.example.androidlearningdemo.ui.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.androidlearningdemo.R;
import com.example.androidlearningdemo.ui.activity.base.BaseActivity;
import com.example.androidlearningdemo.ui.adapter.base.BaseFragmentPagerAdapter;
import com.example.androidlearningdemo.ui.fragment.base.BaseFragment;
import com.example.androidlearningdemo.ui.presenter.HomePresenter;
import com.example.androidlearningdemo.ui.presenter.IHome;
import com.example.androidlearningdemo.utils.ActivityUtil;

import butterknife.BindView;

/**
 * @author wanglei
 */
public class HomeActivity extends BaseActivity<HomePresenter> implements IHome {
    @BindView(R.id.vp_main)
    ViewPager viewPager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    BaseFragmentPagerAdapter<BaseFragment> adapter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initSubViews(View view) {
        super.initSubViews(view);
        setSupportActionBar(toolbar);
        adapter = new BaseFragmentPagerAdapter<>(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter.initAdapterData(adapter);
    }
    @Override
    protected HomePresenter getPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            ActivityUtil.startActivity(this, AboutActivity.class);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
