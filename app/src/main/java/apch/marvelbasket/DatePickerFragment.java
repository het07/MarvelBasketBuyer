package apch.marvelbasket;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private static final String TAG = "DatePickerFragment";
    private TextView birthdate;

    public DatePickerFragment(TextView v) {
        birthdate=v;
    }

    @Override
    public DatePickerDialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int arg1, int arg2, int arg3) {
        // Do something with the date chosen by the use
        showDate(arg1, arg2+1, arg3);
    }

    private void showDate(int year, int month, int day) {
        birthdate.setText(new StringBuilder().append(day).append("-")
                .append(month).append("-").append(year));
        Log.e(TAG, "showDate: date in sp : "+birthdate.getText().toString().trim() );
    }
}
