package poc.com.reminderapp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
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
    public ObservableBoolean enableSave = new ObservableBoolean();
    private int reminderID;

    private MutableLiveData<Reminder> reminderObj=new MutableLiveData<>();

    private ReminderRepository reminderRepository = null;

    public CreateReminderViewModel() {
        reminderRepository = new ReminderRepository();
        initDateTimeFields();
    }

    public void setReminderID(int reminderID) {
        this.reminderID = reminderID;
    }

    public MutableLiveData<Reminder> getReminderObj() {
        return reminderObj;
    }

    private void initDateTimeFields() {
        date.set(DateTimeUtil.getCurrentDate());
        time.set(DateTimeUtil.getCurrentTime());
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        enableSave.set(s.length()>0);
    }

    public void addReminder() {
        Reminder reminder = new Reminder();
        reminder.setId(reminderID);
        reminder.setTitle(title.get());
        reminder.setDescription(description.get());
        reminder.setDateTime(DateTimeUtil.toDateTime(date.get(),time.get()));
        reminderRepository.saveReminder(reminder);
        reminderObj.setValue(reminder);
    }

    public void setTime(String time){
        this.time.set(time);
    }

    public void setDate(String date){
        this.date.set(date);
    }
}
