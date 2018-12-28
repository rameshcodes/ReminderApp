package poc.com.reminderapp.persistance;

import android.arch.persistence.room.RoomDatabase;

public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "reminder.db";
}
