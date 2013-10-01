package se.lolektivet.linus.lifeachievements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2013-09-13
 * Time: 22:45
 * To change this template use File | Settings | File Templates.
 */
public class HabitTest
{
    @Test
    public void testNrOfWeeksActivityHasBeenDone() throws Exception
    {
        Habit habit = Habit.newPositiveHabit(1, 5);
        assertEquals(0, habit.nrOfWeeksActivityHasBeenDone(1));

        habit.doActivity();
        habit.newWeek();
        assertEquals(1, habit.nrOfWeeksActivityHasBeenDone(1));

        habit.doActivity();
        habit.newWeek();
        assertEquals(2, habit.nrOfWeeksActivityHasBeenDone(1));

        habit.doActivity();
        habit.newWeek();
        habit.doActivity();
        habit.newWeek();
        habit.doActivity();
        habit.newWeek();
        assertEquals(5, habit.nrOfWeeksActivityHasBeenDone(1));

        habit.doActivity();
        habit.newWeek();
        assertEquals(5, habit.nrOfWeeksActivityHasBeenDone(1));
    }

    @Test
    public void testNrOfWeeksActivityHasBeenAvoided() throws Exception
    {
        Habit habit = Habit.newNegativeHabit(5);
        assertEquals(0, habit.nrOfWeeksActivityHasBeenAvoided(1));
    }

    @Test
    public void testIsPositive() throws Exception
    {
        Habit habit = Habit.newNegativeHabit(1);
        assertTrue(!habit.isPositive());
        habit = Habit.newPositiveHabit(1, 1);
        assertTrue(habit.isPositive());
    }
}
