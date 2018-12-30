package poc.com.reminderapp.persistance;

import android.arch.persistence.room.Room;
import poc.com.reminderapp.ReminderApplication;

public class DatabaseHelper {
    private static AppDatabase appDatabase=null;
    public static AppDatabase getAppDatabase() {
        if(appDatabase ==null){
            appDatabase = Room.databaseBuilder(ReminderApplication.getAppContext(), AppDatabase.class, AppDatabase.DB_NAME).build();
        }
        return appDatabase;
    }
}
