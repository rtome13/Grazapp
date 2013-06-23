package grazapp.tests;

import grazapp.events.Event;
import grazapp.events.Events;
import junit.framework.TestCase;

public class EventsTest extends TestCase {

	public EventsTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testEvents() {
		fail("Not yet implemented");
	}

	public void testAddEvent() {
		//Event newEvent = new Event();
		//newEvent.parse("5;70s Party;Party;Moserhofgasse 41b, Common Room;2013-06-01;18:00;2013-06-02;03:30;You better go see the doctor because you have a serious case of disco fever. Let's groove tonight!"); 
		
		fail("Not yet implemented");
	}
 
	public void testGetEventName() {
		Events eventTest = new Events();
		assertEquals("No results returns 'null'", null, eventTest.getEventName(1));
		Event newEvent = new Event();
		newEvent.setEID(1);
		newEvent.setName("Megaparty");
		//newEvent.parse("1;Megaparty;Party;Location;LocationLat;LocationLong;StartDate;StartTime;EndDate;EndTime;Description");
		eventTest.addEvent(newEvent); 
		assertEquals("Is name for event 'Megaparty'", "Megaparty", eventTest.getEventName(1));
		//fail("Not yet implemented");
	}

	public void testInitEvents() {
		fail("Not yet implemented");
	}

	public void testGive() {
		fail("Not yet implemented");
	}

	public void testGetAmount() {
		Events events = new Events();
		assertEquals("Size is 0", 0, events.getAmount());
		//fail("Not yet implemented");
	}

	public void testIterator() {
		fail("Not yet implemented");
	}

}
