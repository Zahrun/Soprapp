package gei.soprapp;

import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Set;

import gei.soprapp.dialogs.PickDateDialogFragment;
import gei.soprapp.dialogs.PickParticularitiesDialogFragment;
import gei.soprapp.dialogs.PickTimeDialogFragment;
import gei.soprapp.entities.Equipments;
import gei.soprapp.entities.Sites;

/**
 * Created by Clément Baudouin on 10/01/2016.
 */
public class FragmentSearch extends FragmentAbstract implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static FragmentAbstract newInstance(int sectionNumber) {
        FragmentAbstract fragment = new FragmentSearch();
        return FragmentAbstract.newInstance(fragment, sectionNumber, R.layout.fragment_search);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(Globals.CACHE_SITES_KEY)) {
            // Affecter les valeurs de la liste déroullante des sites
            Spinner spinner = (Spinner) getActivity().findViewById(R.id.spinner);
            String[] defaultSites = getResources().getStringArray(R.array.equipments_array);
            Set<String> sites = sharedPreferences.getStringSet(Globals.CACHE_SITES_KEY, Globals.arrayToSet(defaultSites));
            ArrayAdapter<CharSequence> adapter = new ArrayAdapter(getActivity(), R.layout.spinner_item, Globals.setToArray(sites));
            adapter.setDropDownViewResource(R.layout.spinner_item);
            spinner.setAdapter(adapter);
        }
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Affecter les valeurs de la liste déroullante des sites
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String[] defaultSites = getResources().getStringArray(R.array.equipments_array);
        Set<String> sites = sharedPreferences.getStringSet(Globals.CACHE_SITES_KEY, Globals.arrayToSet(defaultSites));
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(getActivity(),R.layout.spinner_item,Globals.setToArray(sites));
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);

        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        final FragmentManager fm = getActivity().getFragmentManager();

        Button date = (Button) view.findViewById(R.id.dateButton);
        date.setOnClickListener(new View.OnClickListener()

                                {
                                    @Override
                                    public void onClick(View v) {
                                        PickDateDialogFragment pdf = new PickDateDialogFragment();
                                        pdf.show(fm, "datePicker");
                                    }
                                }

        );

        Button time = (Button) view.findViewById(R.id.timeButton);
        time.setOnClickListener(new View.OnClickListener()

                                {
                                    @Override
                                    public void onClick(View v) {
                                        PickTimeDialogFragment ptf = new PickTimeDialogFragment();
                                        ptf.show(fm, "timePicker");
                                    }
                                }

        );

        Button pick = (Button) view.findViewById(R.id.pickButton);
        pick.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(final View v) {
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                PickParticularitiesDialogFragment ppf = new PickParticularitiesDialogFragment();
                                                ppf.show(fm, "particularitiesPicker");
                                            }
                                        }).start();
                                    }
                                }


        );

        new Thread(new Runnable() {
            @Override
            public void run() {
                Requests.getSites(view);
                Requests.getEquipments(view);
            }
        }).start();


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                FragmentSearchResults.setRequest(null,null,null,0,0,null);
                ((MainActivity) getContext()).getmSectionsPagerAdapter().switchSearchFragment();
            }
        });
    }
}
