package poc.com.reminderapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.util.StringUtil;
import poc.com.reminderapp.ReminderApplication;
import poc.com.reminderapp.model.Reminder;
import poc.com.reminderapp.persistance.AppDatabase;
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

    public LiveData<List<Reminder>> getReminderData(String text) {
        if(text==null || text.length()==0)
        return reminderDao.getAll();
        else
            return reminderDao.search(text);
    }

    public void saveReminder(final Reminder reminder) {
        executor.execute(new Runnable() {
            @Override
            public void run() {reminderDao.insertAll(reminder);
            }
        });
    }






}
