package com.example.androidlearningdemo.ui.adapter.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidlearningdemo.R;

import java.util.List;

import butterknife.ButterKnife;

/**
 * @author wanglei
 */
public abstract class BaseRecyclerAdapter<D, VH extends BaseRecyclerAdapter.BaseViewHolder<D>> extends RecyclerView.Adapter {

    List<D> data;

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

     View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.meizi_rv_item, viewGroup,
                false);
        return createViewHolder(view);
    }


    public void onBindViewHolder(@NonNull VH viewHolder, int i) {

        viewHolder.bindData(data.get(i));
    }


    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }

    protected abstract int getLayoutId();

    protected abstract VH createViewHolder(View view);

    public List<D> getData() {
        return data;
    }

    public void setData(List<D> data) {
        this.data = data;
    }

    public abstract static class BaseViewHolder<D> extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected abstract void bindData(D data);
    }
}
