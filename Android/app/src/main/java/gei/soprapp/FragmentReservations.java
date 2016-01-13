package gei.soprapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import gei.soprapp.entities.Reservations;

/**
 * Created by Clément Baudouin on 10/01/2016.
 */
public  class FragmentReservations extends FragmentAbstract {

    private View view;

    public static FragmentAbstract newInstance(int sectionNumber) {
        FragmentReservations fragment = new FragmentReservations();
        return FragmentAbstract.newInstance(fragment, sectionNumber, R.layout.fragment_reservations);
    }

    public void updateReservations(final Reservations[] reservations){
        if (view != null) {
            view.post(new Runnable() {
                @Override
                public void run() {
                    ListView mListView = (ListView) view.findViewById(R.id.reservationsList);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_list_item_1, Globals.formatReservations(reservations, getContext(), true));
                    mListView.setAdapter(adapter);
                }
            });
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view = view;

        final ListView mListView = (ListView) view.findViewById(R.id.reservationsList);
        Requests.setFragmentReservations(this);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Reservations[] reservations = Requests.getReservationsCurrentUser(mListView);
                        view.post(new Runnable() {
                            @Override
                            public void run() {
                                new AlertDialog.Builder(view.getContext())
                                        .setTitle("Vraiment supprimer ?")
                                        .setMessage("Voulez vous vraiment supprimer cette reservation ?")
                                        .setNegativeButton("Non", null)
                                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                final Reservations selected = reservations[position];
                                                new Thread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Requests.deleteReservation(view, selected);
                                                    }
                                                }).start();
                                            }
                                        })
                                        .show();
                            }
                        });
                    }
                }).start();

            }
        });
    }
}
