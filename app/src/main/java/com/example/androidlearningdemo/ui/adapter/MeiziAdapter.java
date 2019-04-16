package com.example.androidlearningdemo.ui.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.androidlearningdemo.R;
import com.example.androidlearningdemo.data.model.Meizi;
import com.example.androidlearningdemo.ui.activity.DetailActivity;
import com.example.androidlearningdemo.ui.adapter.base.BaseRecyclerAdapter;
import com.example.androidlearningdemo.ui.presenter.MeiziPresenter;
import com.example.androidlearningdemo.ui.widget.ScaleImageView;
import com.example.androidlearningdemo.utils.ActivityUtil;
import com.example.androidlearningdemo.utils.ImageLoader;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MeiziAdapter extends BaseRecyclerAdapter<Meizi.ResultsBean, MeiziAdapter.MeiziViewHolder> {
    @Override
    protected int getLayoutId() {
        return R.layout.meizi_rv_item;
    }

    @Override
    protected MeiziViewHolder createViewHolder(View view) {
        return new MeiziViewHolder(view,getData());
    }

    public static class MeiziViewHolder extends BaseRecyclerAdapter.BaseViewHolder<Meizi.ResultsBean> {

        private static final String TAG = "MeiziViewHolder";
        @BindView(R.id.iv_item)
        ScaleImageView itemImageView;
        private List<Meizi.ResultsBean> data;

        public MeiziViewHolder(View itemView, List<Meizi.ResultsBean> data) {
            super(itemView);
            this.data = data;
        }

        @Override
        protected void bindData(Meizi.ResultsBean data) {
            ImageLoader.loadImage(data.getUrl(),itemImageView,itemImageView.getContext());
        }

        @OnClick(R.id.iv_item)
        public void onClick() {
            Bundle bundle = new Bundle();
            bundle.putInt(MeiziPresenter.INDEX,getAdapterPosition());
            bundle.putString(MeiziPresenter.MEIZIS,new Gson().toJson(data));
            ActivityUtil.startActivityNotInActivity(itemImageView.getContext(), DetailActivity.class,bundle);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
    }
}
