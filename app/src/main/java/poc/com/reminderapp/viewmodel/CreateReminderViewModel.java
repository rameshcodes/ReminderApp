package poc.com.reminderapp.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import poc.com.reminderapp.model.Reminder;
import poc.com.reminderapp.repository.ReminderRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateReminderViewModel extends ViewModel {
    private ObservableField<String> title = new ObservableField<>();
    private ObservableField<String> notes = new ObservableField<>();
    private ObservableField<String> time = new ObservableField<>();
    private ObservableField<String> date = new ObservableField<>();

    private ReminderRepository reminderRepository = null;

    public CreateReminderViewModel() {
        reminderRepository = new ReminderRepository();
        initDateTimeFields();
    }

    private void initDateTimeFields() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat tf = new SimpleDateFormat("HH:MM");
        date.set(df.format(calendar.getTime()));
        time.set(tf.format(calendar.getTime()));
    }

    private void addReminder() {
        Reminder reminder = new Reminder();
        reminderRepository.saveReminder(reminder);
    }
}
