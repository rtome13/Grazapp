package grazapp.events;

import grazapp.main.R;
import java.util.Calendar;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

@SuppressLint({ "NewApi", "ValidFragment" })
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
	int caller;
	
	public DatePickerFragment() {
	}
	
	@SuppressLint("ValidFragment")
	public DatePickerFragment(int c) {
		caller = c;
	}

@SuppressLint("NewApi")
@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	int year, month, day;
	

	// Use the current date as the default date in the picker
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	public void onDateSet(DatePicker view, int year, int month, int day) {
		StringBuilder sb=new StringBuilder();
		sb.append(Integer.toString(year) + "-" + Integer.toString(month + 1) + "-" + Integer.toString(day));
		String dobStr=sb.toString();
	
		TextView tv;

		
		if(caller == R.id.button_start_date)
			tv = (TextView) getActivity().findViewById(R.id.startDate_field);
		else 
			if(caller == R.id.button_end_date)
			tv = (TextView) getActivity().findViewById(R.id.endDate_field);
			else return;
		tv.setText(dobStr);
	}
}