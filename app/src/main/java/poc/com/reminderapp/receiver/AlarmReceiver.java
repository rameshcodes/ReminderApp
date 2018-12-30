package poc.com.reminderapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import poc.com.reminderapp.model.Reminder;
import poc.com.reminderapp.repository.ReminderRepository;
import poc.com.reminderapp.utils.Constants;
import poc.com.reminderapp.utils.NotificationUtil;

public class AlarmReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
        ReminderRepository reminderRepository=new ReminderRepository();
        reminderRepository.getReminder(intent.getIntExtra(Constants.NOTIFICATION_ID, 0), new ReminderRepository.RepositoryListener() {
            @Override
            public void onSuccess(Object object) {
                if(object!=null){
                    NotificationUtil.createNotification(context,(Reminder) object);
                }
            }
            @Override
            public void onError() {

            }
        });
    }
}
