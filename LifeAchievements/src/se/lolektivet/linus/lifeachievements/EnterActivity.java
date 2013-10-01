package se.lolektivet.linus.lifeachievements;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

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

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_activity);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(INSTANCE_STATE_KEY_HABITS)) {
                _habits = (Habits) savedInstanceState.getSerializable(INSTANCE_STATE_KEY_HABITS);
            }
        }
    }

    public void enteredActivity(View button) {
        if (button.getId() == R.id.button_workout) {
            _habits.getHabit(Habits.HABIT_WORKOUT).doActivity();
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
        _habits.updateState(new Updater(new TimeProviderImpl()));
    }
}