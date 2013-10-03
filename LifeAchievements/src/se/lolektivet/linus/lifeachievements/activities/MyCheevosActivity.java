package se.lolektivet.linus.lifeachievements.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import se.lolektivet.linus.lifeachievements.R;
import se.lolektivet.linus.lifeachievements.SaveFileHandler;
import se.lolektivet.linus.lifeachievements.core.*;

import java.util.ArrayList;
import java.util.List;

public class MyCheevosActivity extends Activity {

    private static final String INSTANCE_STATE_KEY_HABITS = "Habits";
    private static final String INSTANCE_STATE_KEY_BADGES = "Badges";

    private Habits _habits = null;
    private Badges _badges = null;

    public void enteredActivity(View button) {
        if (button.getId() == R.id.button_workout) {
            _habits.getHabit(Habits.HABIT_WORKOUT).doActivity();
        }
    }

    private Badge[] getUnlockedBadges() {
        List<Badge> unlockedBadges = new ArrayList<Badge>();
        for (Badge badge : _badges.getAllBadges()) {
            if (badge.getImageResource() != 0) {
                unlockedBadges.add(badge);
            }
        }
        return unlockedBadges.toArray(new Badge[unlockedBadges.size()]);
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.single_cheevo_image_view);
//        ImageView cheevo = (ImageView) findViewById(R.id.cheevo);
//        cheevo.setImageResource(R.drawable.workout_2_earth);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(INSTANCE_STATE_KEY_HABITS)) {
                _habits = (Habits) savedInstanceState.getSerializable(INSTANCE_STATE_KEY_HABITS);
            }
            if (savedInstanceState.containsKey(INSTANCE_STATE_KEY_BADGES)) {
                _badges = (Badges) savedInstanceState.getSerializable(INSTANCE_STATE_KEY_BADGES);
            }
        }

        setContentView(R.layout.cheevos);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        if (_habits == null) {
            _habits = new SaveFileHandler().readHabitsFromFile(this);
        }
        _habits.updateState(new StandardUpdater(new TimeProviderImpl()));
        //_habits.initializeState();
        if (_badges == null) {
            _badges = new Badges(_habits);
        }
        ArrayAdapter badgeCreator = new BadgeArrayAdapter(this, R.layout.cheevo_image_view, getUnlockedBadges());
        GridView badgeGrid = (GridView) findViewById(R.id.cheevos);
        badgeGrid.setAdapter(badgeCreator);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putSerializable(INSTANCE_STATE_KEY_HABITS, _habits);
        outState.putSerializable(INSTANCE_STATE_KEY_BADGES, _badges);
    }
}
