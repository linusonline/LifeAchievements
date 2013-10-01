package se.lolektivet.linus.lifeachievements;

import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2013-09-22
 * Time: 20:55
 */
public class Updater
{
    private Calendar _lastUpdate;
    private TimeProvider _timeProvider;
    private static final int ONE_WEEK_IN_MS = 1000 * 60 * 60 * 24 * 7;

    public Updater(TimeProvider timeProvider) {
        _timeProvider = timeProvider;
        _lastUpdate = _timeProvider.getTimeNow();
    }

    public Updater(TimeProvider timeProvider, Calendar lastUpdate) {
        _timeProvider = timeProvider;
        _lastUpdate = lastUpdate;
    }

    public void updateAll(Habit[] habits) {
        Calendar now = _timeProvider.getTimeNow();
        while (_lastUpdate.get(Calendar.YEAR) != now.get(Calendar.YEAR) ||
                _lastUpdate.get(Calendar.WEEK_OF_YEAR) != now.get(Calendar.WEEK_OF_YEAR)) {
            _lastUpdate.setTime(new Date(_lastUpdate.getTime().getTime() + ONE_WEEK_IN_MS));
            for (Habit habit : habits) {
                habit.newWeek();
            }
        }
    }
}
