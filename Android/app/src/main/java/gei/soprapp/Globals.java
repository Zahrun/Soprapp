package gei.soprapp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
    public static final long INTERVALLE_MISE_A_JOUR = 2000;

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


    // Ici du code de debug potentiellement utile

    /*Set<String> set = sharedPreferences.getStringSet(Globals.CACHE_EQUIPMENTS_KEY, new TreeSet<String>());
        String[] array = Globals.setToArray(set);
        Log.d("CACHE_EQUIPMENTS TAILLE", ""+array.length);
        for (String s : array) {
            Log.d("CACHE_EQUIPMENTS_KEY", s);
        }*/
}
