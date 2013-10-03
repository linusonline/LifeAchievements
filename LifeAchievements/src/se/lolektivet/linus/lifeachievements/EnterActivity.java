package se.lolektivet.linus.lifeachievements;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2013-10-01
 * Time: 20:39
 */
public class EnterActivity extends Activity
{
    private Habits _habits = null;

    private static final String INSTANCE_STATE_KEY_HABITS = "Habits";

    private Map<Integer, Integer> _buttonIdsForHabits = new HashMap<Integer, Integer>(Habits.NR_OF_HABITS);

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_activity);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(INSTANCE_STATE_KEY_HABITS)) {
                _habits = (Habits) savedInstanceState.getSerializable(INSTANCE_STATE_KEY_HABITS);
            }
        }
        _buttonIdsForHabits.put(Habits.HABIT_WORKOUT, R.id.button_workout);
    }

    public void enteredActivity(View button) {
        if (button.getId() == R.id.button_workout) {
            doActivityForHabit(Habits.HABIT_WORKOUT);
        }
    }

    public void newDayCheat(View button) {
        _habits.updateState(new AlwaysNewDayUpdater());
        refresh();
    }

    private void doActivityForHabit(int habitId) {
        _habits.getHabit(habitId).doActivity();
        if (_habits.getHabit(habitId).isMaxedOutToday()) {
            findViewById(_buttonIdsForHabits.get(habitId)).setEnabled(false);
        }
    }

    private void refresh() {
        _habits.updateState(new StandardUpdater(new TimeProviderImpl()));
        if (!_habits.getHabit(Habits.HABIT_WORKOUT).isMaxedOutToday()) {
            findViewById(R.id.button_workout).setEnabled(true);
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        new SaveFileHandler().writeHabitFile(this, _habits);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putSerializable(INSTANCE_STATE_KEY_HABITS, _habits);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        // TODO: Show GUI disabled, then read state, then enable.
        if (_habits == null) {
            _habits = new SaveFileHandler().readHabitsFromFile(this);
        }
        refresh();
    }
}