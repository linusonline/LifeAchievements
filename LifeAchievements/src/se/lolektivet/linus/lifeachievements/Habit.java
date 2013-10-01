package se.lolektivet.linus.lifeachievements;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Habit implements Serializable
{
    private static final long serialVersionUID = 0x11FEC4EEB050005L;
    private final int MAX_WEEKS;
    private final int NEWEST_WEEK;
    private static final int OLDEST_WEEK = 0;

    private List<Integer> _nrOfTimesActivityWasDoneLastXWeeks;
    private int _nrOfTimesActivityWasDoneThisWeek;
    private final int MAX_TIMES_PER_DAY;

    public static Habit newPositiveHabit(int maxTimesPerDay, int maxWeeks) {
        if (maxTimesPerDay < 1) {
            throw new IllegalArgumentException();
        }
        return new Habit(maxTimesPerDay, maxWeeks, false);
    }

    public static Habit newNegativeHabit(int maxWeeks) {
        return new Habit(0, maxWeeks, true);
    }

    private Habit(int maxTimesPerDay, int maxWeeks, boolean negativeHabit) {
        MAX_WEEKS = maxWeeks;
        NEWEST_WEEK = MAX_WEEKS - 1;
        MAX_TIMES_PER_DAY = maxTimesPerDay;
        _nrOfTimesActivityWasDoneLastXWeeks = new ArrayList<Integer>(MAX_WEEKS);
        _nrOfTimesActivityWasDoneThisWeek = negativeHabit ? 1000 : 0;
        for (int i = 0; i < MAX_WEEKS; i++) {
            _nrOfTimesActivityWasDoneLastXWeeks.add(negativeHabit ? 1000 : 0);
        }
    }

    public int nrOfWeeksActivityHasBeenAvoided(int maxNrOfTimesPerWeek) {
        if (MAX_TIMES_PER_DAY != 0) {
            throw new RuntimeException("nrOfWeeksActivityHasBeenAvoided can only be called for negative habits!");
        }
        int nrOfWeeks = 0;
        int week = NEWEST_WEEK;
        while (week >= OLDEST_WEEK && _nrOfTimesActivityWasDoneLastXWeeks.get(week--) <= maxNrOfTimesPerWeek) {
            nrOfWeeks += 1;
        }
        return nrOfWeeks;
    }

    public int nrOfWeeksActivityHasBeenDone(int leastNrOfTimesPerWeek) {
        if (MAX_TIMES_PER_DAY == 0) {
            throw new RuntimeException("nrOfWeeksActivityHasBeenDone can only be called for positive habits!");
        }
        int nrOfWeeks = 0;
        int week = NEWEST_WEEK;
        while (week >= OLDEST_WEEK && _nrOfTimesActivityWasDoneLastXWeeks.get(week--) >= leastNrOfTimesPerWeek) {
            nrOfWeeks += 1;
        }
        return nrOfWeeks;
    }

    public void doActivity() {
        _nrOfTimesActivityWasDoneThisWeek++;
        // _nrOfTimesActivityWasDoneLastXWeeks.set(NEWEST_WEEK, _nrOfTimesActivityWasDoneLastXWeeks.get(NEWEST_WEEK) + 1);
    }

    public void newWeek() {
        _nrOfTimesActivityWasDoneLastXWeeks.remove(OLDEST_WEEK);
        _nrOfTimesActivityWasDoneLastXWeeks.add(_nrOfTimesActivityWasDoneThisWeek);
        _nrOfTimesActivityWasDoneThisWeek = 0;
    }

    public boolean isPositive() {
        return MAX_TIMES_PER_DAY != 0;
    }
}