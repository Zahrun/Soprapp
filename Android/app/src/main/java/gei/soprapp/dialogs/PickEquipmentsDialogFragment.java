package gei.soprapp.dialogs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

import gei.soprapp.FragmentSearchResults;
import gei.soprapp.Globals;
import gei.soprapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PickEquipmentsDialogFragment extends DialogFragment {

    private ArrayList<String> mSelectedItems;

    public PickEquipmentsDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mSelectedItems = new ArrayList<>();  // Where we track the selected items
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String[] defaultEquipments = getResources().getStringArray(R.array.equipments_array);
        Set<String> itemsSet = sharedPreferences.getStringSet(Globals.CACHE_EQUIPMENTS_KEY, Globals.arrayToSet(defaultEquipments));
        final String[] equipments = Globals.setToArray(itemsSet);
        // Set the dialog title
        builder.setTitle(R.string.equipments)
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setMultiChoiceItems(equipments, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    mSelectedItems.add(equipments[which]);
                                } else if (mSelectedItems.contains(equipments[which])) {
                                    // Else, if the item is already in the array, remove it
                                    mSelectedItems.remove(equipments[which]);
                                }
                            }
                        })
                        // Set the action buttons
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK, so save the mSelectedItems results somewhere
                        // or return them to the component that opened the dialog
                        FragmentSearchResults.setRequestEquipments(Globals.listToArray(mSelectedItems));
                        final TextView textViewEquipements = ((TextView) getActivity().findViewById(R.id.textViewEquipements));
                        String text = "";
                        for (String s : mSelectedItems) {
                            text += s + ", ";
                        }
                        final String finalText = text.substring(0,text.length()-2);
                        textViewEquipements.post(new Runnable() {
                            @Override
                            public void run() {
                                textViewEquipements.setText(finalText);
                            }
                        });
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }
}
