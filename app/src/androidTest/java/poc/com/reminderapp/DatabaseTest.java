package poc.com.reminderapp;


import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import poc.com.reminderapp.model.Reminder;
import poc.com.reminderapp.persistance.AppDatabase;
import poc.com.reminderapp.persistance.Dao.ReminderDao;
import poc.com.reminderapp.utils.DateTimeUtil;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    private AppDatabase mDb;
    private ReminderDao reminderDao;
    Reminder reminder;

    private void initReminder()
    {
        reminder =  new Reminder();
        reminder.setId(1);
        reminder.setTitle("Title");
        reminder.setDescription("Desc");
        reminder.setDateTime(DateTimeUtil.toDateTime("30-12-2018","10:30 AM"));
    }
    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        reminderDao = mDb.getReminderDao();
        initReminder();
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void writeAndReadCity() {

        reminderDao.insertAll(reminder);
        Reminder reminder1 = reminderDao.getReminder(reminder.getId());
        Assert.assertEquals(reminder,reminder1);
    }

    @Test
    public void clearCityTableData() {
        reminderDao.delete(reminder);
        Reminder reminder2 = reminderDao.getReminder(reminder.getId());
        assertNull(reminder2);
    }



}
