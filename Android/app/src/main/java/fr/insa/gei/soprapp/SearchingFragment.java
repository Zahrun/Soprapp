package fr.insa.gei.soprapp;


import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SearchingFragment extends Fragment {
    FragmentManager fm ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.searching_fragment, container, false);
        fm = getActivity().getFragmentManager();

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.sites_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

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

        Button heure = (Button) view.findViewById(R.id.heureButton);
        heure.setOnClickListener(new View.OnClickListener()

                                 {
                                     @Override
                                     public void onClick (View v){
                                         PickTimeFragment ptf = new PickTimeFragment();
                                         ptf.show(fm, "timePicker");
                                     }
                                 }

        );

        Button selectionner = (Button) view.findViewById(R.id.selectionnerButton);
        selectionner.setOnClickListener(new View.OnClickListener()

                                        {
                                            @Override
                                            public void onClick (View v){
                                                PickParticularitiesFragment ppf = new PickParticularitiesFragment();
                                                ppf.show(fm, "particularitiesPicker");
                                            }
                                        }



        );

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                Intent intent = new Intent(getActivity().getApplicationContext(), SearchResult.class);
                startActivity(intent);
            }
        } );

    }
}


