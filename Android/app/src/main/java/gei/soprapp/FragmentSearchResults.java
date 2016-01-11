package gei.soprapp;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import java.sql.Time;
import java.util.Date;

import gei.soprapp.entities.RoomEquipments;
import gei.soprapp.entities.Sites;

/**
 * Created by Clément Baudouin on 10/01/2016.
 */
public class FragmentSearchResults extends FragmentAbstract {

    private static Sites siteRequest;
    private static Date dateRequest;
    private static Time timeRequest;
    private static int dureeRequest;
    private static int nbPersonnesRequest;
    private static RoomEquipments equipmentsRequest;

    public static void setRequest(Sites site, Date date, Time time, int duree, int nbPersonnes, RoomEquipments equipments){
        siteRequest = site;
        dateRequest = date;
        timeRequest = time;
        dureeRequest = duree;
        nbPersonnesRequest = nbPersonnes;
        equipmentsRequest = equipments;
    }

    public static FragmentAbstract newInstance(int sectionNumber) {
        FragmentSearchResults fragment = new FragmentSearchResults();
        return FragmentAbstract.newInstance(fragment, sectionNumber, R.layout.fragment_search_results);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new Thread(new Runnable() {
            @Override
            public void run() {
                final Sites[] sites = Requests.getSites(view);
                if (sites == null){
                    return;
                }
                final TextView textView = (TextView) view.findViewById(R.id.resultatText);
                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        String text = new String();
                        for (Sites site : sites) {
                            text += site.toString() + "\n";
                        }
                        textView.setText("Petit test de requete (sites):\n" + text);
                    }
                });
            }
        }).start();

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    ((MainActivity) getContext()).getmSectionsPagerAdapter().switchSearchFragment();
                    return true;
                }
                return false;
            }
        });
    }

}
