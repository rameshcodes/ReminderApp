package poc.com.reminderapp.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import poc.com.reminderapp.BR;
import poc.com.reminderapp.R;
import poc.com.reminderapp.model.Reminder;

import java.util.List;


public class ReminderAdapter extends Adapter<ReminderAdapter.ReminderViewHolder> {

    private List<Reminder> reminderList = null;

    public void setReminderList(List<Reminder> reminderList) {
        this.reminderList = reminderList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.rv_reminder_item, viewGroup, false);
        return new ReminderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderViewHolder reminderViewHolder, int pos) {
        reminderViewHolder.bind(reminderList.get(pos));
    }

    @Override
    public int getItemCount() {
        if (reminderList == null) {
            return 0;
        }
        return reminderList.size();
    }

    static class ReminderViewHolder extends RecyclerView.ViewHolder {

        private final ViewDataBinding binding;

        public ReminderViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Reminder reminder) {
            binding.setVariable(BR.reminder, reminder);
            binding.executePendingBindings();
        }
    }
}
