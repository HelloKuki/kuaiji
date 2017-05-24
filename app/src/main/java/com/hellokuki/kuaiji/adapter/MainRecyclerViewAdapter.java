package com.hellokuki.kuaiji.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hellokuki.kuaiji.R;

/**
 * Created by huangqiyu on 2017/5/23.
 */
public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {
    public final int NORMAL = 1;
    public final int FOOTER = 2;


    private LayoutInflater mInflater;
    private View mFooterView;


    public MainRecyclerViewAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void addFooterView(View footerView) {
        mFooterView = footerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mFooterView != null && viewType == FOOTER) {
            return new ViewHolder(mFooterView);
        }
        return new ViewHolder(mInflater.inflate(R.layout.layout_bill_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        if (mFooterView == null) {
            return NORMAL;
        }

        if (position == getItemCount() - 1) {
            return FOOTER;
        }
        return NORMAL;
    }

    @Override
    public int getItemCount() {
        if (mFooterView != null) {
            return 10 + 1;
        }
        return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
            if (itemView == mFooterView) return;
        }
    }
}
