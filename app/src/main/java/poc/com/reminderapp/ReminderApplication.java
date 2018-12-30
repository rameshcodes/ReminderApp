package poc.com.reminderapp;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import poc.com.reminderapp.persistance.AppDatabase;


public class ReminderApplication extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return appContext;
    }
}
