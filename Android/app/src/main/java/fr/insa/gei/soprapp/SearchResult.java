package fr.insa.gei.soprapp;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import fr.insa.gei.soprapp.entities.Sites;

public class SearchResult extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        new Thread(new Runnable() {
            @Override
            public void run() {
                String uri = "http://192.168.0.101:8080/webapp/rest/entities.sites";
                // Create a new RestTemplate instance
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                ResponseEntity<Sites[]> responseEntity = restTemplate.getForEntity(uri, Sites[].class);

                final Sites[] result = responseEntity.getBody();
                final TextView textView = (TextView) findViewById(R.id.search_result);
                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(result[0].toString());
                    }
                });
            }
        }).start();
    }


}
