package gei.soprapp.dialogs;


import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

import gei.soprapp.FragmentSearchResults;
import gei.soprapp.R;

public class PickTimeDialogFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        TimePickerDialog result = new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity())){
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Calendar c = Calendar.getInstance();
                Date selected = new Date(FragmentSearchResults.getRequestDate()+hourOfDay*60*60*1000+minute*60*1000);
                if (selected.before(c.getTime())){
                    int minHour = c.get(Calendar.HOUR_OF_DAY);
                    int minMinute = c.get(Calendar.MINUTE);
                    updateTime(minHour,minMinute);
                    selected = new Date(FragmentSearchResults.getRequestDate()+minHour*60*60*1000+minMinute*60*1000);
                }
                final Date finalDate = selected;
                final java.text.DateFormat timeFormat = DateFormat.getTimeFormat(getActivity());
                final TextView textViewHeure = ((TextView) getActivity().findViewById(R.id.textViewHeure));
                textViewHeure.post(new Runnable() {
                    @Override
                    public void run() {
                        textViewHeure.setText(timeFormat.format(finalDate));
                    }
                });
            }
        };
        return result;
    }



    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        FragmentSearchResults.setRequestTime(hourOfDay*60*60*1000+minute*60*1000);
    }
}
