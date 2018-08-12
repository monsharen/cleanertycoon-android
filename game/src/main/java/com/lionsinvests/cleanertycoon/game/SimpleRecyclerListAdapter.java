package com.lionsinvests.cleanertycoon.game;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

public class SimpleRecyclerListAdapter extends RecyclerView.Adapter<SimpleRecyclerListAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final String[] texts;

        private ViewHolder(View viewHolder, String... texts) {
            super(viewHolder);
            this.texts = texts;
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
        String[] texts = new String[simpleListPopulator.getNumberOfTexts()];
        ViewHolder vh = new ViewHolder(v, texts);

        return vh;
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
        int getNumberOfTexts();
        void assignViewData(SimpleRecyclerListAdapter.ViewHolder holder, int position);
        View.OnClickListener getOnClickListener();
    }
}
