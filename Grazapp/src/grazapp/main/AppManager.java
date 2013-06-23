package grazapp.main;

import grazapp.database.DatabaseManager;
import grazapp.events.Event;
import grazapp.events.Events;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;

/**
 * EventManager, handles everything below it 
 */
public class AppManager implements Iterable<Event>{
	private static Events events = new Events();
	public DatabaseManager dbm;

	public AppManager(){
	}
	

	
	public AppManager(DatabaseManager dbm, String situation){
		this.dbm = dbm;
		if(situation.equals("addEvent"))
			readFromDatabase();
		
	}
	

	/**
	 * Get the amount of events
	 * @return amount of events
	 */
	public int getEventCount() { 
		return events.getAmount();       
	}

	/**
	 * Adds a new event
	 * @param event to be added
	 */
	public void add(Event event) { 
		events.addEvent(event);     
	}
	

	/**
	 * Iterator for all events
	 * @return event iterator
	 */ 
	@Override
	public Iterator<Event> iterator() { 
		return eventIterator();       
	}

	/**
	 * Iterator for all events
	 * @return event iterator
	 */
	public static Iterator<Event> eventIterator() { 
		return events.iterator();       
	}

	/**
	 * Returns the #i event
	 * @param i which event is returned
	 * @return reference to event i
	 * @throws IndexOutOfBoundsException if faulty i
	 */
	public Event getEvent(int i) throws IndexOutOfBoundsException { 
		return events.give(i);           
	}
	
	public Event[] getAllEvents() {
		Event[] eventsArray = new Event[getEventCount()];
		for (int i = 0; i < eventsArray.length; i++) {
			eventsArray[i] = getEvent(i);
		}
		return eventsArray;
	}

	/**
	 * Read events from the database/file
	 */
	public void readFromDatabase() {
		events.initEvents(dbm);
	}


	
	
	//TODO: How to find events?
	/**
	 * Returns a collection of references of the found events
	 * @param parameter search parameter
	 * @param k index of the field what is searched
	 * @return collection of found events
	 
	public Collection<Event> find(String parameter,int k) {
		

	}
	*/

	/**
	 * Get the current date
	 * @return current date
	 */
	public String getDate() {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DATE); 
		int month = (calendar.get(Calendar.MONTH))+1; 
		int year = calendar.get(Calendar.YEAR);
		return year + "-" + month + "-" + day;	
	}



}