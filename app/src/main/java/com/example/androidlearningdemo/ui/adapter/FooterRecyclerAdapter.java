package com.example.androidlearningdemo.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.androidlearningdemo.ui.adapter.base.BaseRecyclerAdapter;

public class FooterRecyclerAdapter<D, VH extends BaseRecyclerAdapter.BaseViewHolder<D>> extends RecyclerView.Adapter {

    private static String TAG = FooterRecyclerAdapter.class.getSimpleName();
    private static int VIEW_TYPE_FOOTER = Integer.MAX_VALUE - 1;
    private boolean needFooter = false;
    private BaseRecyclerAdapter<D, VH>mWrapped;

    public FooterRecyclerAdapter(BaseRecyclerAdapter<D,VH> adapter) {
        mWrapped = adapter;
    }

    public BaseRecyclerAdapter getWrapped() {
        return mWrapped;
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
        mWrapped.setHasStableIds(hasStableIds);
    }

    @Override
    public long getItemId(int position) {
        if (getItemViewType(position) == VIEW_TYPE_FOOTER)
        {
            return position;
        }
        else
        {
            return mWrapped.getItemId(position);
        }
    }

    public int getFooterCount() {
        return needFooter ? 1 : 0;
    }
    @Override
    public int getItemCount() {
        return mWrapped.getItemCount() + getFooterCount();
    }
    @Override
    public int getItemViewType(int position) {
        if (position == mWrapped.getItemCount()) {
            return VIEW_TYPE_FOOTER;
        } else {
            return mWrapped.getItemViewType(position);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int type = getItemViewType(i);
        if (type == VIEW_TYPE_FOOTER)
        {
            return createFooterViewHolder(viewGroup,type);
        }
        else
        {
            return mWrapped.onCreateViewHolder(viewGroup, i);
        }
    }
    private RecyclerView.ViewHolder createFooterViewHolder(ViewGroup viewGroup, int viewType)
    {
        ProgressBar progressBar = new ProgressBar(viewGroup.getContext());
        progressBar.setIndeterminate(true);
        viewGroup.addView(progressBar);

        StaggeredGridLayoutManager.LayoutParams layoutParams =
                (StaggeredGridLayoutManager.LayoutParams) progressBar.getLayoutParams();
        layoutParams.setFullSpan(true);
        return new SimpleViewHolder(progressBar);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int type = getItemViewType(i);
        if (type == VIEW_TYPE_FOOTER) {
            bindFooterViewHolder(viewHolder, i);
        } else {
            mWrapped.onBindViewHolder((VH) viewHolder, i);
        }
    }

    private void bindFooterViewHolder( RecyclerView.ViewHolder holder,  int position) {
    }
    static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public SimpleViewHolder(View itemView) {
            super(itemView);
        }
    }
    public void addFooter(){
        needFooter = true;
        notifyItemInserted(mWrapped.getItemCount());
    }

    public void removeFooter(){
        needFooter = false;
        notifyItemRemoved(mWrapped.getItemCount());
    }
}

