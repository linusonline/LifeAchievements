package se.lolektivet.linus.lifeachievements.core;

import se.lolektivet.linus.lifeachievements.R;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2013-10-01
 * Time: 20:00
 */
public class Badges implements Serializable
{
    private static final long serialVersionUID = 0x11FEC4EEB050003L;
    private Badge _allBadges[];

    public static final int BADGE_WORKOUT_2 = 0;
    public static final int BADGE_WORKOUT_3 = 1;
    public static final int BADGE_WORKOUT_4 = 2;
    public static final int BADGE_WORKOUT_5 = 3;
    public static final int NR_OF_BADGES = 4;

    public Badges(Habits habits) {
        _allBadges = new Badge[NR_OF_BADGES];

        // Create badges from achievements and habits.
        AchievementPattern workoutAchievement2Pattern = new AchievementPattern(2, 4, 8, 12, 2);
        _allBadges[BADGE_WORKOUT_2] = new Badge(workoutAchievement2Pattern, habits.getHabit(Habits.HABIT_WORKOUT),
                R.drawable.workout_2_earth,
                R.drawable.workout_2_wood,
                R.drawable.workout_2_rock,
                R.drawable.workout_2_diamond);

        AchievementPattern workoutAchievement3Pattern = new AchievementPattern(2, 4, 6, 10, 3);
        _allBadges[BADGE_WORKOUT_3] = new Badge(workoutAchievement3Pattern, habits.getHabit(Habits.HABIT_WORKOUT),
                R.drawable.workout_3_earth,
                R.drawable.workout_3_wood,
                R.drawable.workout_3_rock,
                R.drawable.workout_3_diamond);

        AchievementPattern workoutAchievement4Pattern = new AchievementPattern(1, 2, 4, 6, 4);
        _allBadges[BADGE_WORKOUT_4] = new Badge(workoutAchievement4Pattern, habits.getHabit(Habits.HABIT_WORKOUT),
                R.drawable.workout_4_earth,
                R.drawable.workout_4_wood,
                R.drawable.workout_4_rock,
                R.drawable.workout_4_diamond);

        AchievementPattern workoutAchievement5Pattern = new AchievementPattern(1, 2, 3, 5, 5);
        _allBadges[BADGE_WORKOUT_5] = new Badge(workoutAchievement5Pattern, habits.getHabit(Habits.HABIT_WORKOUT),
                R.drawable.workout_5_earth,
                R.drawable.workout_5_wood,
                R.drawable.workout_5_rock,
                R.drawable.workout_5_diamond);

    }

    public Badge getBadge(int badge) {
        return _allBadges[badge];
    }

    public Badge[] getAllBadges() {
        return _allBadges;
    }

}
