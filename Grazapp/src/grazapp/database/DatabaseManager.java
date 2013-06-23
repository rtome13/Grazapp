package grazapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {
	//InputStream is = null;
	
	
	public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
	
	/** Constants used for the database version and localization */
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "Grazapp.db";
	
	/** Table Names for the tables of the SQL Database */
	private static String TABLE_EVENTS = "Events";
	
	/** Column Names for the tables of the SQL Database */
	private static String EVENT_ID = "Event_id";
	private static String EVENT_NAME = "Event_name";
	private static String EVENT_TYPE = "Event_type";
	private static String EVENT_LOCATION = "Event_location";
	private static String EVENT_LOCATIONLATITUDE = "Event_locationlatitude";
	private static String EVENT_LOCATIONLONGITUDE = "Event_locationlongitude";
	private static String EVENT_START_DATE_DAY = "Event_start_date_day";
	private static String EVENT_START_DATE_MONTH = "Event_start_date_month";
	private static String EVENT_START_DATE_YEAR = "Event_start_date_year";
	private static String EVENT_START_TIME_HOUR = "Event_start_time_hour";
	private static String EVENT_START_TIME_MINUTE = "Event_start_time_minute";
	private static String EVENT_END_DATE_DAY = "Event_end_date_day";
	private static String EVENT_END_DATE_MONTH = "Event_end_date_month";
	private static String EVENT_END_DATE_YEAR = "Event_end_date_year";
	private static String EVENT_END_TIME_HOUR = "Event_end_time_hour";
	private static String EVENT_END_TIME_MINUTE = "Event_end_time_minute";
	private static String EVENT_DESCRIPTION = "Event_description";
	private static String EVENT_SOURCE = "Event_source";
	
	/** SQL Specific variables. Used for normalization*/
	private static final String TEXT = " TEXT";
	private static final String INTEGER = " INTEGER";
 	private static final String PRIMARY_KEY = " PRIMARY KEY";
 	private static final String AUTOINCREMENT = " AUTOINCREMENT";
	private static final String COMMA = ", ";
	private static final String PLICA = "'";
	
		
	/** Query commands to create and delete the tables of the database. Used in OnCreate and OnUpgrade. */
	private static final String SQL_CREATE_EVENTS = "CREATE TABLE " + TABLE_EVENTS 
													+ "(" 
														+ EVENT_ID + INTEGER + PRIMARY_KEY + AUTOINCREMENT + COMMA
														+ EVENT_NAME + TEXT + COMMA 
														+ EVENT_TYPE + TEXT + COMMA  
														+ EVENT_LOCATION + TEXT + COMMA
														+ EVENT_LOCATIONLATITUDE + TEXT + COMMA 
														+ EVENT_LOCATIONLONGITUDE + TEXT + COMMA 
														+ EVENT_START_DATE_DAY + INTEGER + COMMA 
														+ EVENT_START_DATE_MONTH + INTEGER + COMMA
														+ EVENT_START_DATE_YEAR + INTEGER + COMMA
														+ EVENT_START_TIME_HOUR + INTEGER + COMMA 
														+ EVENT_START_TIME_MINUTE + INTEGER + COMMA 
														+ EVENT_END_DATE_DAY + INTEGER + COMMA 
														+ EVENT_END_DATE_MONTH + INTEGER + COMMA 
														+ EVENT_END_DATE_YEAR + INTEGER + COMMA 
														+ EVENT_END_TIME_HOUR + INTEGER + COMMA
														+ EVENT_END_TIME_MINUTE + INTEGER + COMMA 
														+ EVENT_DESCRIPTION + INTEGER + COMMA
														+ EVENT_SOURCE + TEXT
													+ ")";

	private static final String SQL_DELETE_EVENTS = "DROP TABLE IF EXISTS " + TABLE_EVENTS;
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_EVENTS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_EVENTS);
		onCreate(db);
	}
	
	public void resetDatabase(){
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(SQL_DELETE_EVENTS);
	}

	public boolean addEvent(String name, String type, String location, String locationLatitude, String locationLongitude, int startDateDay, int startDateMonth, int startDateYear, int startTimeHour, int startTimeMinute, int endDateDay, int endDateMonth, int endDateYear, int endTimeHour, int endTimeMinute, String description, String source){
		SQLiteDatabase db = getWritableDatabase();
		String insert_event_query = "INSERT INTO " + TABLE_EVENTS
																+ "(" 
																	+ EVENT_NAME + COMMA
																	+ EVENT_TYPE + COMMA
																	+ EVENT_LOCATION + COMMA
																	+ EVENT_LOCATIONLATITUDE + COMMA 
																	+ EVENT_LOCATIONLONGITUDE + COMMA
																	+ EVENT_START_DATE_DAY + COMMA
																	+ EVENT_START_DATE_MONTH + COMMA
																	+ EVENT_START_DATE_YEAR + COMMA
																	+ EVENT_START_TIME_HOUR + COMMA 
																	+ EVENT_START_TIME_MINUTE + COMMA 
																	+ EVENT_END_DATE_DAY + COMMA 
																	+ EVENT_END_DATE_MONTH + COMMA 
																	+ EVENT_END_DATE_YEAR + COMMA 
																	+ EVENT_END_TIME_HOUR + COMMA
																	+ EVENT_END_TIME_MINUTE + COMMA 
																	+ EVENT_DESCRIPTION + COMMA
																	+ EVENT_SOURCE
																+ ")"
				
										+ " VALUES("
												+ PLICA + name + PLICA + COMMA
												+ PLICA + type + PLICA + COMMA
												+ PLICA + location + PLICA + COMMA
												+ PLICA + locationLatitude + PLICA + COMMA
												+ PLICA + locationLongitude + PLICA + COMMA
												+ PLICA + startDateDay + PLICA + COMMA
												+ PLICA + startDateMonth + PLICA + COMMA
												+ PLICA + startDateYear + PLICA + COMMA
												+ PLICA + startTimeHour + PLICA + COMMA
												+ PLICA + startTimeMinute + PLICA + COMMA
												+ PLICA + endDateDay + PLICA + COMMA
												+ PLICA + endDateMonth + PLICA + COMMA
												+ PLICA + endDateYear + PLICA + COMMA
												+ PLICA + endTimeHour + PLICA + COMMA
												+ PLICA + endTimeMinute + PLICA + COMMA
												+ PLICA + description + PLICA + COMMA
												+ PLICA + source + PLICA
											+ ")";
			db.execSQL(insert_event_query);
			return true;
		}

	
	public Cursor getAllEvents(){
		SQLiteDatabase db = getReadableDatabase();
		String query = "SELECT * FROM " + TABLE_EVENTS + " ORDER BY " + EVENT_START_DATE_YEAR + " DESC" + COMMA +  EVENT_START_DATE_MONTH + " DESC" + COMMA + EVENT_START_DATE_DAY + " DESC";
		Cursor result = db.rawQuery(query, null);
		return result;
	}
	
	public void removeWebsiteEvents(){
		SQLiteDatabase db = getReadableDatabase();
		String removeWebsiteElementsQuery = "DELETE FROM " + TABLE_EVENTS + " WHERE " + EVENT_SOURCE  +  " = " + PLICA + "website" + PLICA;
		db.execSQL(removeWebsiteElementsQuery);
	}
	
	public String getLastId(){
		SQLiteDatabase db = getReadableDatabase();
		String query = "SELECT * FROM " + TABLE_EVENTS + " WHERE " +  EVENT_ID + " = (SELECT MAX(" + EVENT_ID + ") FROM " + TABLE_EVENTS + ")";
		Cursor result = db.rawQuery(query, null);
		result.moveToFirst();
		return result.getString(0);
	}
}