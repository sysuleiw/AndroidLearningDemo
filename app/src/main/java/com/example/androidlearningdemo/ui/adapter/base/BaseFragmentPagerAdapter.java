package com.example.androidlearningdemo.ui.adapter.base;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @author wanglei
 * fragmentAdapter就是提供Fragment的delegate
 */
public class BaseFragmentPagerAdapter<T extends Fragment> extends FragmentStatePagerAdapter {
    protected List<T> fragments;
    protected List<String> titles;

    public BaseFragmentPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    public void setData(List<String>titles, List<T>fragments)
    {
        this.titles = titles;
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    public void setData(List<T>fragments)
    {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return (null == fragments) ? 0 : fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
