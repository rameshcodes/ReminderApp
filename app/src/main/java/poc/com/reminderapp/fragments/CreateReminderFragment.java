package poc.com.reminderapp.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import poc.com.reminderapp.R;
import poc.com.reminderapp.activities.HomeActivity;
import poc.com.reminderapp.databinding.CreateReminderFragmentBinding;
import poc.com.reminderapp.model.Reminder;
import poc.com.reminderapp.receiver.AlarmReceiver;
import poc.com.reminderapp.utils.AlarmUtil;
import poc.com.reminderapp.utils.Constants;
import poc.com.reminderapp.utils.DateTimeUtil;
import poc.com.reminderapp.viewmodel.CreateReminderViewModel;

import java.util.Calendar;

public class CreateReminderFragment extends BaseFragment implements View.OnClickListener {

    private final int TARGET=1234;
    private CreateReminderViewModel mViewModel;

    public static CreateReminderFragment newInstance(int newId) {
        CreateReminderFragment createReminderFragment = new CreateReminderFragment();
        Bundle data = new Bundle();
        data.putInt(Constants.NEW_REMINDER_ID,newId);
        createReminderFragment.setArguments(data);
        return createReminderFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        CreateReminderFragmentBinding createReminderFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.create_reminder_fragment,container,false);
        mViewModel = ViewModelProviders.of(this).get(CreateReminderViewModel.class);
        mViewModel.setReminderID(getArguments().getInt(Constants.NEW_REMINDER_ID));
        createReminderFragmentBinding.setVm(mViewModel);
        return createReminderFragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initObserver();
    }

    private void initViews(View root) {
        AppCompatTextView timeTextView = root.findViewById(R.id.timeFieldTextView);
        AppCompatTextView timeLabelTextView = root.findViewById(R.id.timeLabelTextView);
        AppCompatTextView dateLabelTextView = root.findViewById(R.id.dateLabelTextView);
        AppCompatTextView dateTextView = root.findViewById(R.id.dateFieldTextView);
        AppCompatButton save = root.findViewById(R.id.saveReminder);
        save.setOnClickListener(this);
        timeLabelTextView.setOnClickListener(this);
        timeTextView.setOnClickListener(this);
        dateTextView.setOnClickListener(this);
        dateLabelTextView.setOnClickListener(this);
    }

    private void initObserver(){
        mViewModel.getReminderObj().observe(this, new Observer<Reminder>() {
            @Override
            public void onChanged(@Nullable Reminder reminder) {
                registerAlarmIntent(reminder);
            }
        });
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.timeFieldTextView || v.getId()==R.id.timeLabelTextView) {
            showTimePickerDialog();
        } else if (v.getId() == R.id.dateFieldTextView || v.getId() == R.id.dateLabelTextView)  {
            showDatePickerDialog();
        } else if(v.getId()==R.id.saveReminder){
            mViewModel.addReminder();
            ((HomeActivity)getActivity()).onBackPressed();
        }
    }

    private void showTimePickerDialog() {
        AppCompatDialogFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.setTargetFragment(this,TARGET);
        timePickerFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog() {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setTargetFragment(this,TARGET);
        datePickerFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    public void setDate(String date){
        mViewModel.setDate(date);
    }

    public void setTime(String time){
        mViewModel.setTime(time);
    }

    private void registerAlarmIntent(Reminder reminder){
        Intent alarmIntent = new Intent(getContext(), AlarmReceiver.class);
        Calendar calendar = DateTimeUtil.toCalendar(reminder.getDateTime());
        calendar.set(Calendar.SECOND, 0);
        AlarmUtil.setAlarm(getContext(), alarmIntent, reminder.getId(), calendar);
    }
}
