package gei.soprapp;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Clément Baudouin on 10/01/2016.
 */
public class Globals {
    // Cache
    public final static String CACHE_SALLES_KEY = "cache_salles";
    public final static String CACHE_SITES_KEY = "cache_sites";
    public final static String CACHE_EQUIPMENTS_KEY = "cache_equipments";
    // Préférences
    public final static String PREF_FAVORITE_SALLE = "pref_key_salles";
    public final static String PREF_FAVORITE_SITE = "pref_key_site";
    //Valeurs par defaut
    public final static String DEFAULT_PREFERENCE_VALUE = "0";
    // URIs
    public final static String BASE_URI="http://176.31.1.146:8080/webapp";
    public final static String REST_URI=BASE_URI+"/rest/";

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

    public static String[] makePositionArray(int size){
        String result[] = new String[size];
        for (int i =0; i< size; i++){
            result[i]=String.valueOf(i);
        }
        return result;
    }

    // Ici du code de debug potentiellement utile

    /*Set<String> set = sharedPreferences.getStringSet(Globals.CACHE_EQUIPMENTS_KEY, new TreeSet<String>());
        String[] array = Globals.setToArray(set);
        Log.d("CACHE_EQUIPMENTS TAILLE", ""+array.length);
        for (String s : array) {
            Log.d("CACHE_EQUIPMENTS_KEY", s);
        }*/
}
