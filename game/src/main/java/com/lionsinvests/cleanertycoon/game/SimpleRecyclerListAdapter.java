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

    public class ContractSimpleListPopulator implements SimpleListPopulator {

        private final List<Contract> contracts;

        public ContractSimpleListPopulator(List<Contract> contracts) {
            this.contracts = contracts;
        }

        @Override
        public int getItemLayoutId() {
            return R.layout.contract_list_item;
        }

        @Override
        public int getListSize() {
            return contracts.size();
        }

        @Override
        public int getNumberOfTexts() {
            return 6;
        }

        @Override
        public void assignViewData(ViewHolder holder, int position) {
            Contract contract = contracts.get(position);
            holder.texts[0] = "";
        }

        @Override
        public View.OnClickListener getOnClickListener() {
            return new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            };
        }
    }
}
