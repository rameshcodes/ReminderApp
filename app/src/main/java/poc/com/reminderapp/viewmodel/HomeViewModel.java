package poc.com.reminderapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import poc.com.reminderapp.model.Reminder;
import poc.com.reminderapp.repository.ReminderRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {

    public final ObservableBoolean noResults = new ObservableBoolean(false);
    private ReminderRepository reminderRepository;

    public HomeViewModel() {
        reminderRepository = new ReminderRepository();
    }

    public LiveData<List<Reminder>> getReminderList() {
        return reminderRepository.getReminderData();
    }

    public void showEmptyListView(boolean value ){
        noResults.set(value);
    }

}
