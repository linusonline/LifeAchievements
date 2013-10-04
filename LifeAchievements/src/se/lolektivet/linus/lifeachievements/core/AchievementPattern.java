package se.lolektivet.linus.lifeachievements.core;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2013-09-13
 * Time: 23:37
 */
public class AchievementPattern implements Serializable
{
    private static final long serialVersionUID = 0x11FEC4EEB050004L;
    private Integer _weeksOfStreakForEachLevel[] = new Integer[4];
    private int _minOrMaxTimesPerWeek;

    public AchievementPattern(int weeksForLevel1, int weeksForLevel2, int weeksForLevel3, int weeksForLevel4, int minOrMaxTimesPerWeek) {
        if (weeksForLevel1 < 1 ||
                weeksForLevel1 > weeksForLevel2 ||
                weeksForLevel2 > weeksForLevel3 ||
                weeksForLevel3 > weeksForLevel4) {
            throw new RuntimeException("Nr of weeks must be same or higher for each consecutive level.");
        }
        _weeksOfStreakForEachLevel[0] = weeksForLevel1;
        _weeksOfStreakForEachLevel[1] = weeksForLevel2;
        _weeksOfStreakForEachLevel[2] = weeksForLevel3;
        _weeksOfStreakForEachLevel[3] = weeksForLevel4;
        _minOrMaxTimesPerWeek = minOrMaxTimesPerWeek;
    }

    public int getLevelForHabit(Habit habit) {
        int weeksOfStreak;
        if (habit.isPositive()) {
            weeksOfStreak = habit.nrOfWeeksActivityHasBeenDone(_minOrMaxTimesPerWeek);
        } else {
            weeksOfStreak = habit.nrOfWeeksActivityHasBeenAvoided(_minOrMaxTimesPerWeek);
        }

        int level = 0;
        while (level < 4 && weeksOfStreak >= _weeksOfStreakForEachLevel[level]) {
            level++;
        }
        return level;
    }
}
