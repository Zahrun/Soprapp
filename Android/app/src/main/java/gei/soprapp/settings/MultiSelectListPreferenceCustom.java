package gei.soprapp.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.MultiSelectListPreference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Clément Baudouin on 10/01/2016.
 */
public class MultiSelectListPreferenceCustom extends MultiSelectListPreference {
        public MultiSelectListPreferenceCustom(Context context, AttributeSet attrs, int array, String itemListKey, String preferenceKey) {
            super(context, attrs);
            String defaultItemsArray[] = context.getResources().getStringArray(array);
            TreeSet<String> defaultItems = new TreeSet<>();
            for (String site : defaultItemsArray)
            {
                defaultItems.add(site);
            }
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            Set<String> itemsSet = sharedPreferences.getStringSet(itemListKey, defaultItems);
            String[] items = new String[itemsSet.size()];
            itemsSet.toArray(items);
            this.setEntries(items);
            this.setEntryValues(items);
            Set<String> selectedItems = sharedPreferences.getStringSet(preferenceKey, new TreeSet<String>());
            this.setDefaultValue(selectedItems);
        }
    }
