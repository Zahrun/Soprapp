package gei.soprapp;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import gei.soprapp.entities.Reservations;

/**
 * Created by Clément Baudouin on 10/01/2016.
 */
public  class FragmentReservations extends FragmentAbstract {
    public static FragmentAbstract newInstance(int sectionNumber) {
        FragmentReservations fragment = new FragmentReservations();
        return FragmentAbstract.newInstance(fragment, sectionNumber, R.layout.fragment_reservations);
    }

    private String[] formatReservations(Reservations[] reservations){
        String[] reservationsStrings = new String[reservations.length];
        for (int i = 0; i < reservations.length; i++){
            Reservations current = reservations[i];
            String date = DateFormat.getDateFormat(getContext()).format(current.getStart());
            String heure = DateFormat.getTimeFormat(getContext()).format(current.getStart());
            reservationsStrings[i] = "Le " + date + " à " + heure + " salle: \"" + current.getRoomRef().getNumber()+"\"";
            reservationsStrings[i] += "\n\t\t capacité: " + current.getRoomRef().getCapacity() + " personnes";
            if (current.getInvitedUsersCollection() != null){
                reservationsStrings[i] += " (" + current.getInvitedUsersCollection().size()+" invitées)";
            } else {
                reservationsStrings[i] += " (" + 0 +" invitées)";
            }
        }
        return reservationsStrings;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ListView mListView = (ListView) view.findViewById(R.id.reservationsList);
        //1- la requête
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Reservations[] reservations = Requests.getReservationsCurrentUser(mListView);
                if (reservations==null) return;
                mListView.post(new Runnable() {
                    @Override
                    public void run() {
                        //2- affichage
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                                android.R.layout.simple_list_item_1, formatReservations(reservations));
                        mListView.setAdapter(adapter);
                    }
                });
            }
        }).start();



    }
}
