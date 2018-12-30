package poc.com.reminderapp;

import org.junit.Assert;
import org.junit.Test;
import poc.com.reminderapp.utils.DateTimeUtil;

import java.util.Calendar;

public class DateUtilsTest {

    @Test
    public void validateDays(){
        Calendar c1= Calendar.getInstance();
        c1.set(Calendar.HOUR_OF_DAY,c1.get(Calendar.HOUR_OF_DAY)+1);
        boolean isValid = DateTimeUtil.isValidReminder(c1);
        Assert.assertEquals(isValid,true);
    }
}
