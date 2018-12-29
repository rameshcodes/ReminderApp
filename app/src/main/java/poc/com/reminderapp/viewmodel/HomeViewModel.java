package poc.com.reminderapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import poc.com.reminderapp.model.Reminder;
import poc.com.reminderapp.repository.ReminderRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {
//    private MutableLiveData<List<Reminder>> remindersList = null;
    private ReminderRepository reminderRepository;

    public HomeViewModel() {
        reminderRepository = new ReminderRepository();
//        fetchData();
    }

    public LiveData<List<Reminder>> getReminderList() {
//        if (remindersList == null) {
//            remindersList = new MutableLiveData<>();
//        }
        return reminderRepository.getReminderData();
    }

//    private void fetchData() {
//        remindersList.setValue(reminderRepository.getReminderData());
//    }
}
