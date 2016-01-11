package gei.soprapp.settings;

import android.content.Context;
import android.util.AttributeSet;

import gei.soprapp.Globals;
import gei.soprapp.R;

/**
 * Created by Clément Baudouin on 10/01/2016.
 */
public class MultiSelectListPreferenceSalles extends MultiSelectListPreferenceCustom {

    public MultiSelectListPreferenceSalles(Context context, AttributeSet attrs) {
        super(context, attrs, R.array.salles_array, Globals.CACHE_SALLES_KEY, Globals.sallesPreferenceKey);
    }
}
