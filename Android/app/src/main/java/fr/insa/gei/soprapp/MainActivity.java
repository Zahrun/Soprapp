package fr.insa.gei.soprapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import fr.insa.gei.soprapp.entities.Sites;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String uri = "http://192.168.0.101:8080/webapp/rest/entities.sites";
                        // Create a new RestTemplate instance
                        RestTemplate restTemplate = new RestTemplate();
                        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter  ());
                        final Sites result = restTemplate.getForObject(uri, Sites.class);

                        final TextView  textView = (TextView) findViewById(R.id.textView2);
                        textView.post(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(result.toString());
                            }
                        });
                    }
                }).start();

            }
        });



        /*fab.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent act2 = new Intent(view.getContext(),ActivityTest.class);
                startActivity(act2);
            }
        });*/

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
            // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sites_array, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Button date = (Button) findViewById(R.id.dateButton);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickDateFragment pdf = new PickDateFragment();
                pdf.show(getFragmentManager(), "datePicker");
            }
        });

        Button heure = (Button) findViewById(R.id.heureButton);
        heure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickTimeFragment ptf = new PickTimeFragment();
                ptf.show(getFragmentManager(),"timePicker");
            }
        });

        Button selectionner = (Button) findViewById(R.id.selectionnerButton);
        selectionner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickParticularitiesFragment ppf = new PickParticularitiesFragment();
                ppf.show(getFragmentManager(),"particularitiesPicker");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
