package gei.soprapp.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;

import java.util.Set;
import java.util.TreeSet;

import gei.soprapp.Globals;

/**
 * Created by Clément Baudouin on 10/01/2016.
 */
public class ListPreferenceCustom extends ListPreference {
        public ListPreferenceCustom(Context context, AttributeSet attrs, int array, String stringSetKey) {
            super(context, attrs);
            String defaultItemsArray[] = context.getResources().getStringArray(array);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            Set<String> itemsSet = sharedPreferences.getStringSet(stringSetKey, Globals.arrayToSet(defaultItemsArray));
            this.setEntries(Globals.setToArray(itemsSet));
            this.setEntryValues(Globals.makePositionArray(itemsSet.size()));
        }
    }
