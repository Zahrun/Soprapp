package gei.soprapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
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
    Set<String> favoriteRoomsSet;
    Set<String> cacheRoomsSet;
    String[] favoriteRoomsArray;
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
        favoriteRoomsSet = sharedPreferences.getStringSet( Globals.PREF_FAVORITE_ROOMS, defaultItems);
        favoriteRoomsArray = new String[favoriteRoomsSet.size()];
        favoriteRoomsSet.toArray(favoriteRoomsArray);
        favoriteRoomsSet = sharedPreferences.getStringSet( Globals.PREF_FAVORITE_ROOMS, defaultItems);
        favoriteRoomsArray = new String[favoriteRoomsSet.size()];
        favoriteRoomsSet.toArray(favoriteRoomsArray);

        mListView = (ListView) view.findViewById(R.id.listFavorites);
        adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, favoriteRoomsArray);
        mListView.setAdapter(adapter);

        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        switch (key){
            case Globals.PREF_FAVORITE_ROOMS:
                favoriteRoomsSet = sharedPreferences.getStringSet( Globals.PREF_FAVORITE_ROOMS, defaultItems);
                favoriteRoomsArray = new String[favoriteRoomsSet.size()];
                favoriteRoomsSet.toArray(favoriteRoomsArray);

                mListView = (ListView) getActivity().findViewById(R.id.listFavorites);
                if(mListView==null) return;
                adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, favoriteRoomsArray);
                mListView.setAdapter(adapter);
                break;
            case Globals.CACHE_ROOMS_KEY:
                cacheRoomsSet = sharedPreferences.getStringSet( Globals.CACHE_ROOMS_KEY, defaultItems);
                for (String s : favoriteRoomsSet){
                    if (!cacheRoomsSet.contains(s)){
                        favoriteRoomsSet.remove(s);
                    }
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putStringSet(Globals.PREF_FAVORITE_ROOMS, favoriteRoomsSet).commit();

                favoriteRoomsArray = new String[favoriteRoomsSet.size()];
                favoriteRoomsSet.toArray(favoriteRoomsArray);
                mListView = (ListView) getActivity().findViewById(R.id.listFavorites);
                if(mListView==null) return;
                adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, favoriteRoomsArray);
                mListView.setAdapter(adapter);
                break;
            default:
                break;
        }
    }
}
