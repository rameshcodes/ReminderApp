package poc.com.reminderapp.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import poc.com.reminderapp.R;
import poc.com.reminderapp.viewmodel.CreateReminderViewModel;

public class CreateReminderFragment extends Fragment implements View.OnClickListener {

    private CreateReminderViewModel mViewModel;
    private AppCompatTextView timeTextView;
    private AppCompatTextView dateTextView;

    public static CreateReminderFragment newInstance() {
        return new CreateReminderFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.create_reminder_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CreateReminderViewModel.class);
        // TODO: Use the ViewModel
    }

    private void initViews(View root) {
        timeTextView = root.findViewById(R.id.timeFieldTextView);
        dateTextView = root.findViewById(R.id.dateFieldTextView);
        timeTextView.setOnClickListener(this);
        dateTextView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.timeFieldTextView) {
            showTimePickerDialog();
        } else if (v.getId() == R.id.dateFieldTextView) {
            showDatePickerDialog();
        }
    }

    private void showTimePickerDialog() {
        AppCompatDialogFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog() {
        AppCompatDialogFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }
}
