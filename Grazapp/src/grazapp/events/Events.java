package grazapp.events;

import grazapp.database.DatabaseManager;

import java.util.ArrayList;
import java.util.Iterator;

import android.database.Cursor;

public class Events implements Iterable<Event> {
	//private Collection<Event> events = new ArrayList<Event>();
	private ArrayList<Event> events = new ArrayList<Event>();
	
	/**
	 * Initialize events
	 */
	public Events() { }

	/**
	 * Adds a new event to the events listing
	 * @param event event which is added
	 */
	public void addEvent(Event event) {
		events.add(event); 
	}

	/**
	 * Return the name of a event as a string
	 * @param id event id
	 * @return name as a string
	 */
	public String getEventName(int id) {
		for ( Event event: this ) {
			if ( event.getEID() == id ) return event.getName();
		}
		return null;
	}


	/**
	 * Read the events from somewhere
	 */
	public void initEvents(DatabaseManager dbm) {
		events = new ArrayList<Event>();
		Cursor result = dbm.getAllEvents();
		
		result.moveToFirst();


		int n = result.getCount();
		for(int i = 0; i < n; i++){
			Event event = new Event();
			
			StringBuilder sb = new StringBuilder();
			sb = new StringBuilder();
			
			String startYear = result.getString(8);
			String startMonth = result.getString(7);
			String startDay = result.getString(6);
			
			if (Integer.parseInt(startYear) < 10 && Integer.parseInt(startMonth) < 10 && Integer.parseInt(startDay) < 10)
				sb.append("0" + Integer.parseInt(startYear) + "-" + "0" + Integer.parseInt(startMonth) + "-" + "0" + Integer.parseInt(startDay));
			else
			if (Integer.parseInt(startYear) < 10 && Integer.parseInt(startMonth) < 10)
				sb.append("0" + Integer.parseInt(startYear) + "-" + "0" + Integer.parseInt(startMonth) + "-" + Integer.parseInt(startDay));
			else
			if (Integer.parseInt(startYear) < 10 && Integer.parseInt(startDay) < 10)
				sb.append("0" + Integer.parseInt(startYear) + "-" + Integer.parseInt(startMonth) + "-" + "0" + Integer.parseInt(startDay));
			else
			if (Integer.parseInt(startMonth) < 10 && Integer.parseInt(startDay) < 10)
				sb.append(Integer.parseInt(startYear) + "-" + Integer.parseInt(startMonth) + "-" + "0" + Integer.parseInt(startDay));
			else
			if (Integer.parseInt(startYear) < 10)
				sb.append("0" + Integer.parseInt(startYear) + "-" + Integer.parseInt(startMonth) + "-" + Integer.parseInt(startDay));
			else
			if (Integer.parseInt(startMonth) < 10)
				sb.append(Integer.parseInt(startYear) + "-" + "0" + Integer.parseInt(startMonth) + "-" + Integer.parseInt(startDay));
			else
			if (Integer.parseInt(startDay) < 10)
				sb.append(Integer.parseInt(startYear) + "-" + Integer.parseInt(startMonth) + "-" + "0" + Integer.parseInt(startDay));
			else
				sb.append(Integer.parseInt(startYear) + "-" + Integer.parseInt(startMonth) + "-" + Integer.parseInt(startDay));
			
			String startDate = sb.toString();
			
			
			
			
			sb = new StringBuilder();
			
			String startHour = result.getString(9);
			String startMinute = result.getString(10);

			if (Integer.parseInt(startMinute) < 10 && Integer.parseInt(startHour) < 10)
				sb.append("0" + Integer.parseInt(startHour) + ":" + "0" + Integer.parseInt(startMinute));
			else
			if (Integer.parseInt(startHour) < 10)
				sb.append("0" + Integer.parseInt(startHour) + ":" + Integer.parseInt(startMinute));
			else
			if (Integer.parseInt(startMinute) < 10)
				sb.append(Integer.parseInt(startHour) + ":" + "0" + Integer.parseInt(startMinute));
			else
				sb.append(Integer.parseInt(startHour) + ":" + Integer.parseInt(startMinute));
			
			String startTime = sb.toString();
			
			
			
			
			
			
			sb = new StringBuilder();
			
			String endYear = result.getString(13);
			String endMonth = result.getString(12);
			String endDay = result.getString(11);
			
			if (Integer.parseInt(endYear) < 10 && Integer.parseInt(endMonth) < 10 && Integer.parseInt(endDay) < 10)
				sb.append("0" + Integer.parseInt(endYear) + "-" + "0" + Integer.parseInt(endMonth) + "-" + "0" + Integer.parseInt(endDay));
			else
			if (Integer.parseInt(endYear) < 10 && Integer.parseInt(endMonth) < 10)
				sb.append("0" + Integer.parseInt(endYear) + "-" + "0" + Integer.parseInt(endMonth) + "-" + Integer.parseInt(endDay));
			else
			if (Integer.parseInt(endYear) < 10 && Integer.parseInt(endDay) < 10)
				sb.append("0" + Integer.parseInt(endYear) + "-" + Integer.parseInt(endMonth) + "-" + "0" + Integer.parseInt(endDay));
			else
			if (Integer.parseInt(endMonth) < 10 && Integer.parseInt(endDay) < 10)
				sb.append(Integer.parseInt(endYear) + "-" + Integer.parseInt(endMonth) + "-" + "0" + Integer.parseInt(endDay));
			else
			if (Integer.parseInt(endYear) < 10)
				sb.append("0" + Integer.parseInt(endYear) + "-" + Integer.parseInt(endMonth) + "-" + Integer.parseInt(endDay));
			else
			if (Integer.parseInt(endMonth) < 10)
				sb.append(Integer.parseInt(endYear) + "-" + "0" + Integer.parseInt(endMonth) + "-" + Integer.parseInt(endDay));
			else
			if (Integer.parseInt(endDay) < 10)
				sb.append(Integer.parseInt(endYear) + "-" + Integer.parseInt(endMonth) + "-" + "0" + Integer.parseInt(endDay));
			else
				sb.append(Integer.parseInt(endYear) + "-" + Integer.parseInt(endMonth) + "-" + Integer.parseInt(endDay));
			
			String endDate = sb.toString();
			
			
			
			
			sb = new StringBuilder();
			String endHour = result.getString(14);
			String endMinute = result.getString(15);
			
			
			if (Integer.parseInt(endMinute) < 10 && Integer.parseInt(endHour) < 10)
				sb.append("0" + Integer.parseInt(endHour) + ":" + "0" + Integer.parseInt(endMinute));
			else
			if (Integer.parseInt(endHour) < 10)
				sb.append("0" + Integer.parseInt(endHour) + ":" + Integer.parseInt(endMinute));
			else
			if (Integer.parseInt(endMinute) < 10)
				sb.append(Integer.parseInt(endHour) + ":" + "0" + Integer.parseInt(endMinute));
			else
				sb.append(Integer.parseInt(endHour) + ":" + Integer.parseInt(endMinute));

			String endTime = sb.toString();
			
			event.setAll(
						result.getString(0), // ID
						result.getString(1), // name
						result.getString(2), // type
						result.getString(3), // location
						result.getString(4), // locationLatitude
						result.getString(5), // locationLongitude
						startDate, // startDate
						startTime, // startTime
						endDate, // endDate
						endTime, // endTime
						result.getString(16), // description
						result.getString(17)); // local event or from website
			addEvent(event);
			result.moveToNext();
		}

	}

	/**
	 * Returns reference to i event
	 * @param i which event reference is required
	 * @return reference to event which id is i
	 * @throws IndexOutOfBoundsException if i is not acceptable
	 */
	public Event give(int i) throws IndexOutOfBoundsException  {
		return events.get(i);
		
	}

	/**
	 * Returns the amount of event as integer
	 * @return amount of events
	 */
	public int getAmount() { 
		return events.size();   
	}

	/**
	 * Iterator for all events
	 * @return event iterator
	 */
	@Override
	public Iterator<Event> iterator() {
		return events.iterator();
	}

	
	//TODO: How to find events?	
	
}

