package com.lionsinvests.cleanertycoon.game;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class SimpleRecyclerListAdapter extends RecyclerView.Adapter<SimpleRecyclerListAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View[] views;

        private ViewHolder(View viewHolder, View... views) {
            super(viewHolder);
            this.views = views;
        }
    }

    private final SimpleListPopulator simpleListPopulator;

    public SimpleRecyclerListAdapter(SimpleListPopulator simpleListPopulator) {
        this.simpleListPopulator = simpleListPopulator;
    }

    @Override
    public SimpleRecyclerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(simpleListPopulator.getItemLayoutId(), parent, false);
        v.setOnClickListener(simpleListPopulator.getOnClickListener());
        View[] views = simpleListPopulator.getViews(v);
        return new ViewHolder(v, views);
    }

    @Override
    public void onBindViewHolder(SimpleRecyclerListAdapter.ViewHolder holder, int position) {
        simpleListPopulator.assignViewData(holder, position);
    }

    @Override
    public int getItemCount() {
        return simpleListPopulator.getListSize();
    }

    public interface SimpleListPopulator {
        int getItemLayoutId();
        int getListSize();
        View[] getViews(View view);
        void assignViewData(SimpleRecyclerListAdapter.ViewHolder holder, int position);
        View.OnClickListener getOnClickListener();
    }
}
