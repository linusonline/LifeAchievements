package se.lolektivet.linus.lifeachievements.core;

import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2013-09-29
 * Time: 19:53
 */
public class Habits implements Serializable
{
    private static final long serialVersionUID = 0x11FEC4EEB050001L;
    private static final String LOG_TAG = "LifeAchievements";

    private Habit _allHabits[];
    public static final int HABIT_WORKOUT = 0;
    public static final int NR_OF_HABITS = 1;

    public Habits() {
    }

    public Habits readState(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        _allHabits = (Habit[]) stream.readObject();
        Log.d(LOG_TAG, "Loaded habits from file.");
        return this;
    }

    public void writeState(ObjectOutputStream stream) throws IOException {
        stream.writeObject(_allHabits);
    }

    public Habits initializeState() {
        _allHabits = new Habit[NR_OF_HABITS];
        _allHabits[HABIT_WORKOUT] = Habit.newPositiveHabit(1, 12);
        Log.d(LOG_TAG, "Created new habits.");
        // simulateHabits();
        return this;
    }

    public Habit getHabit(int habit) {
        return _allHabits[habit];
    }

    public Habit[] getAllHabits() {
        return _allHabits;
    }

    public void updateState(Updater updater) {
        updater.updateAll(_allHabits);
    }

    private void simulateHabits() {
        _allHabits[HABIT_WORKOUT].doActivity();
        _allHabits[HABIT_WORKOUT].newDay();
        _allHabits[HABIT_WORKOUT].doActivity();
        _allHabits[HABIT_WORKOUT].newDay();
        _allHabits[HABIT_WORKOUT].doActivity();
        _allHabits[HABIT_WORKOUT].newDay();
        _allHabits[HABIT_WORKOUT].newWeek();
        _allHabits[HABIT_WORKOUT].doActivity();
        _allHabits[HABIT_WORKOUT].newDay();
        _allHabits[HABIT_WORKOUT].doActivity();
        _allHabits[HABIT_WORKOUT].newDay();
        _allHabits[HABIT_WORKOUT].doActivity();
        _allHabits[HABIT_WORKOUT].newDay();
        _allHabits[HABIT_WORKOUT].newWeek();
        _allHabits[HABIT_WORKOUT].doActivity();
        _allHabits[HABIT_WORKOUT].newDay();
        _allHabits[HABIT_WORKOUT].doActivity();
        _allHabits[HABIT_WORKOUT].newDay();
        _allHabits[HABIT_WORKOUT].doActivity();
        _allHabits[HABIT_WORKOUT].newDay();
        _allHabits[HABIT_WORKOUT].newWeek();
        _allHabits[HABIT_WORKOUT].doActivity();
        _allHabits[HABIT_WORKOUT].newDay();
        _allHabits[HABIT_WORKOUT].doActivity();
        _allHabits[HABIT_WORKOUT].newDay();
        _allHabits[HABIT_WORKOUT].doActivity();
        _allHabits[HABIT_WORKOUT].newDay();
        _allHabits[HABIT_WORKOUT].newWeek();
        _allHabits[HABIT_WORKOUT].doActivity();
        _allHabits[HABIT_WORKOUT].newDay();
        _allHabits[HABIT_WORKOUT].doActivity();
        _allHabits[HABIT_WORKOUT].newDay();
        _allHabits[HABIT_WORKOUT].doActivity();
        _allHabits[HABIT_WORKOUT].newDay();
        _allHabits[HABIT_WORKOUT].newWeek();
        _allHabits[HABIT_WORKOUT].doActivity();
        _allHabits[HABIT_WORKOUT].newDay();
        _allHabits[HABIT_WORKOUT].doActivity();
        _allHabits[HABIT_WORKOUT].newDay();
        _allHabits[HABIT_WORKOUT].doActivity();
        _allHabits[HABIT_WORKOUT].newDay();
        _allHabits[HABIT_WORKOUT].newWeek();
    }
}