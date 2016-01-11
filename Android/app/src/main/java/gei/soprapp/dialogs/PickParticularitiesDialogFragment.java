package gei.soprapp.dialogs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Set;

import gei.soprapp.Globals;
import gei.soprapp.MainActivity;
import gei.soprapp.R;
import gei.soprapp.entities.Equipments;

/**
 * A simple {@link Fragment} subclass.
 */
public class PickParticularitiesDialogFragment extends DialogFragment {

    private ArrayList<Integer> mSelectedItems;

    public PickParticularitiesDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mSelectedItems = new ArrayList<>();  // Where we track the selected items
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String[] defaultEquipments = getResources().getStringArray(R.array.equipments_array);
        Set<String> itemsSet = sharedPreferences.getStringSet(Globals.CACHE_EQUIPMENTS_KEY, Globals.arrayToSet(defaultEquipments));
        String[] equipments = Globals.setToArray(itemsSet);
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
                                    mSelectedItems.add(which);
                                } else if (mSelectedItems.contains(which)) {
                                    // Else, if the item is already in the array, remove it
                                    mSelectedItems.remove(Integer.valueOf(which));
                                }
                            }
                        })
                        // Set the action buttons
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK, so save the mSelectedItems results somewhere
                        // or return them to the component that opened the dialog

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
