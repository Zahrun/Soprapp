package gei.soprapp;

import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Set;

import gei.soprapp.dialogs.PickDateDialogFragment;
import gei.soprapp.dialogs.PickEquipmentsDialogFragment;
import gei.soprapp.dialogs.PickTimeDialogFragment;

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
        Spinner spinner;
        Set<String> sites;
        String defaultSites[];
        switch (key) {
            case Globals.CACHE_SITES_KEY :
            // Affecter les valeurs de la liste déroullante des sites
            spinner = (Spinner) getActivity().findViewById(R.id.spinner);
            defaultSites = getResources().getStringArray(R.array.equipments_array);
            sites = sharedPreferences.getStringSet(Globals.CACHE_SITES_KEY, Globals.arrayToSet(defaultSites));
            ArrayAdapter<CharSequence> adapter = new ArrayAdapter(getActivity(), R.layout.spinner_item, Globals.setToArray(sites));
            adapter.setDropDownViewResource(R.layout.spinner_item);
            spinner.setAdapter(adapter);
                break;
            case Globals.PREF_FAVORITE_SITE:
                spinner = (Spinner) getActivity().findViewById(R.id.spinner);
                defaultSites = getResources().getStringArray(R.array.equipments_array);
                sites = sharedPreferences.getStringSet(Globals.CACHE_SITES_KEY, Globals.arrayToSet(defaultSites));
                // Valeur par défaut
                String sitesArray[] = Globals.setToArray(sites);
                String favoriteSite = sharedPreferences.getString(Globals.PREF_FAVORITE_SITE, sitesArray[0]);
                spinner.setSelection(Globals.findPositionInArray(favoriteSite, sitesArray));
                break;
            default:
                break;
        }
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Affecter les valeurs de la liste déroullante des sites
        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String[] defaultSites = getResources().getStringArray(R.array.equipments_array);
        Set<String> sites = sharedPreferences.getStringSet(Globals.CACHE_SITES_KEY, Globals.arrayToSet(defaultSites));
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(getActivity(),R.layout.spinner_item,Globals.setToArray(sites));
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);
        // Valeur par défaut
        String sitesArray[] = Globals.setToArray(sites);
        String favoriteSite = sharedPreferences.getString(Globals.PREF_FAVORITE_SITE, sitesArray[0]);
        spinner.setSelection(Globals.findPositionInArray(favoriteSite, sitesArray));

        //Etre notifié des changements de cache et préférences
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

        final java.text.DateFormat dateFormat = DateFormat.getDateFormat(getActivity());
        final TextView textViewDate = ((TextView) view.findViewById(R.id.textViewDate));
        textViewDate.setText(dateFormat.format(Calendar.getInstance().getTime()));

        final Button time = (Button) view.findViewById(R.id.timeButton);
        time.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View v) {
                                        PickTimeDialogFragment ptf = new PickTimeDialogFragment();
                                        ptf.show(fm, "timePicker");
                                    }
                                }

        );

        final java.text.DateFormat timeFormat = DateFormat.getTimeFormat(getActivity());
        final TextView textViewHeure = ((TextView) view.findViewById(R.id.textViewHeure));
        textViewHeure.setText(timeFormat.format(Calendar.getInstance().getTime()));

        Button pick = (Button) view.findViewById(R.id.pickButton);
        pick.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(final View v) {
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                PickEquipmentsDialogFragment ppf = new PickEquipmentsDialogFragment();
                                                ppf.show(fm, "particularitiesPicker");
                                            }
                                        }).start();
                                    }
                                }


        );

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                String site = (String) spinner.getSelectedItem();
                int duree = Integer.valueOf(((EditText) view.findViewById(R.id.editTextDuree)).getText().toString());
                int nbPersonnes = Integer.valueOf(((EditText) view.findViewById(R.id.editTextNbPerssonnes)).getText().toString());
                FragmentSearchResults.setRequest(site,duree,nbPersonnes);
                ((MainActivity) getContext()).getmSectionsPagerAdapter().switchSearchFragment();
            }
        });
    }
}
