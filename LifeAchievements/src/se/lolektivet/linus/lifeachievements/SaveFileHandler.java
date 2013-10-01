package se.lolektivet.linus.lifeachievements;

import android.content.Context;
import android.util.Log;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2013-09-29
 * Time: 19:53
 */
public class SaveFileHandler
{
    private static final String HABIT_FILE_NAME = "habits.dat";
    private static final String LOG_TAG = "LifeAchievements";

    void writeHabitFile(Context context, Habits habits) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = context.openFileOutput(HABIT_FILE_NAME, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            habits.writeState(oos);
            Log.d(LOG_TAG, "Wrote habit file.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Couldn't write habit file!");
        } catch (IOException e) {
            throw new RuntimeException("Couldn't write habit file!");
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                } else if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                // ignored
            }
        }
    }

    Habits readHabitsFromFile(Context context) {
        Habits habits = new Habits();
        FileInputStream fis;
        try {
            fis = context.openFileInput(HABIT_FILE_NAME);
        } catch (FileNotFoundException e) {
            habits.initializeState();
            writeHabitFile(context, habits);
            return habits;
        }
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(fis);
            return habits.readState(ois);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return habits.initializeState();
        } catch (OptionalDataException e) {
            e.printStackTrace();
            return habits.initializeState();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
            return habits.initializeState();
        } catch (IOException e) {
            e.printStackTrace();
            return habits.initializeState();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                } else {
                    fis.close();
                }
            } catch (IOException e) {
                // ignored
            }
        }
    }

}
