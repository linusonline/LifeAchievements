package se.lolektivet.linus.lifeachievements.core;

import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2013-09-22
 * Time: 20:55
 */
public class StandardUpdater implements Updater
{
    private Calendar _lastUpdate;
    private TimeProvider _timeProvider;
    private static final int ONE_DAY_IN_MS = 1000 * 60 * 60 * 24;
    private static final int ONE_WEEK_IN_MS = ONE_DAY_IN_MS * 7;

    public StandardUpdater(TimeProvider timeProvider) {
        _timeProvider = timeProvider;
        _lastUpdate = _timeProvider.getTimeNow();
    }

    public StandardUpdater(TimeProvider timeProvider, Calendar lastUpdate) {
        _timeProvider = timeProvider;
        _lastUpdate = lastUpdate;
    }

    @Override
    public void updateAll(Habit[] habits) {
        Calendar now = _timeProvider.getTimeNow();
        Calendar lastUpdate = _lastUpdate;
        while (lastUpdate.get(Calendar.YEAR) != now.get(Calendar.YEAR) ||
                lastUpdate.get(Calendar.WEEK_OF_YEAR) != now.get(Calendar.WEEK_OF_YEAR)) {
            lastUpdate.setTime(new Date(lastUpdate.getTime().getTime() + ONE_WEEK_IN_MS));
            for (Habit habit : habits) {
                habit.newWeek();
            }
        }
        lastUpdate = _lastUpdate;
        while (lastUpdate.get(Calendar.YEAR) != now.get(Calendar.YEAR) ||
                lastUpdate.get(Calendar.DAY_OF_YEAR) != now.get(Calendar.DAY_OF_YEAR)) {
            lastUpdate.setTime(new Date(lastUpdate.getTime().getTime() + ONE_DAY_IN_MS));
            for (Habit habit : habits) {
                habit.newDay();
            }
        }
        _lastUpdate = lastUpdate;
    }
}
