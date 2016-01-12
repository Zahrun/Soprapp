package gei.soprapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Clément Baudouin on 10/01/2016.
 */
public class FragmentFavorites extends FragmentAbstract implements SharedPreferences.OnSharedPreferenceChangeListener{

    SharedPreferences sharedPreferences ;
    TreeSet<String> defaultItems ;
    Set<String> itemsSet ;
    String[] items ;
    ListView mListView ;
    ArrayAdapter<String> adapter;


    public static FragmentAbstract newInstance(int sectionNumber) {
        FragmentFavorites fragment = new FragmentFavorites();
        return FragmentAbstract.newInstance(fragment, sectionNumber, R.layout.fragment_favorites);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        defaultItems = new TreeSet<>();
<<<<<<< HEAD
        itemsSet = sharedPreferences.getStringSet( Globals.PREF_FAVORITE_SALLE, defaultItems);
=======
        itemsSet = sharedPreferences.getStringSet( Globals.CACHE_SALLES_KEY, defaultItems);
>>>>>>> origin/master
        items = new String[itemsSet.size()];
        itemsSet.toArray(items);

        mListView = (ListView) view.findViewById(R.id.listFavorites);
        adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, items);
        mListView.setAdapter(adapter);

        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        defaultItems = new TreeSet<>();
        itemsSet = sharedPreferences.getStringSet( Globals.PREF_FAVORITE_SALLE, defaultItems);
        items = new String[itemsSet.size()];
        itemsSet.toArray(items);

        mListView = (ListView) getView().findViewById(R.id.listFavorites);
        adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, items);
        mListView.setAdapter(adapter);
    }
}
