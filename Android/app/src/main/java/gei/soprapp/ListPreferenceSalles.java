package gei.soprapp;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Clément Baudouin on 10/01/2016.
 */
public class ListPreferenceSalles extends ListPreferenceCustom {

    public ListPreferenceSalles(Context context, AttributeSet attrs) {
        super(context, attrs, R.array.salles_array, Globals.sallesPreferenceKey);
    }
}
