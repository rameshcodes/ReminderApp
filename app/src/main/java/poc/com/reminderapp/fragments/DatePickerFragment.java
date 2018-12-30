package poc.com.reminderapp.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.DatePicker;
import poc.com.reminderapp.utils.DateTimeUtil;

import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends AppCompatDialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        if(getTargetFragment() instanceof CreateReminderFragment){
            ((CreateReminderFragment)getTargetFragment()).setDate(DateTimeUtil.getDateInReadableFormat(year,month,day));
        }
    }
}
