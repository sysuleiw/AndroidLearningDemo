package com.example.androidlearningdemo.ui.presenter;

import com.example.androidlearningdemo.data.model.Meizi;
import com.example.androidlearningdemo.ui.fragment.DetailFragment;
import com.example.androidlearningdemo.ui.presenter.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class DetailPresenter extends BasePresenter<IDetail> {

    public DetailPresenter(IDetail view) {
        super(view);
    }

    public void initData(List<Meizi.ResultsBean> meizis,int index) {
        List<DetailFragment> list = new ArrayList<>();
        for (Meizi.ResultsBean result : meizis) {
            list.add(DetailFragment.newInstance(result));
        }
        getViewRef().getAdapter().setData(list);
        getViewRef().getViewPager().setCurrentItem(index);
    }
}
