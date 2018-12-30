package poc.com.reminderapp.repository;

import android.arch.lifecycle.LiveData;
import poc.com.reminderapp.ReminderApplication;
import poc.com.reminderapp.model.Reminder;
import poc.com.reminderapp.persistance.Dao.ReminderDao;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ReminderRepository {

    Executor executor;
    ReminderDao reminderDao;

    public ReminderRepository() {
        reminderDao = ReminderApplication.getAppDatabase().getReminderDao();
        executor = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Reminder>> getReminderData() {
            return reminderDao.getAll();
    }

    public void saveReminder(final Reminder reminder) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                reminderDao.insertAll(reminder);
            }
        });
    }

    public LiveData<List<Reminder>> searchReminders(String str){
//        return reminderDao.search(str);
        return null;
    }

}
