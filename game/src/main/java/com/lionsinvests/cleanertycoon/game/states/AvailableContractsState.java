package com.lionsinvests.cleanertycoon.game.states;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import android.widget.TextView;
import com.lionsinvests.cleanertycoon.game.Contract;
import com.lionsinvests.cleanertycoon.game.ContractDatabase;
import com.lionsinvests.cleanertycoon.game.SimpleRecyclerListAdapter;
import com.lionsinvests.cleanertycoon.game.Employee;
import com.lionsinvests.cleanertycoon.game.GameLogic;
import com.lionsinvests.cleanertycoon.game.R;
import com.lionsinvests.cleanertycoon.game.statemachine.EventListener;
import com.lionsinvests.cleanertycoon.game.statemachine.Session;
import com.lionsinvests.cleanertycoon.game.statemachine.State;
import com.lionsinvests.cleanertycoon.game.statemachine.StateId;

import java.util.List;
import java.util.Locale;

public class AvailableContractsState implements State {

    private RecyclerView listRecyclerView;
    private RecyclerView.Adapter recyclerViewAdapter = null;
    private Activity activity;
    private Session session;
    private GameLogic gameLogic;
    private EventListener eventListener;
    
    @Override
    public void init(Activity activity, Session session, GameLogic gameLogic, EventListener eventListener) {
        activity.setContentView(R.layout.activity_contracts);
        this.activity = activity;
        this.session = session;
        this.gameLogic = gameLogic;
        this.eventListener = eventListener;

        configureAvailableContracts();
    }

    @Override
    public void end() {

    }

    private void configureAvailableContracts() {
        listRecyclerView = activity.findViewById(R.id.availableContractsList);
        listRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        listRecyclerView.setLayoutManager(mLayoutManager);

        final List<Contract> contracts = ContractDatabase.getInstance().getContracts();
        SimpleRecyclerListAdapter.SimpleListPopulator simpleListPopulator = new ContractListPopulator(contracts);
        recyclerViewAdapter = new SimpleRecyclerListAdapter(simpleListPopulator);
        listRecyclerView.setAdapter(recyclerViewAdapter);
    }

    private class ContractListPopulator implements SimpleRecyclerListAdapter.SimpleListPopulator {

        private final List<Contract> contracts;

        public ContractListPopulator(List<Contract> contracts) {
            this.contracts = contracts;
        }

        @Override
        public int getItemLayoutId() {
            return R.layout.contract_list_item;
        }

        @Override
        public int getListSize() {
            return ContractDatabase.getInstance().getContracts().size();
        }

        @Override
        public View[] getViews(View view) {
            View[] views = new View[6];
            views[0] = view.findViewById(R.id.name);
            views[1] = view.findViewById(R.id.length);
            views[2] = view.findViewById(R.id.pay);
            views[3] = view.findViewById(R.id.happiness);
            views[4] = view.findViewById(R.id.terminationFee);
            views[5] = view.findViewById(R.id.level);
            return views;
        }

        @Override
        public void assignViewData(SimpleRecyclerListAdapter.ViewHolder holder, int position) {
            Contract contract = contracts.get(position);
            ((TextView) holder.views[0]).setText(contract.getName());
            ((TextView) holder.views[1]).setText(String.format(Locale.getDefault(), "Length: %d weeks", contract.getLength()));
            ((TextView) holder.views[2]).setText(String.format(Locale.getDefault(), "Pay: $%.0f", contract.getPay()));
            ((TextView) holder.views[3]).setText(String.format(Locale.getDefault(), "Happiness: %d", contract.getHappiness()));
            ((TextView) holder.views[4]).setText(String.format(Locale.getDefault(), "Termination fee: $%.0f", contract.getTerminationFee()));
            ((TextView) holder.views[5]).setText(String.format(Locale.getDefault(), "Level: %d", contract.getLevel()));

        }

        @Override
        public View.OnClickListener getOnClickListener() {
            return new ContractsListOnClickListener(listRecyclerView);
        }
    }

    private class ContractsListOnClickListener implements View.OnClickListener {

        private RecyclerView mRecyclerView;

        ContractsListOnClickListener(RecyclerView recyclerView) {
            this.mRecyclerView = recyclerView;
        }

        @Override
        public void onClick(View view) {
            Integer employeeId = session.getInteger("employeeId");
            Employee employee = gameLogic.getPlayer().getCompany().getEmployees().get(employeeId);

            int itemPosition = mRecyclerView.getChildLayoutPosition(view);
            Contract contract = ContractDatabase.getInstance().getContracts().get(itemPosition);
            ContractDatabase.getInstance().getContracts().remove(contract);
            employee.setContract(contract);
            eventListener.onEvent(StateId.MAIN_SCREEN);

        }
    }
}
