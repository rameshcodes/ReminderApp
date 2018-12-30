package poc.com.reminderapp.Service;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.util.Log;
import poc.com.reminderapp.model.Reminder;
import poc.com.reminderapp.receiver.AlarmReceiver;
import poc.com.reminderapp.repository.ReminderRepository;
import poc.com.reminderapp.utils.AlarmUtil;
import poc.com.reminderapp.utils.DateTimeUtil;

import java.util.Calendar;
import java.util.List;

/**
 * Since Alarm manager loses all previously registered alarm jobs when device is turned off. We launch the following class using boot completed broadcast when turned on and it will re register all the reminder from database
 */
public class ReRegisterReminderService extends JobIntentService {

    static final int JOB_ID = 1000;

    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, ReRegisterReminderService.class, JOB_ID, work);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.i("TAG","re register is called");
        ReminderRepository reminderRepository = new ReminderRepository();
        List<Reminder> reminderList = (List<Reminder>) reminderRepository.getReminderData();
        for (Reminder reminder : reminderList) {
            Calendar calendar = DateTimeUtil.toCalendar(reminder.getDateTime());
            calendar.set(Calendar.SECOND, 0);
            if (DateTimeUtil.isValidReminder(calendar)) {
                Log.i("TAG","valid reminder : "+reminder.getTitle());
                Intent alarmIntent = new Intent(this, AlarmReceiver.class);
                AlarmUtil.setAlarm(this, alarmIntent, reminder.getId(), calendar);
            }
        }
    }
}
