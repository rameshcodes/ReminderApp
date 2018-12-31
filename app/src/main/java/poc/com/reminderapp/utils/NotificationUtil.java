package poc.com.reminderapp.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import poc.com.reminderapp.R;
import poc.com.reminderapp.activities.HomeActivity;
import poc.com.reminderapp.model.Reminder;
import poc.com.reminderapp.receiver.DismissReceiver;

public class NotificationUtil {
    public static void createNotification(Context context, Reminder reminder) {
        // Create intent for notification onClick behaviour
        Intent viewIntent = new Intent(context, HomeActivity.class);
        viewIntent.putExtra("NOTIFICATION_ID", reminder.getId());
        viewIntent.putExtra("NOTIFICATION_DISMISS", true);
        PendingIntent pending = PendingIntent.getActivity(context, reminder.getId(), viewIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        int imageResId = R.mipmap.ic_launcher;

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(imageResId)
                .setColor(context.getResources().getColor(R.color.colorAccent))
                .setContentTitle(reminder.getTitle())
                .setContentText(reminder.getDescription())
                .setTicker(reminder.getTitle())
                .setContentIntent(pending);


        Intent swipeIntent = new Intent(context, DismissReceiver.class);
        swipeIntent.putExtra("NOTIFICATION_ID", reminder.getId());
        PendingIntent pendingDismiss = PendingIntent.getBroadcast(context, reminder.getId(), swipeIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setDeleteIntent(pendingDismiss);

        String soundUri = "content://settings/system/notification_sound";
        if (soundUri.length() != 0) {
            builder.setSound(Uri.parse(soundUri));
        }
        long[] pattern = {0, 300, 0};
        builder.setVibrate(pattern);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            builder.setPriority(Notification.PRIORITY_HIGH);
        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(reminder.getId(), builder.build());
    }

    public static void cancelNotification(Context context, int notificationId) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(notificationId);
    }
}
