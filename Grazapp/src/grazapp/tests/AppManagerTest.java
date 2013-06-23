package grazapp.tests;

import static org.junit.Assert.*;

import grazapp.database.DatabaseManager;
import grazapp.events.Event;
import grazapp.main.AppManager;

import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AppManagerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testAppManager() {
		fail("Not yet implemented");
	}

	@Test
	public final void testGetEventCount() {
		fail("Not yet implemented");
	}

	@Test
	public final void testAdd() {
		AppManager am = new AppManager();
		Event event1 = new Event();
		am.add(event1);
		int numEvents = am.getEventCount();
		assertEquals("verify if the event was added", 1, numEvents);
		
		Event eventRetrieved = am.getEvent(0);
		assertEquals("verify if we can get the right event", event1, eventRetrieved);
	}

	@Test
	public final void testIterator() {
		fail("Not yet implemented");
	}

	@Test
	public final void testEventIterator() {
		fail("Not yet implemented");
	}

	@Test
	public final void testGetEvent() {
		AppManager am = new AppManager();
		Event event1 = new Event();
		am.add(event1);
		int numEvents = am.getEventCount();
		
		Event eventRetrieved = am.getEvent(0);
		assertEquals("verify if we can get the right event", event1, eventRetrieved);
	}

	@Test
	public final void testGetAllEvents() {
		AppManager am = new AppManager();
		Event event1 = new Event();
		am.add(event1);
		int numEventsInserted = am.getEventCount();
		
		Event[] eventsRetrieved = am.getAllEvents();
		int numEventsRetrieved = eventsRetrieved.length;
		assertEquals("verify if we can get the right event", numEventsInserted, numEventsRetrieved);
	}

	@Test
	public final void testReadFromDatabase() {
		fail("Not yet implemented");
	}

	@Test
	public final void testGetDate() {
		AppManager am = new AppManager();
		
		Calendar calendar = Calendar.getInstance();
		int currentDay = calendar.get(Calendar.DATE); 
		int currentMonth = (calendar.get(Calendar.MONTH))+1; 
		int currentYear = calendar.get(Calendar.YEAR);
		
		String date = am.getDate();
		String[] splitDate = date.split("-");
		int year = Integer.parseInt(splitDate[0]);
		int month = Integer.parseInt(splitDate[1]);
		int day = Integer.parseInt(splitDate[2]);
		
		assertEquals("check if the year is the current year", currentYear, year);
		assertEquals("check if the year is the current month", currentMonth, month);
		assertEquals("check if the year is the current day", currentDay, day);
	}

}
