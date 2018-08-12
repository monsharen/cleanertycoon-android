package com.lionsinvests.cleanertycoon.game;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.ViewHolder> {
    private List<Employee> employeeList;
    private View.OnClickListener onClickListener;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView employeeName;
        private TextView happiness;
        private TextView contract;

        private ViewHolder(View viewHolder, TextView employeeName, TextView happiness, TextView contract) {
            super(viewHolder);
            this.employeeName = employeeName;
            this.happiness = happiness;
            this.contract = contract;
        }
    }

    public EmployeeListAdapter(List<Employee> employeeList, View.OnClickListener onClickListener) {
        this.employeeList = employeeList;
        this.onClickListener = onClickListener;
    }

    @Override
    public EmployeeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.employee_list_item, parent, false);

        v.setOnClickListener(onClickListener);

        TextView name = v.findViewById(R.id.employeeName);
        TextView happiness = v.findViewById(R.id.employeeHappiness);
        TextView employeeContract = v.findViewById(R.id.employeeContract);
        ViewHolder vh = new ViewHolder(v, name, happiness, employeeContract);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Employee employee = employeeList.get(position);
        holder.employeeName.setText(employee.getName());
        holder.happiness.setText("Happiness: " + employee.getHappiness());

        Contract contract = employee.getContract();
        if (contract != null) {
            holder.contract.setText("Contract: " + contract.getName());
        }



    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }
}
