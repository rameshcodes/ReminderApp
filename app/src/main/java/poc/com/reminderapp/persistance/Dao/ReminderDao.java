package poc.com.reminderapp.persistance.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import poc.com.reminderapp.model.Reminder;

import java.util.List;

@Dao
public interface ReminderDao {

    @Query("SELECT * from Reminder")
    LiveData<List<Reminder>> getAll();

    @Insert()
    void insertAll(Reminder... reminders);

    @Delete
    void delete(Reminder reminder);

    @Query("SELECT * from Reminder where title like (:text)")
    LiveData<List<Reminder>> search(String text);
}
