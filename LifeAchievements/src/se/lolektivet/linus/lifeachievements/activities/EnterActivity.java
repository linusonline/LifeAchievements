package se.lolektivet.linus.lifeachievements.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import se.lolektivet.linus.lifeachievements.AlwaysNewDayUpdater;
import se.lolektivet.linus.lifeachievements.R;
import se.lolektivet.linus.lifeachievements.SaveFileHandler;
import se.lolektivet.linus.lifeachievements.core.*;

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

    private Map<HabitId, Integer> _buttonIdsForHabits = new HashMap<HabitId, Integer>(Habits.NR_OF_HABITS);
    private Map<HabitId, String> _enabledButtonTextsForHabits = new HashMap<HabitId, String>(Habits.NR_OF_HABITS);
    private Map<HabitId, String> _disabledButtonTextsForHabits = new HashMap<HabitId, String>(Habits.NR_OF_HABITS);
    private Map<HabitId, Integer> _labelIdsForHabits = new HashMap<HabitId, Integer>(Habits.NR_OF_HABITS);
    private Map<HabitId, Integer> _labelText1ForHabits = new HashMap<HabitId, Integer>(Habits.NR_OF_HABITS);
    private Map<HabitId, Integer> _labelText2ForHabits = new HashMap<HabitId, Integer>(Habits.NR_OF_HABITS);

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_activity);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(INSTANCE_STATE_KEY_HABITS)) {
                _habits = (Habits) savedInstanceState.getSerializable(INSTANCE_STATE_KEY_HABITS);
            }
        }
        _buttonIdsForHabits.put(HabitId.WORKOUT, R.id.button_workout);
        _enabledButtonTextsForHabits.put(HabitId.WORKOUT, getResources().getText(R.string.enter_workout_enabled).toString());
        _disabledButtonTextsForHabits.put(HabitId.WORKOUT, getResources().getText(R.string.enter_workout_disabled).toString());
        _labelIdsForHabits.put(HabitId.WORKOUT, R.id.label_workout);
        _labelText1ForHabits.put(HabitId.WORKOUT, R.string.workout_count_text_1);
        _labelText2ForHabits.put(HabitId.WORKOUT, R.string.workout_count_text_2);
    }

    public void enteredActivity(View button) {
        if (button.getId() == R.id.button_workout) {
            doActivityForHabit(HabitId.WORKOUT);
        }
    }

    private AlwaysNewDayUpdater _cheater = new AlwaysNewDayUpdater();
    public void newDayCheat(View button) {
        _habits.updateState(_cheater);
        refresh();
        ((Button)button).setText("New day (" + _cheater.getDayName() + ")");
    }

    private void doActivityForHabit(HabitId habitId) {
        _habits.getHabit(habitId).doActivity();
        if (_habits.getHabit(habitId).isMaxedOutToday()) {
            Button button = (Button) findViewById(_buttonIdsForHabits.get(habitId));
            button.setEnabled(false);
            button.setText(_disabledButtonTextsForHabits.get(habitId));
        }
        refreshLabel(habitId);
    }

    private void refresh() {
        _habits.updateState(new StandardUpdater(new TimeProviderImpl()));
        for (HabitId habitId : HabitId.values()) {
            refreshButton(habitId);
        }
    }

    private void refreshButton(HabitId habitId) {
        if (!_habits.getHabit(habitId).isMaxedOutToday()) {
            Button button = (Button) findViewById(_buttonIdsForHabits.get(habitId));
            button.setEnabled(true);
            button.setText(_enabledButtonTextsForHabits.get(habitId));
        }
        refreshLabel(habitId);
    }

    private void refreshLabel(HabitId habitId) {
        String text = getResources().getString(_labelText1ForHabits.get(habitId));
        text += " " + _habits.getHabit(habitId).nrOfTimesActivityWasDoneThisWeek() + " ";
        text += getResources().getString(_labelText2ForHabits.get(habitId));
        ((TextView) findViewById(_labelIdsForHabits.get(habitId))).setText(text);
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