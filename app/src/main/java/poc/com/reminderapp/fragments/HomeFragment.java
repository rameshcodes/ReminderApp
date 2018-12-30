package poc.com.reminderapp.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import poc.com.reminderapp.R;
import poc.com.reminderapp.activities.HomeActivity;
import poc.com.reminderapp.adapter.ReminderAdapter;
import poc.com.reminderapp.databinding.HomeFragmentBinding;
import poc.com.reminderapp.model.Reminder;
import poc.com.reminderapp.utils.SearchTransition;
import poc.com.reminderapp.viewmodel.HomeViewModel;

import java.util.List;

public class HomeFragment extends BaseFragment {
    private HomeViewModel mViewModel;
    private ReminderAdapter adapter;
    private LinearLayout searchLayout;

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
        searchLayout = root.findViewById(R.id.search_layout);
        RecyclerView reminderRecyclerView = root.findViewById(R.id.reminderList);
        adapter = new ReminderAdapter();
        reminderRecyclerView.setAdapter(adapter);
        searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadSearchFragment();
            }
        });
    }

    private void initDataObserver() {
        mViewModel.getReminderList().observe(this, new Observer<List<Reminder>>() {
            @Override
            public void onChanged(@Nullable List<Reminder> reminders) {
                mViewModel.showEmptyListView(reminders==null || reminders.isEmpty());
                if (reminders != null) {
                    adapter.setReminderList(reminders);
                }
            }
        });
    }

    private void loadSearchFragment(){
        ((HomeActivity)getActivity()).showBackButton();
        SearchRemindersFragment searchFragment = new SearchRemindersFragment();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            searchFragment.setEnterTransition(new Fade());
            setExitTransition(new Fade());
            searchFragment.setSharedElementEnterTransition(new SearchTransition());
        }
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addSharedElement(searchLayout, getString(R.string.search_transition));
        fragmentTransaction.replace(R.id.container, searchFragment).addToBackStack("Search");
        fragmentTransaction.commit();

    }

    public int lastReminderIndex(){
        Reminder reminder= adapter.getLastItem();
        int index=1;
        if(reminder!=null){
            index+=reminder.getId();
        }
        return index;
    }

}
