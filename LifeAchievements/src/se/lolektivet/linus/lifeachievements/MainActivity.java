package se.lolektivet.linus.lifeachievements;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2013-10-01
 * Time: 20:09
 */
public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void goToMyCheevosView(View button) {
        Intent intent = new Intent(this, MyCheevosActivity.class);
        startActivity(intent);
    }

    public void goToEnterActivityView(View button) {
        Intent intent = new Intent(this, EnterActivity.class);
        startActivity(intent);
    }

    public void goToAllCheevosView(View button) {

    }
}
