package gei.soprapp;

import android.widget.TextView;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import gei.soprapp.entities.Sites;

/**
 * Created by Clément Baudouin on 11/01/2016.
 */
public class Requetes {

    private static <T> T requete(String URI, Class<T> type) {
        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        ResponseEntity<T>responseEntity = restTemplate.getForEntity(URI, type);
        return responseEntity.getBody();
    }

    public static Sites[] getSites(){
        String uri = Globals.REST_URI+"entities.sites";
        return requete(uri, Sites[].class);
    }
}
