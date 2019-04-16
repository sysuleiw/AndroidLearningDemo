package com.example.androidlearningdemo.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.androidlearningdemo.R;
import com.example.androidlearningdemo.data.model.Meizi;
import com.example.androidlearningdemo.ui.fragment.base.BaseFragment;
import com.example.androidlearningdemo.utils.ImageLoader;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class DetailFragment extends BaseFragment {
    @BindView(R.id.iv_meizi)
    PhotoView meiziImageView;
    private String url;
    PhotoViewAttacher mAttacher;

    public DetailFragment()
    {
        super();
    }

    public static DetailFragment newInstance(Meizi.ResultsBean meizi)
    {
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url",meizi.getUrl());
        detailFragment.setArguments(bundle);
        return detailFragment;
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_detail;
    }

    @Override
    public void initSubViews(View view) {
        super.initSubViews(view);

        ImageLoader.loadImage(getArguments().getString("url"),
                meiziImageView, getContext());
        mAttacher = new PhotoViewAttacher(meiziImageView);
        mAttacher.update();
    }
}
