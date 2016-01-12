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

import java.util.Set;
import java.util.TreeSet;

import gei.soprapp.entities.Equipments;
import gei.soprapp.entities.Rooms;
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
            ResponseEntity<T>responseEntity = restTemplate.getForEntity(URI, type);
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
}
