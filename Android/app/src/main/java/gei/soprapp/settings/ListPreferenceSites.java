package gei.soprapp.settings;

import android.content.Context;
import android.util.AttributeSet;

import gei.soprapp.Globals;
import gei.soprapp.R;

/**
 * Created by Clément Baudouin on 10/01/2016.
 */
public class ListPreferenceSites extends ListPreferenceCustom {

    public ListPreferenceSites(Context context, AttributeSet attrs) {
        super(context, attrs, R.array.sites_array, Globals.listeSitesKey);
    }
}
