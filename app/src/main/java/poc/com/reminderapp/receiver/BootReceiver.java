package poc.com.reminderapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import poc.com.reminderapp.Service.ReRegisterReminderService;
import poc.com.reminderapp.model.Reminder;
import poc.com.reminderapp.persistance.AppDatabase;
import poc.com.reminderapp.persistance.DatabaseHelper;

import java.util.List;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ReRegisterReminderService.enqueueWork(context,new Intent(context,ReRegisterReminderService.class));
    }

}

