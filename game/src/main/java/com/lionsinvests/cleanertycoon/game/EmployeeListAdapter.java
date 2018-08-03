package com.lionsinvests.cleanertycoon.game;

import android.os.Debug;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.ViewHolder> {
    private List<Employee> employeeList;
    private View.OnClickListener onClickListener;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView employeeName;
        private TextView happiness;

        private ViewHolder(View viewHolder, TextView employeeName, TextView happiness) {
            super(viewHolder);
            this.employeeName = employeeName;
            this.happiness = happiness;
        }
    }

    EmployeeListAdapter(List<Employee> employeeList, View.OnClickListener onClickListener) {
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
        ViewHolder vh = new ViewHolder(v, name, happiness);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Employee employee = employeeList.get(position);
        holder.employeeName.setText(employee.getName());
        holder.happiness.setText("Happiness: " + employee.getHappiness());

    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }
}
