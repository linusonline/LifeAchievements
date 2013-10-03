package se.lolektivet.linus.lifeachievements.core;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2013-09-22
 * Time: 11:02
 */
public class Badge implements Serializable
{
    private static final long serialVersionUID = 0x11FEC4EEB050002L;
    private AchievementPattern _achievementPattern;
    private Habit _habit;
    private int _badgeResources[] = new int[4];

    public Badge(AchievementPattern achievementPattern, Habit habit, int earthImage, int woodImage, int rockImage, int diamondImage)
    {
        _achievementPattern = achievementPattern;
        _habit = habit;
        _badgeResources[0] = earthImage;
        _badgeResources[1] = woodImage;
        _badgeResources[2] = rockImage;
        _badgeResources[3] = diamondImage;
    }

    public int getImageResource() {
        int level = _achievementPattern.getLevelForHabit(_habit);
        if (level < 1 || level > 4) {
            return 0;
        }
        return _badgeResources[level - 1];
    }
}
