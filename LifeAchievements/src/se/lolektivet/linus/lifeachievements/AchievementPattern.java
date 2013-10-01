package se.lolektivet.linus.lifeachievements;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2013-09-13
 * Time: 23:37
 */
public class AchievementPattern implements Serializable
{
    private static final long serialVersionUID = 0x11FEC4EEB050004L;
    private List<Integer> _weeksOfStreakForEachLevel = new ArrayList<Integer>(4);
    private int _minOrMaxTimesPerWeek;

    public AchievementPattern(int weeksForLevel1, int weeksForLevel2, int weeksForLevel3, int weeksForLevel4, int minOrMaxTimesPerWeek) {
        if (weeksForLevel1 < 1 ||
                weeksForLevel1 > weeksForLevel2 ||
                weeksForLevel2 > weeksForLevel3 ||
                weeksForLevel3 > weeksForLevel4) {
            throw new RuntimeException("Nr of weeks must be same or higher for each consecutive level.");
        }
        _weeksOfStreakForEachLevel.add(weeksForLevel1);
        _weeksOfStreakForEachLevel.add(weeksForLevel2);
        _weeksOfStreakForEachLevel.add(weeksForLevel3);
        _weeksOfStreakForEachLevel.add(weeksForLevel4);
        _minOrMaxTimesPerWeek = minOrMaxTimesPerWeek;
    }

    public int getLevelForHabit(Habit habit) {
        int level = 0;
        if (habit.isPositive()) {
            for (Integer minWeeks : _weeksOfStreakForEachLevel) {
                if (habit.nrOfWeeksActivityHasBeenDone(_minOrMaxTimesPerWeek) >= minWeeks) {
                    level++;
                }
            }
        } else {
            for (Integer minWeeks : _weeksOfStreakForEachLevel) {
                if (habit.nrOfWeeksActivityHasBeenAvoided(_minOrMaxTimesPerWeek) >= minWeeks) {
                    level++;
                }
            }
        }
        return level;
    }
}
