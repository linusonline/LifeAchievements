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
        for (Habit habit : habits) {
            habit.newDay();
        }
        _day++;

        if (_day >= 7) {
            _day = 0;
            for (Habit habit : habits) {
                habit.newWeek();
            }
        }
    }

    public String getDayName() {
        switch (_day) {
            case 0:
                return "Monday";
            case 1:
                return "Tuesday";
            case 2:
                return "Wednesday";
            case 3:
                return "Thursday";
            case 4:
                return "Friday";
            case 5:
                return "Saturday";
            case 6:
                return "Sunday";
            default:
                return "Errorday";
        }
    }
}
