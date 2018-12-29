package poc.com.reminderapp;

import android.app.Application;
import android.arch.persistence.room.Room;
import poc.com.reminderapp.persistance.AppDatabase;


public class ReminderApplication extends Application {

    private static AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        initDb();
    }

    private void initDb() {
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, AppDatabase.DB_NAME).build();
    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
