package com.example.androidlearningdemo.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.androidlearningdemo.R;
import com.example.androidlearningdemo.ui.activity.base.BaseActivity;

import butterknife.BindView;

/**
 * @author wanglei
 */
public class AboutActivity extends BaseActivity {

    @BindView(R.id.tb_about)
    Toolbar tbAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setSupportActionBar(tbAbout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_about;
    }
}
