package gei.soprapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.GenericArrayType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import gei.soprapp.entities.Equipments;
import gei.soprapp.entities.Rooms;
import gei.soprapp.entities.Reservations;
import gei.soprapp.entities.Sites;

/**
 * Created by Clément Baudouin on 11/01/2016.
 */
public class Requests {

    private static boolean errorDialogPrinted = false;
    private static AlertDialog errorDialog;

    private static <T> T requete(String URI, Class<T> type, final View view) {
        return requete(URI, type, view, new HashMap<String, String>());
    }

    private static <T> T requete(String URI, Class<T> type, final View view, Map<String,String> params) {
        T result = null;
        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        try {
            ResponseEntity<T> responseEntity = restTemplate.getForEntity(URI, type, params);
            result = responseEntity.getBody();
            if (Requests.errorDialogPrinted == true) {
                errorDialog.dismiss();
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        new AlertDialog.Builder(view.getContext())
                                .setTitle("La connexion a réussi")
                                .setMessage("La connexion a réussi, vous pouvez maintenant utiliser l'application.")
                                .setIcon(android.R.drawable.ic_dialog_info)
                                .show();
                    }
                });
                Requests.errorDialogPrinted = false;
            }
        } catch (Exception e){
            e.printStackTrace();
            if (!Requests.errorDialogPrinted) {
                Requests.errorDialogPrinted = true;
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        Requests.errorDialog = new AlertDialog.Builder(view.getContext())
                                .setTitle("Pas de connexion")
                                .setMessage("La connexion a échoué, vérifiez votre connexion internet.")
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                });
            }
        }
        return result;
    }

    public static Sites[] getSites(View view){
        String uri = Globals.REST_URI+"entities.sites";
        Sites[] result = requete(uri, Sites[].class, view);
        if (result==null){
            return result;
        }
        // Enregister la liste
        Log.d("Enregistrement", "listeSites");
        String[] sites = new String[result.length];
        for (int i = 0; i<result.length; i++){
            sites[i] = result[i].getName();
        }
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(Globals.CACHE_SITES_KEY, Globals.arrayToSet(sites));
        editor.commit();
        return result;
    }

    public static Reservations[] getReservations(View view){
        String uri = Globals.REST_URI+"entities.reservations";
        Reservations[] result = requete(uri, Reservations[].class, view);
        if (result==null){
            return result;
        }
        return result;
    }

    public static Reservations[] getReservationsCurrentUser(View view){
        String uri = Globals.REST_URI+"entities.reservations/filter/{searchParams}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("searchParams", "Marai");

        Reservations[] result = requete(uri, Reservations[].class, view, params);
        if (result==null){
            return result;
        }
        // Enregister la liste
        int[] reservations = new int[result.length];
        for (int i = 0; i<result.length; i++){
            reservations[i] = result[i].getReservationID();
        }
        return result;
    }

    public static Reservations[] getReservationsOfRoom(View view, String selected){
        String uri = Globals.REST_URI+"entities.reservations/filter/{searchParams}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("searchParams", "&"+selected);

        Reservations[] result = requete(uri, Reservations[].class, view, params);
        return result;
    }

    public static Equipments[] getEquipments(View view){
        String uri = Globals.REST_URI+"entities.equipments";
        Equipments[] result = requete(uri, Equipments[].class, view);
        if (result==null){
            return result;
        }
        // Enregister la liste
        Log.d("Enregistrement", "listeEquipments");
        String[] equipments = new String[result.length];
        for (int i = 0; i<result.length; i++){
            equipments[i] = result[i].getDescription();
        }
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(Globals.CACHE_EQUIPMENTS_KEY, Globals.arrayToSet(equipments));
        editor.commit();
        return result;
    }

    public static Rooms[] getRooms(View view){
        String uri = Globals.REST_URI+"entities.rooms";
        Rooms[] result = requete(uri, Rooms[].class, view);
        if (result==null){
            return result;
        }
        // Enregister la liste en Strings
        Log.d("Enregistrement", "listeRooms");
        String[] rooms = new String[result.length];
        for (int i = 0; i<result.length; i++){
            rooms[i] = result[i].getNumber();
        }
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(Globals.CACHE_ROOMS_KEY, Globals.arrayToSet(rooms));
        editor.commit();
        return result;
    }

    public static Rooms[] getSiteRooms(View view, String site){
        String uri = Globals.REST_URI+"entities.rooms";
        Rooms[] result = requete(uri, Rooms[].class, view);
        if (result==null){
            return result;
        }
        // Enregister la liste
        Log.d("Enregistrement", "listeRooms");
        String[] rooms = new String[result.length];
        for (int i = 0; i<result.length; i++){
            rooms[i] = result[i].getNumber();
        }
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(Globals.CACHE_ROOMS_KEY, Globals.arrayToSet(rooms));
        editor.commit();
        return result;
    }

    public static boolean deleteReservation(Reservations selected) {
        String uri = Globals.REST_URI+"entities.reservations";

        Map<String, String> params = new HashMap<String, String>();
        params.put("searchParams", "&"+selected);

        boolean result = true;//requete(uri, Reservations[].class, view, params);
        return result;
    }
}
