package se.lolektivet.linus.lifeachievements;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2013-09-29
 * Time: 19:30
 * To change this template use File | Settings | File Templates.
 */
public interface TimeProvider
{
    int getYear();

    int getWeekOfYear();

    Calendar getTimeNow();
}
