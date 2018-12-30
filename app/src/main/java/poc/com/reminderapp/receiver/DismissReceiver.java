package poc.com.reminderapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import poc.com.reminderapp.utils.AlarmUtil;
import poc.com.reminderapp.utils.Constants;
import poc.com.reminderapp.utils.NotificationUtil;

public class DismissReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int reminderId = intent.getIntExtra(Constants.NOTIFICATION_ID, 0);
        NotificationUtil.cancelNotification(context, reminderId);
    }
}


