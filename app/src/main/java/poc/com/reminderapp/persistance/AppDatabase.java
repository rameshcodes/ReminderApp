package poc.com.reminderapp.persistance;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import poc.com.reminderapp.model.Reminder;
import poc.com.reminderapp.persistance.Dao.ReminderDao;

@Database(entities = {Reminder.class}, version = AppDatabase.DB_VERSION)
public abstract class AppDatabase extends RoomDatabase {
    protected static final int DB_VERSION = 1;
    public static final String DB_NAME = "reminder.db";

    public abstract ReminderDao getReminderDao();
}
