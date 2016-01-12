package gei.soprapp;

import android.content.Context;
import android.text.format.DateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import gei.soprapp.entities.Reservations;
import gei.soprapp.entities.Sites;

/**
 * Created by Clément Baudouin on 10/01/2016.
 */
public class Globals {
    // Cache
    public final static String CACHE_EQUIPMENTS_KEY = "cache_equipments";
    public final static String CACHE_SITES_KEY = "cache_sites";
    public static final String CACHE_ROOMS_KEY = "cache_rooms";
    // Préférences
    public final static String PREF_FAVORITE_ROOMS = "pref_key_salles";
    public final static String PREF_FAVORITE_SITE = "pref_key_site";
    //Valeurs par defaut
    public final static int DEFAULT_POSITION = 0;
    // Réseau
    public final static String BASE_URI="http://176.31.1.146:8080/webapp";
    public final static String REST_URI=BASE_URI+"/rest/";
    public static final long INTERVALLE_MISE_A_JOUR = 1000;

    public static <T> Set<T> arrayToSet(T[] array){
        TreeSet<T> set = new TreeSet<>();
        for (T current : array)
        {
            set.add(current);
        }
        return set;
    }

    public static String[] setToArray(Set<String> set){
        String[] array = new String[set.size()];
        set.toArray(array);
        return array;
    }

    public static int findPositionInArray(String favoriteSite, String[] sitesArray) {
        for (int i = 0; i<sitesArray.length; i++){
            if(sitesArray[i].equals(favoriteSite))
                return i;
        }
        return DEFAULT_POSITION;
    }

    public static String[] listToArray(List<String> list) {
        String result[] = new String[list.size()];
        list.toArray(result);
        return result;
    }

    public static long getDate() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int monthOfYear = c.get(Calendar.MONTH);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        return new GregorianCalendar(year,monthOfYear,dayOfMonth).getTimeInMillis();
    }

    public static long getTime() {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);
        return hour*60*60*1000+minutes*60*1000;
    }

    public static String[] formatReservations(Reservations[] reservations, Context context, boolean printSalle){
        String[] reservationsStrings = new String[reservations.length];
        for (int i = 0; i < reservations.length; i++){
            Reservations current = reservations[i];
            String date = DateFormat.getDateFormat(context).format(current.getStart());
            String heure = DateFormat.getTimeFormat(context).format(current.getStart());
            reservationsStrings[i] = "Le " + date + " à " + heure;
            if (printSalle) {
                reservationsStrings[i ]+= " salle: \"" + current.getRoomRef().getNumber() + "\"";
                reservationsStrings[i] += "\n\t\t capacité: " + current.getRoomRef().getCapacity() + " personnes";
            }
            long duree = (current.getEnd().getTime() - current.getStart().getTime())/(1000*60);
            reservationsStrings[i] += "\n\t\t durée:";
            if (duree>60*24*30*365){
                long annees = duree / (60*24);
                reservationsStrings[i] += " " +  annees + " an";
                if (annees>1){
                    reservationsStrings[i] += "nées";
                }
                duree -= annees*(60*24);
            }
            if (duree>60*24*30){
                long mois = duree / (60*24*30);
                reservationsStrings[i] += " " +  mois + " mois";
                duree -= mois*(60*24*30);
            }
            if (duree>60*24){
                long jours = duree / (60*24);
                reservationsStrings[i] += " " +  jours + " jour";
                if (jours>1){
                    reservationsStrings[i] += "s";
                }
                duree -= jours*(60*24);
            }
            if (duree>60){
                long heures = duree / 60;
                reservationsStrings[i] += " " +  heures + " heure";
                if (heures>1){
                    reservationsStrings[i] += "s";
                }
                duree -= heures*60;
            }
            if (duree>0) {
                reservationsStrings[i] += " " + duree + " minute";
                if (duree>1){
                    reservationsStrings[i] += "s";
                }
            }
            /* invitations pas implementés
            if (current.getInvitedUsersCollection() != null){
                reservationsStrings[i] += " (" + current.getInvitedUsersCollection().size()+" invitées)";
            } else {
                reservationsStrings[i] += " (" + 0 +" invitées)";
            }*/
        }
        return reservationsStrings;
    }


    // Ici du code de debug potentiellement utile

    /*Set<String> set = sharedPreferences.getStringSet(Globals.CACHE_EQUIPMENTS_KEY, new TreeSet<String>());
        String[] array = Globals.setToArray(set);
        Log.d("CACHE_EQUIPMENTS TAILLE", ""+array.length);
        for (String s : array) {
            Log.d("CACHE_EQUIPMENTS_KEY", s);
        }*/
}
