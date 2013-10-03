package se.lolektivet.linus.lifeachievements;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import se.lolektivet.linus.lifeachievements.core.AchievementPattern;
import se.lolektivet.linus.lifeachievements.core.Habit;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2013-09-14
 * Time: 00:02
 */
public class AchievementPatternTest
{
    @Test
    public void testGetLevelForHabitAndFrequency() throws Exception
    {
        AchievementPattern achievementPattern1 = new AchievementPattern(2, 4, 8, 12, 2);
        Habit trainingHabit = Habit.newPositiveHabit(1, 12);
        assertEquals(0, achievementPattern1.getLevelForHabit(trainingHabit));

        trainingHabit.doActivity();
        assertEquals(0, achievementPattern1.getLevelForHabit(trainingHabit));

        trainingHabit.doActivity();
        assertEquals(0, achievementPattern1.getLevelForHabit(trainingHabit));

        trainingHabit.newWeek();
        assertEquals(0, achievementPattern1.getLevelForHabit(trainingHabit));

        trainingHabit.doActivity();
        trainingHabit.doActivity();
        assertEquals(0, achievementPattern1.getLevelForHabit(trainingHabit));

        trainingHabit.newWeek();
        assertEquals(1, achievementPattern1.getLevelForHabit(trainingHabit));

        trainingHabit.doActivity();
        trainingHabit.doActivity();
        trainingHabit.newWeek();
        trainingHabit.doActivity();
        trainingHabit.doActivity();
        trainingHabit.newWeek();
        assertEquals(2, achievementPattern1.getLevelForHabit(trainingHabit));

    }
}
