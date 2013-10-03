package se.lolektivet.linus.lifeachievements.activities;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import se.lolektivet.linus.lifeachievements.core.Badge;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2013-09-22
 * Time: 10:02
 */
public class BadgeArrayAdapter extends ArrayAdapter<Badge>
{
    private int _resourceId;
    private LayoutInflater _inflater;

    public BadgeArrayAdapter(Context context, int resource, Badge[] objects)
    {
        super(context, resource, objects);
        _resourceId = resource;
        _inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view;
        ImageView imageView;

        if (convertView == null) {
            view = _inflater.inflate(_resourceId, parent, false);
        } else {
            view = convertView;
        }

        try {
            imageView = (ImageView) view;
        } catch (ClassCastException e) {
            Log.e("BadgeArrayAdapter", "You must supply a resource ID for an ImageView");
            throw new IllegalStateException(
                    "BadgeArrayAdapter requires the resource ID to be an ImageView", e);
        }

        Badge item = getItem(position);
        imageView.setImageResource(item.getImageResource());

        return view;
    }
}
