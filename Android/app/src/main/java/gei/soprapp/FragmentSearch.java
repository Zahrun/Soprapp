package gei.soprapp;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import gei.soprapp.entities.Sites;

/**
 * Created by Clément Baudouin on 10/01/2016.
 */
public class FragmentSearch extends FragmentAbstract {

    public static FragmentAbstract newInstance(int sectionNumber) {
        FragmentAbstract fragment = new FragmentSearch();
        return FragmentAbstract.newInstance(fragment, sectionNumber, R.layout.fragment_search);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.sites_array, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        final FragmentManager fm = getActivity().getFragmentManager();

        Button date = (Button) view.findViewById(R.id.dateButton);
        date.setOnClickListener(new View.OnClickListener()

                                {
                                    @Override
                                    public void onClick(View v) {
                                        PickDateFragment pdf = new PickDateFragment();
                                        pdf.show(fm, "datePicker");
                                    }
                                }

        );

        Button time = (Button) view.findViewById(R.id.timeButton);
        time.setOnClickListener(new View.OnClickListener()

                                {
                                    @Override
                                    public void onClick(View v) {
                                        PickTimeFragment ptf = new PickTimeFragment();
                                        ptf.show(fm, "timePicker");
                                    }
                                }

        );

        Button pick = (Button) view.findViewById(R.id.pickButton);
        pick.setOnClickListener(new View.OnClickListener()

                                {
                                    @Override
                                    public void onClick(View v) {
                                        PickParticularitiesFragment ppf = new PickParticularitiesFragment();
                                        ppf.show(fm, "particularitiesPicker");
                                    }
                                }


        );

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                ((MainActivity) getContext()).getmSectionsPagerAdapter().switchSearchFragment();
            }
        });
    }
}
