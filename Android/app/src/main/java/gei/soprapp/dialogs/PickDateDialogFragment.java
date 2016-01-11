package gei.soprapp.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import gei.soprapp.FragmentSearchResults;
import gei.soprapp.R;


public class PickDateDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int monthOfYear = c.get(Calendar.MONTH);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        DatePickerDialog result = new DatePickerDialog(getActivity(), this, year, monthOfYear, dayOfMonth);
        result.getDatePicker().setMinDate(System.currentTimeMillis());
        return result;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // Do something with the time chosen by the user
        FragmentSearchResults.setRequestDate(new GregorianCalendar(year, monthOfYear, dayOfMonth).getTimeInMillis());
        final java.text.DateFormat dateformat = DateFormat.getTimeFormat(getActivity());
        final TextView textViewDate = ((TextView) getActivity().findViewById(R.id.textViewDate));
        final Date date = new GregorianCalendar(year,monthOfYear,dayOfMonth).getTime();
        textViewDate.post(new Runnable() {
            @Override
            public void run() {
                textViewDate.setText(dateformat.format(date));
            }
        });
    }
}
