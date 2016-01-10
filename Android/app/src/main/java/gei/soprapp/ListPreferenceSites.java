package gei.soprapp;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Clément Baudouin on 10/01/2016.
 */
public class ListPreferenceSites extends ListPreferenceCustom {

    public ListPreferenceSites(Context context, AttributeSet attrs) {
        super(context, attrs, R.array.sites_array, Globals.listeSitesKey);
    }
}
