package gei.soprapp;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import gei.soprapp.entities.Equipments;
import gei.soprapp.entities.RoomEquipments;
import gei.soprapp.entities.Sites;

/**
 * Created by Clément Baudouin on 10/01/2016.
 */
public class FragmentSearchResults extends FragmentAbstract {

    private static String siteRequest = "";
    private static long dateRequest = Globals.getDate();
    private static long timeRequest = Globals.getTime();
    private static int dureeRequest = 0;
    private static int nbPersonnesRequest = 0;
    private static String[] equipmentsRequest = new String[0];

    public static void setRequest(String site, int duree, int nbPersonnes){
        siteRequest = site;
        dureeRequest = duree;
        nbPersonnesRequest = nbPersonnes;
    }

    public static void setRequestEquipments(String[] equipments){
        FragmentSearchResults.equipmentsRequest=equipments;
    }

    public static void setRequestTime(long time) {
        FragmentSearchResults.timeRequest = time;
    }

    public static void setRequestDate(long date) {
        FragmentSearchResults.dateRequest = date;
    }


    public static long getRequestDate() {
        return FragmentSearchResults.dateRequest;
    }
    public static long getRequestBeginning() {
        return FragmentSearchResults.dateRequest+FragmentSearchResults.timeRequest;
    }

    public static FragmentAbstract newInstance(int sectionNumber) {
        FragmentSearchResults fragment = new FragmentSearchResults();
        return FragmentAbstract.newInstance(fragment, sectionNumber, R.layout.fragment_search_results);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextView textView = (TextView) view.findViewById(R.id.resultatText);

        textView.post(new Runnable() {
            @Override
            public void run() {
                String text = new String();
                text += "site " + siteRequest + "\n";
                text += "date " + dateRequest + "\n";
                text += "heure " + timeRequest + "\n";
                Date date = new Date(dateRequest+timeRequest);
                text += "debut(date+heure) " + date + "\n";
                text += "duree " + dureeRequest + " (min)\n";
                text += "nbPerssonnes " + nbPersonnesRequest + "\n";
                for (String s : equipmentsRequest) {
                    text += "equipement " + s.toString() + "\n";
                }
                textView.setText("Arguments de la requete:\n"+text);
            }
        });

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                final Sites[] sites = Requests.getSites(view);
                if (sites == null){
                    return;
                }
                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        String text = new String();
                        for (Sites site : sites) {
                            text += site.toString() + "\n";
                        }
                        textView.append("Petit test de requete (sites):\n" + text);
                    }
                });
            }
        }).start();*/

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
