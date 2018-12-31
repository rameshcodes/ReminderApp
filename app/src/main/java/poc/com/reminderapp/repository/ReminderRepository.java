package poc.com.reminderapp.repository;

import android.arch.lifecycle.LiveData;
import poc.com.reminderapp.model.Reminder;
import poc.com.reminderapp.persistance.Dao.ReminderDao;
import poc.com.reminderapp.persistance.DatabaseHelper;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ReminderRepository {

    Executor executor;
    ReminderDao reminderDao;

    public ReminderRepository() {
        reminderDao = DatabaseHelper.getAppDatabase().getReminderDao();
        executor = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Reminder>> getReminderData() {
            return reminderDao.getAll();
    }

    public void getAllReminders(final RepositoryListener repositoryListener){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    List<Reminder> list = reminderDao.getCompleteData();
                    if(repositoryListener!=null && list!=null&&!list.isEmpty()){
                        repositoryListener.onSuccess(list);
                    }
                }
            });
    }

    public void saveReminder(final Reminder reminder) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                reminderDao.insertAll(reminder);
            }
        });
    }

    public LiveData<List<Reminder>> searchReminders(String str){
        return reminderDao.search(str);
    }

    public void getReminder(final int id, final RepositoryListener repositoryListener){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Reminder reminder = reminderDao.getReminder(id);
                if(repositoryListener!=null){
                    repositoryListener.onSuccess(reminder);
                }
            }
        });
    }



    public interface RepositoryListener{
        public void onSuccess(Object object);
        public void onError();
    }


}
