package gei.soprapp;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Clément Baudouin on 10/01/2016.
 */
public class MultiSelectListPreferenceSalles extends MultiSelectListPreferenceCustom {

    public MultiSelectListPreferenceSalles(Context context, AttributeSet attrs) {
        super(context, attrs, R.array.salles_array, Globals.listeSallesKey, Globals.sallesPreferenceKey);
    }
}
