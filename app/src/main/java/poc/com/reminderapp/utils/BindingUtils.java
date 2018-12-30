package poc.com.reminderapp.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public final class BindingUtils {

    @BindingAdapter({"dateTime"})
    public static void setFavorite(final TextView view, final String dateTime) {
        view.setText(DateTimeUtil.toReadableDateTime(dateTime));
    }
    @BindingAdapter({"enable"})
    public static void enableImageButton(final ImageButton imageButton, final boolean isEnabled) {
        imageButton.setEnabled(isEnabled);
    }
}
