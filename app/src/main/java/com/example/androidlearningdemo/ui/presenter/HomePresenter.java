package com.example.androidlearningdemo.ui.presenter;

import com.example.androidlearningdemo.MeiziContext;
import com.example.androidlearningdemo.R;
import com.example.androidlearningdemo.ui.adapter.base.BaseFragmentPagerAdapter;
import com.example.androidlearningdemo.ui.fragment.MeiziFragment;
import com.example.androidlearningdemo.ui.fragment.base.BaseFragment;
import com.example.androidlearningdemo.ui.presenter.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter extends BasePresenter<IHome> {
    public HomePresenter(IHome view) {
        super(view);
    }

    public void initAdapterData(BaseFragmentPagerAdapter<BaseFragment> adapter) {
        List<String> titles = new ArrayList<>();
        titles.add(MeiziContext.getInstance().getContext().getString(R.string.meizi));
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new MeiziFragment());
        adapter.setData(titles, fragments);
    }
}
