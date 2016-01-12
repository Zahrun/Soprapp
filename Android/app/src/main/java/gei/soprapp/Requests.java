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
import gei.soprapp.entities.Reservations;
import gei.soprapp.entities.Sites;

/**
 * Created by Clément Baudouin on 11/01/2016.
 */
public class Requests {


    private static <T> T requete(String URI, Class<T> type, final View view) {
        T result = null;
        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        try {
                ResponseEntity<T> responseEntity = restTemplate.getForEntity(URI, type);
                result = responseEntity.getBody();
        } catch (Exception e){
            e.printStackTrace();
            view.post(new Runnable() {
                @Override
                public void run() {
                    new AlertDialog.Builder(view.getContext())
                            .setTitle("Pas de connexion")
                            .setMessage("La connexion a échoué, vérifiez votre connexion internet.")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            });
        }
        return result;
    }

    private static <T> T requete(String URI, Class<T> type, final View view, Map<String,String> params) {
        T result = null;
        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        try {
            ResponseEntity<T> responseEntity = restTemplate.getForEntity(URI, type, params);
            result = responseEntity.getBody();
        } catch (Exception e){
            e.printStackTrace();
            view.post(new Runnable() {
                @Override
                public void run() {
                    new AlertDialog.Builder(view.getContext())
                            .setTitle("Pas de connexion")
                            .setMessage("La connexion a échoué, vérifiez votre connexion internet.")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            });
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
        // Enregister la liste
        int[] reservations = new int[result.length];
        for (int i = 0; i<result.length; i++){
            reservations[i] = result[i].getReservationID();
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

    public static Equipments[] getEquipments(View view){
        String uri = Globals.REST_URI+"entities.equipments";
        Equipments[] result = requete(uri, Equipments[].class, view);
        if (result==null){
            return result;
        }
        // Enregister la liste
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
}
