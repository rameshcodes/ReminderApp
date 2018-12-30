package poc.com.reminderapp.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import poc.com.reminderapp.model.Reminder;
import poc.com.reminderapp.repository.ReminderRepository;
import poc.com.reminderapp.utils.DateTimeUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateReminderViewModel extends ViewModel {
    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> description = new ObservableField<>();
    public ObservableField<String> time = new ObservableField<>();
    public ObservableField<String> date = new ObservableField<>();

    private ReminderRepository reminderRepository = null;

    public CreateReminderViewModel() {
        reminderRepository = new ReminderRepository();
        initDateTimeFields();
    }

    private void initDateTimeFields() {
        date.set(DateTimeUtil.getCurrentDate());
        time.set(DateTimeUtil.getCurrentTime());
    }

    public void addReminder() {
        Reminder reminder = new Reminder();
        reminder.setTitle(title.get());
        reminder.setDescription(description.get());
        reminder.setDateTime(DateTimeUtil.toDateTime(date.get(),time.get()));
        reminderRepository.saveReminder(reminder);
    }

    public void setTime(String time){
        this.time.set(time);
    }

    public void setDate(String date){
        this.date.set(date);
    }
}
