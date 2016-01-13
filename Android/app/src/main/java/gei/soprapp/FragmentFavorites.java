package gei.soprapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Clément Baudouin on 10/01/2016.
 */
public class FragmentFavorites extends FragmentAbstract implements SharedPreferences.OnSharedPreferenceChangeListener{

    SharedPreferences sharedPreferences ;

    public static FragmentAbstract newInstance(int sectionNumber) {
        FragmentFavorites fragment = new FragmentFavorites();
        return FragmentAbstract.newInstance(fragment, sectionNumber, R.layout.fragment_favorites);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        Set<String> favoriteRoomsSet = sharedPreferences.getStringSet( Globals.PREF_FAVORITE_ROOMS, new TreeSet<String>());
        final String favoriteRoomsArray[] = new String[favoriteRoomsSet.size()];
        favoriteRoomsSet.toArray(favoriteRoomsArray);

        final ListView mListView = (ListView) view.findViewById(R.id.listFavorites);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, favoriteRoomsArray);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = favoriteRoomsArray[position];
                FragmentFavoritesReservations.setSelected(selected);
                ((MainActivity.SectionsPagerAdapter)((ViewPager) view.getRootView().findViewById(R.id.container)).getAdapter()).switchFavoritesFragment();
            }
        });

        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroyView (){
        super.onDestroyView();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Set<String> favoriteRoomsSet;
        String favoriteRoomsArray[];
        switch (key){
            case Globals.PREF_FAVORITE_ROOMS:
                favoriteRoomsSet = sharedPreferences.getStringSet( Globals.PREF_FAVORITE_ROOMS, new TreeSet<String>());
                favoriteRoomsArray = new String[favoriteRoomsSet.size()];
                favoriteRoomsSet.toArray(favoriteRoomsArray);

                ListView mListView = (ListView) getActivity().findViewById(R.id.listFavorites);
                if(mListView==null) return;
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                        android.R.layout.simple_list_item_1, favoriteRoomsArray);
                mListView.setAdapter(adapter);
                break;
            case Globals.CACHE_ROOMS_KEY:
                Set<String> cacheRoomsSet = sharedPreferences.getStringSet( Globals.CACHE_ROOMS_KEY, new TreeSet<String>());
                favoriteRoomsSet = sharedPreferences.getStringSet( Globals.PREF_FAVORITE_ROOMS, new TreeSet<String>());
                Set<String> favoriteRoomsSetCopy = new TreeSet<>(favoriteRoomsSet);
                for (String s : favoriteRoomsSet){
                    if (!cacheRoomsSet.contains(s)){
                        favoriteRoomsSetCopy.remove(s);
                    }
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putStringSet(Globals.PREF_FAVORITE_ROOMS, favoriteRoomsSetCopy).commit();

                mListView = (ListView) getActivity().findViewById(R.id.listFavorites);
                if(mListView==null) return;
                adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, Globals.setToArray(favoriteRoomsSetCopy));
                mListView.setAdapter(adapter);
                break;
            default:
                break;
        }
    }
}
