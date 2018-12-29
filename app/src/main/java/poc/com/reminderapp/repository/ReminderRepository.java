package poc.com.reminderapp.repository;

import android.arch.lifecycle.LiveData;
import poc.com.reminderapp.ReminderApplication;
import poc.com.reminderapp.model.Reminder;
import poc.com.reminderapp.persistance.AppDatabase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ReminderRepository {

    AppDatabase db = null;
    Executor executor;

    public ReminderRepository() {
        db = ReminderApplication.getAppDatabase();
        executor = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Reminder>> getReminderData() {
        return db.getReminderDao().getAll();
    }

    public void saveReminder(final Reminder reminder) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                db.getReminderDao().insertAll(reminder);
            }
        });

    }
}
