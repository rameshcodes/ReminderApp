package poc.com.reminderapp.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import poc.com.reminderapp.R;
import poc.com.reminderapp.adapter.ReminderAdapter;
import poc.com.reminderapp.databinding.HomeFragmentBinding;
import poc.com.reminderapp.model.Reminder;
import poc.com.reminderapp.viewmodel.HomeViewModel;

import java.util.List;

public class HomeFragment extends Fragment {
    private HomeViewModel mViewModel;
    private ReminderAdapter adapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        HomeFragmentBinding homeFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.home_fragment, container, false);
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeFragmentBinding.setVm(mViewModel);
        return homeFragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initDataObserver();
    }

    private void initViews(View root) {
        RecyclerView reminderRecyclerView = root.findViewById(R.id.reminderList);
        adapter = new ReminderAdapter();
        reminderRecyclerView.setAdapter(adapter);
    }

    private void initDataObserver() {
        mViewModel.getReminderList().observe(this, new Observer<List<Reminder>>() {
            @Override
            public void onChanged(@Nullable List<Reminder> reminders) {
                if (reminders != null) {
                    adapter.setReminderList(reminders);
                }
            }
        });
    }

}
