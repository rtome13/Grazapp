package grazapp.events;


import grazapp.database.DatabaseManager;
import grazapp.main.AppManager;
import grazapp.main.R;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddEvent extends FragmentActivity {
	
	
	public void submitEvent(View view) {
		String defaultLatitutde = "47.070278"; // center of Graz - DEFAULT
		String defaultLongitude = "15.438889"; // center of Graz - DEFAULT
		


	    TextView nameField = (TextView) findViewById(R.id.name_field);
	    String insertedName = nameField.getText().toString();
	    if(insertedName.equals("")){
	    	Toast toast = Toast.makeText(getApplicationContext(), "The field \"Event Name\" must not be empty!", Toast.LENGTH_SHORT);
	    	toast.show();
	    	return;
	    }
	    
	    Spinner eventType = (Spinner) findViewById(R.id.spinner1);
	    String insertedType = eventType.getSelectedItem().toString();
	    if(insertedType.equals("Choose the type")){
	    	Toast toast = Toast.makeText(getApplicationContext(), "The event must have a type", Toast.LENGTH_SHORT);
	    	toast.show();
	    	return;
	    }
	    
	    TextView locationField = (TextView) findViewById(R.id.location_field);
	    String insertedLocation = locationField.getText().toString();
	    
	    TextView startDateField = (TextView) findViewById(R.id.startDate_field);
	    String insertedStartDate = startDateField.getText().toString();
	    if(insertedStartDate.equals("")){
	    	Toast toast = Toast.makeText(getApplicationContext(), "The field \"Start Date\" must not be empty!", Toast.LENGTH_SHORT);
	    	toast.show();
	    	return;
	    }
		String[] splitStartDate = insertedStartDate.split("-");
	
	        
	    TextView endDateField = (TextView) findViewById(R.id.endDate_field);
	    String insertedEndDate = endDateField.getText().toString();
	    if(insertedEndDate.equals("")){
	    	Toast toast = Toast.makeText(getApplicationContext(), "The field \"End Date\" must not be empty!", Toast.LENGTH_SHORT);
	    	toast.show();
	    	return;
	    }
	    String[] splitEndDate = insertedEndDate.split("-");

	    TextView startTimeField = (TextView) findViewById(R.id.startTime_field);
	    String insertedStartTime = startTimeField.getText().toString();
	    if(insertedStartTime.equals("")){
	    	Toast toast = Toast.makeText(getApplicationContext(), "The field \"Start Time\" must not be empty!", Toast.LENGTH_SHORT);
	    	toast.show();
	    	return;
	    }
	    String[] splitStartTime = insertedStartTime.split(":");
	    
	    TextView endTimeField = (TextView) findViewById(R.id.endTime_field);
	    String insertedEndTime = endTimeField.getText().toString();
	    if(insertedEndTime.equals("")){
	    	Toast toast = Toast.makeText(getApplicationContext(), "The field \"End Time\" must not be empty!", Toast.LENGTH_SHORT);
	    	toast.show();
	    	return;
	    }
		String[] splitEndTime = insertedEndTime.split(":");
	    
	    TextView descriptionField = (TextView) findViewById(R.id.description_field);
	    String insertedDescription = descriptionField.getText().toString();
	    
	    
		DatabaseManager dbm = new DatabaseManager(getBaseContext());
		AppManager apm = new AppManager(dbm, "");
	    
	    dbm.addEvent( 
	    		insertedName, 
	    		insertedType, 
	    		insertedLocation, 
	    		defaultLatitutde,
	    		defaultLongitude,
	    		Integer.parseInt(splitStartDate[2]),
	    		Integer.parseInt(splitStartDate[1]),
	    		Integer.parseInt(splitStartDate[0]),
	    		Integer.parseInt(splitStartTime[0]),
	    		Integer.parseInt(splitStartTime[1]),
	    		Integer.parseInt(splitEndDate[2]),
	    		Integer.parseInt(splitEndDate[1]),
	    		Integer.parseInt(splitEndDate[0]),
	    		Integer.parseInt(splitEndTime[0]),
	    		Integer.parseInt(splitEndTime[1]), 
	    		insertedDescription,
	    		"local");
	    /*
	    Event event = new Event();
	    event.setAll(dbm.getLastId(), 
	    		insertedName, 
	    		insertedType, 
	    		insertedLocation, 
	    		defaultLatitutde,
	    		defaultLongitude,
	    		insertedStartDate, 
	    		insertedStartTime,
	    		insertedEndDate, 
	    		insertedEndTime, 
	    		insertedDescription,
	    		"local");
	    apm.addInPlace(event);*/
	    
	    
	    Toast toast = Toast.makeText(getApplicationContext(), "Event added with success!", Toast.LENGTH_SHORT);
	    toast.show();
	    
	    dbm.close();
	    
	    finish();
	}
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_event);
		
	}
	
	public void showDatePickerDialog(View v) {
	    DialogFragment datePicker = new DatePickerFragment(v.getId());

	    datePicker.show(getSupportFragmentManager(), "datePicker"); 
	}
	
	public void showTimePickerDialog(View v) {
	    DialogFragment timePicker = new TimePickerFragment(v.getId());

	    timePicker.show(getSupportFragmentManager(), "timePicker"); 
	}
}
