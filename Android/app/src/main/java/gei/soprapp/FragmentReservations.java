package gei.soprapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.TreeSet;

import gei.soprapp.entities.Reservations;

/**
 * Created by Clément Baudouin on 10/01/2016.
 */
public  class FragmentReservations extends FragmentAbstract {
    public static FragmentAbstract newInstance(int sectionNumber) {
        FragmentReservations fragment = new FragmentReservations();
        return FragmentAbstract.newInstance(fragment, sectionNumber, R.layout.fragment_reservations);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ListView mListView = (ListView) view.findViewById(R.id.resultatResa);
        //1- la requête
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Reservations[] reservations = Requests.getReservationsCurrentUser(mListView);
                mListView.post(new Runnable() {
                    @Override
                    public void run() {
                        //2- affichage
                        ArrayAdapter<Reservations> adapter = new ArrayAdapter<Reservations>(getActivity(),
                                android.R.layout.simple_list_item_1, reservations);
                        mListView.setAdapter(adapter);
                    }
                });
            }
        }).start();



    }
}
