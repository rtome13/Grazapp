package grazapp.events;

import grazapp.main.R;
import java.util.Calendar;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;


@SuppressLint("ValidFragment")
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
	int caller;

	public TimePickerFragment() {
	}

	@SuppressLint("ValidFragment")
	public TimePickerFragment(int c) {
		caller = c;
	}


	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current time as the default values for the picker
		final Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);

		// Create a new instance of TimePickerDialog and return it
		return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
	}

	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		// Do something with the time chosen by the user

		StringBuilder sb=new StringBuilder();
		sb.append(Integer.toString(hourOfDay) + ":" + Integer.toString(minute));
		String dobStr=sb.toString();

		TextView tv;
		if(caller == R.id.button_start_time)
			tv = (TextView) getActivity().findViewById(R.id.startTime_field);
		else 
			if(caller == R.id.button_end_time)
				tv = (TextView) getActivity().findViewById(R.id.endTime_field);
			else return;
		tv.setText(dobStr);
	}
}