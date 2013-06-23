package grazapp.events.test;

import grazapp.events.Event;
import junit.framework.TestCase;

public class EventTest extends TestCase {

	public EventTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}


	
	/** testEventID */
	public void testEventID() {    
		Event event = new Event(); 
		assertEquals("Is EID for event 0", 0, event.getEID()); 
		event.setEID(1); 
		Event event2 = new Event(); 
		assertEquals("Is EID for event2 0", 0, event2.getEID()); 
		event2.setEID(2); 
		assertEquals("Is EID for event the same as EID for event2 - 1", (event2.getEID() - 1), event.getEID()); 
	} 



	/** testSetName */
	public void testSetName() {    
		Event event = new Event();
		assertEquals("Is eventName null", null, event.getName()); 
		event.setName("MegaParty"); 
		assertEquals("Is eventName MegaParty", "MegaParty" , event.getName()); 
	} 



	/** testSetType */
	public void testSetType() {    
		Event event = new Event();
		assertEquals("Is eventType null", null, event.getType()); 
		event.setType("Sports"); 
		assertEquals("Is eventName Sports", "Sports" , event.getType()); 
	} 


	/** testSetStartDate */
	public void testSetStartDate() {    
		Event event = new Event();
		assertEquals("Is eventStartDate null", null, event.getStartDate()); 
		event.setStartDate("2000-01-01"); 
		assertEquals("Is eventStartDate 2000-01-01", "2000-01-01" , event.getStartDate()); 
	} 



	/** testSetStartTime */
	public void testSetStartTime() {    
		Event event = new Event();
		assertEquals("Is eventStartTime null", null, event.getStartTime()); 
		event.setStartTime("00:00"); 
		assertEquals("Is eventStartTime 00:00", "00:00" , event.getStartTime()); 
	} 



	/** testSetEndDate */
	public void testSetEndDate() {    
		Event event = new Event();
		assertEquals("Is eventEndDate null", null, event.getEndDate()); 
		event.setEndDate("2010-01-01"); 
		assertEquals("Is eventEndDate 2010-01-01", "2010-01-01" , event.getEndDate()); 
	} 


	/** testSetEndTime */
	public void testSetEndTime() {    
		Event event = new Event(); 
		assertEquals("Is eventEndTime null", null, event.getEndTime()); 
		event.setEndTime("00:00"); 
		assertEquals("Is eventEndTime 00:00", "00:00" , event.getEndTime()); 
	} 



	/** testSetDescription */
	public void testSetDescription() {    
		Event event = new Event();
		assertEquals("Is eventDescription null", null, event.getDescription()); 
		event.setDescription("Too lazy, just party"); 
		assertEquals("Is eventDescription Too lazy, just party", "Too lazy, just party" , event.getDescription()); 
	} 

	/** testSetLocation */
	public void testSetLocation() {    
		Event event = new Event();
		assertEquals("Is eventLocation null", null, event.getLocation()); 
		event.setLocation("Inffeldgasse 16c"); 
		assertEquals("Is eventLocation Inffeldgasse 16c", "Inffeldgasse 16c", event.getLocation()); 
	} 


	public final void testSetAll() {
		Event event = new Event();
		event.setAll("1", "name", "type", "location", "locationLatitude", "locationLongitude", "startDate", "startTime", "endDate", "endTime", "description", "source");
		//assertEquals("Is eID '1'", "1", event.getEID());
		assertEquals("Is name 'name'", "name", event.getName());
		assertEquals("Is type 'type'", "type", event.getType());
		assertEquals("Is location 'location'", "location", event.getLocation());
		event.setLocationLatitude("newLat");
		assertEquals("Is locationLatitude 'newLat'", "newLat", event.getLocationLatitude());
		event.setLocationLongitude("newLong");
		assertEquals("Is locationLongitude 'newLong'", "newLong", event.getLocationLongitude());
		assertEquals("Is startDate 'startDate'", "startDate", event.getStartDate());
		assertEquals("Is startTime 'startTime'", "startTime", event.getStartTime());
		assertEquals("Is endDate 'endDate'", "endDate", event.getEndDate());
		assertEquals("Is endTime 'endTime'", "endTime", event.getEndTime());
		assertEquals("Is description 'description'", "description", event.getDescription());
		event.setSource("newSource");
		assertEquals("Is source 'newSource'", "newSource", event.getSource());
	}



	/** testParse */
	public void testParse() {   
		Event event = new Event();
		event.parse("5;70s Party;Party;Moserhofgasse 41b, Common Room;LocationLat;LocationLong;2013-06-01;18:00;2013-06-02;03:30;You better go see the doctor because you have a serious case of disco fever. Let's groove tonight!"); 
		assertEquals("Is event toString the same", "70s Party: 2013-06-01 - 18:00", event.toString()); 
		assertEquals("Is EID for event 5", 5, event.getEID()); 
		assertEquals("Is eventLocation for event Moserhofgasse 41b", "Moserhofgasse 41b, Common Room", event.getLocation()); 
	} 

}
