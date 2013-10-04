package se.lolektivet.linus.lifeachievements.core;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2013-10-05
 * Time: 00:13
 */
public enum HabitId
{
    WORKOUT(0);

    private final int _n;

    HabitId(int n) {
        _n = n;
    }

    public int n() {
        return _n;
    }
}
