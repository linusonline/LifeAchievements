package se.lolektivet.linus.lifeachievements.core;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2013-09-29
 * Time: 19:28
 */
public class TimeProviderImpl implements TimeProvider
{
    @Override
    public int getYear() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.YEAR);
    }

    @Override
    public int getWeekOfYear() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.WEEK_OF_YEAR);
    }

    @Override
    public Calendar getTimeNow() {
        return Calendar.getInstance();
    }
}
