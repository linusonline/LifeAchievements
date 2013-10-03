package se.lolektivet.linus.lifeachievements;

import se.lolektivet.linus.lifeachievements.core.Habit;
import se.lolektivet.linus.lifeachievements.core.Updater;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2013-10-03
 * Time: 19:23
 */
public class AlwaysNewDayUpdater implements Updater
{
    private static int _day = 0;

    @Override
    public void updateAll(Habit[] habits)
    {
        _day++;
        for (Habit habit : habits) {
            habit.newDay();
            if (_day >= 7) {
                habit.newWeek();
            }
        }
        if (_day >= 7) {
            _day = 0;
        }
    }
}
