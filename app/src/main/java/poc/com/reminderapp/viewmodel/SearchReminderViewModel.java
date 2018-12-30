package poc.com.reminderapp.viewmodel;

import android.arch.lifecycle.*;
import android.databinding.ObservableBoolean;
import android.support.annotation.Nullable;
import poc.com.reminderapp.model.Reminder;
import poc.com.reminderapp.repository.ReminderRepository;

import java.util.List;

public class SearchReminderViewModel extends ViewModel {
    public final ObservableBoolean enableSearchButton = new ObservableBoolean(false);
    public final ObservableBoolean noResults = new ObservableBoolean(false);
    public final ObservableBoolean showLoading = new ObservableBoolean(false);
    private String searchString;
    private MediatorLiveData<List<Reminder>> remindersList = null;

    private ReminderRepository reminderRepository;

    public SearchReminderViewModel() {
        reminderRepository = new ReminderRepository();
    }

    public LiveData<List<Reminder>> getReminderList() {
        if (remindersList == null) {
            remindersList = new MediatorLiveData<>();
        }
        return remindersList;

    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        searchString = s.toString();
        enableSearchButton.set(s.length() > 0);
    }

    public void search() {
        final LiveData<List<Reminder>> liveData = reminderRepository.searchReminders(searchString);
        remindersList.addSource(liveData, new Observer<List<Reminder>>() {
            @Override
            public void onChanged(@Nullable List<Reminder> reminders) {
                if (remindersList.getValue() != null) {
                    remindersList.getValue().clear();
                }
                if (reminders != null)
                    remindersList.setValue(reminders);
                noResults.set(reminders==null || reminders.isEmpty());
            }
        });
    }
}
