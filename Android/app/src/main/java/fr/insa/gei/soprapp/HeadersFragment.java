package fr.insa.gei.soprapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;


public class HeadersFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String settings = getArguments().getString("header");
        if ("sites".equals(settings)) {
            addPreferencesFromResource(R.xml.default_site);
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
            updatePreference(getPreferenceScreen().findPreference("listSites"),"Sopra Colo 1");
            //nécessaire d'aller chercher la valeur actuelle ?
        } else if ("rooms".equals(settings)) {
            addPreferencesFromResource(R.xml.fav_rooms);
        }

    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        updatePreference(findPreference(key), key);
    }

    private void updatePreference(Preference preference, String key) {
        if (preference == null) return;
        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            listPreference.setTitle(listPreference.getEntry());
            return;
        }
        SharedPreferences sharedPrefs = getPreferenceManager().getSharedPreferences();
        preference.setSummary(sharedPrefs.getString(key, "Default"));
    }
}
