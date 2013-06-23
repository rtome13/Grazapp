package grazapp.main;

import grazapp.database.DatabaseManager;
import grazapp.events.AddEvent;
import grazapp.swipe.CurrencyFragment;
import grazapp.swipe.EventsList;
import grazapp.swipe.SightsList;
import grazapp.swipe.UniInfoFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

@SuppressLint("DefaultLocale")
public class MainActivity extends FragmentActivity {	
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;
	public DatabaseManager dbm = new DatabaseManager(this); // start the databaseManager
	public AppManager appManager = null;
	
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		////// DO NOT DELETE THIS - this is for making a reset to the database
		////// HOW TO USE:
		////// 1. Uncomment those 2 lines and run the app
		////// 2. Comment those lines again and run the app
		deleteDatabase("Grazapp.db");
		populateDB();
		
		appManager = new AppManager(dbm, "");
		
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
		case R.id.add_event:
			Intent intent = new Intent(this, AddEvent.class);
			startActivity(intent);
			return true;
		case R.id.menu_settings:
			gotoAbout();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
		
	}

	private void gotoAbout() {
		String Aboutmessage = "Henrik Braun\nAri Rauhala\nRita Tomé\nC. Ryan Williams\n\nCoursework for Mobile Applications at TUGraz ";
		new AlertDialog.Builder(this)
	    .setTitle("Grazapp v.1.0")
	    .setMessage(Aboutmessage)
	    
	    .setNegativeButton("Back", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	          
	        }
	     })
	     .show();
		
		
		
	}

	public void populateDB(){
		//InputStream in = getResources().openRawResource(R.raw.database);
		InputStream is = null;

		try {
			is = getAssets().open("database.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = null;
		try {
			line = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while(line != null){
			String[] temp;
			temp = line.split(";");
			
			String[] splitStartDate = temp[5].split("-");
			int startDateYear = Integer.parseInt(splitStartDate[0]);
			int startDateMonth = Integer.parseInt(splitStartDate[1]);
			int startDateDay = Integer.parseInt(splitStartDate[2]);
			
			
			String[] splitStartTime = temp[6].split(":");
			int startTimeHour = Integer.parseInt(splitStartTime[0]);
			int startTimeMinute = Integer.parseInt(splitStartTime[1]);

				
			String[] splitEndDate = temp[7].split("-");
			int endDateYear = Integer.parseInt(splitEndDate[0]);
			int endDateMonth = Integer.parseInt(splitEndDate[1]);
			int endDateDay = Integer.parseInt(splitEndDate[2]);

			
			String[] splitEndTime = temp[8].split(":");
			int endTimeHour = Integer.parseInt(splitEndTime[0]);
			int endTimeMinute = Integer.parseInt(splitEndTime[1]);
			
			dbm.addEvent(
					temp[0], 
					temp[1], 
					temp[2], 
					temp[3], 
					temp[4], 
					startDateDay, 
					startDateMonth, 
					startDateYear, 
					startTimeHour, 
					startTimeMinute, 
					endDateDay, 
					endDateMonth, 
					endDateYear, 
					endTimeHour, 
					endTimeMinute, 
					temp[9], 
					"website");
			try {
				line = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	
	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	@SuppressLint("DefaultLocale")
	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		
		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
            
			switch(position){
            case 0:
            	return new UniInfoFragment();
            case 1:
            	return new EventsList(dbm);
            case 2:
            	return new SightsList();
            case 3:
            	return new CurrencyFragment();
            default:
            	return null;
			}
		}

		@Override
		public int getCount() {
			// Show 4 total pages.
			return 4;
		}

		@SuppressLint("DefaultLocale")
		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase();
			case 1:
				return getString(R.string.title_section2).toUpperCase();
			case 2:
				return getString(R.string.title_section4).toUpperCase();
			case 3:
				return getString(R.string.title_section5).toUpperCase();
			}
			return null;
		}
		
	}

}