package grazapp.events.test;

import java.util.Calendar;

import grazapp.main.MainActivity;
import grazapp.main.R;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.jayway.android.robotium.solo.Solo;

public class AddEventTest extends ActivityInstrumentationTestCase2<MainActivity> {
	private Solo solo;
	TextView startDateField;
	 TextView startTimeField;

	public AddEventTest() {
		super(MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
		final Activity activity = getActivity();
	    getInstrumentation().waitForIdleSync();
	    startDateField = (TextView) activity.findViewById(R.id.startDate_field);
	    startTimeField = (TextView) activity.findViewById(R.id.startTime_field);
	    Calendar calendar = Calendar.getInstance();
	    int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		
	    //tests showTimePickerDialog() and also the TimePicker class
		solo.clickOnView(startTimeField);
	    assertTrue("Trying to find the TimePickerDialog", solo.searchText("00"));
	    
	    //tests showDatePickerDialog() and also the DatePicker class
	    solo.clickOnView(startDateField);
	    assertTrue("Year in the DatePickerDialog", solo.searchText(String.valueOf(year)));
	    assertTrue("Month in the DatePickerDialog", solo.searchText(String.valueOf(month)));
	    assertTrue("Day in the DatePickerDialog", solo.searchText(String.valueOf(day)));
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}


}
