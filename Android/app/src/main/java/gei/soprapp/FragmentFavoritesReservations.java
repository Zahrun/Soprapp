package gei.soprapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Set;
import java.util.TreeSet;

import gei.soprapp.entities.Reservations;

/**
 * Created by Clément Baudouin on 10/01/2016.
 */
public class FragmentFavoritesReservations extends FragmentAbstract {

    private static String selected;

    public static void setSelected(String selected){
        FragmentFavoritesReservations.selected = selected;
    }


    public static FragmentAbstract newInstance(int sectionNumber) {
        FragmentFavoritesReservations fragment = new FragmentFavoritesReservations();
        return FragmentAbstract.newInstance(fragment, sectionNumber, R.layout.fragment_favorites_reservations);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextView textView = (TextView) view.findViewById(R.id.favoritesReservationsText);
        CharSequence text = textView.getText();
        textView.setText(text.subSequence(0, text.length() - 1) + " " + selected + ":");


        final ListView mListView = (ListView) view.findViewById(R.id.favoritesReservationsList);
        //1- la requête
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Reservations[] reservations = Requests.getReservationsOfRoom(mListView, FragmentFavoritesReservations.selected);
                if (reservations==null || reservations.length==0) return;
                if (mListView==null) return;
                mListView.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.append("\n\t\t\t(capacité: " + reservations[0].getRoomRef().getCapacity()+")");
                        //2- affichage
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                                android.R.layout.simple_list_item_1, Globals.formatReservations(reservations, getContext(),false));
                        mListView.setAdapter(adapter);
                    }
                });
            }
        }).start();

        // Pouvoir revenir en arrière
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    ((MainActivity.SectionsPagerAdapter)((ViewPager) v.getRootView().findViewById(R.id.container)).getAdapter()).switchFavoritesFragment();
                    return true;
                }
                return false;
            }
        });
    }
}
