package poc.com.reminderapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import poc.com.reminderapp.model.Reminder;
import poc.com.reminderapp.repository.ReminderRepository;

import java.util.List;

public class SearchReminderViewModel extends ViewModel {
    public final ObservableBoolean enableSearchButton = new ObservableBoolean(false);
    public final ObservableBoolean noResults = new ObservableBoolean(false);
    public final ObservableBoolean showLoading = new ObservableBoolean(false);
    private String searchString;

    private ReminderRepository reminderRepository;

    public SearchReminderViewModel() {
        reminderRepository = new ReminderRepository();
    }

    public LiveData<List<Reminder>> getReminderList() {
        return reminderRepository.getReminderData();

    }

    public void onTextChanged(CharSequence s, int start, int before, int count)  {
        searchString = s.toString();
        enableSearchButton.set(s.length() > 2);
    }

    public LiveData<List<Reminder>> search() {
        return reminderRepository.searchReminders(searchString);
    }
}
