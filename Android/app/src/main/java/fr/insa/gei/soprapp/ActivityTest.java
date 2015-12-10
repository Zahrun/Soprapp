package fr.insa.gei.soprapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.springframework.web.client.RestTemplate;

import fr.insa.gei.soprapp.dto.Sites;

public class ActivityTest extends AppCompatActivity {

 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_test);

        Button test = (Button) findViewById(R.id.testButton);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "192.168.0.101:8080/soprapp/sites" ;
                // Create a new RestTemplate instance
                RestTemplate restTemplate = new RestTemplate();
                Sites result = new Sites();
                result = restTemplate.getForObject(uri, Sites.class);
                displayResult(result) ;
        }
        });
    }

    private void displayResult(Sites result) {
    }


}
