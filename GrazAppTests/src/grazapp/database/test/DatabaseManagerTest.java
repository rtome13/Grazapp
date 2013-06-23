package grazapp.database.test;

import grazapp.database.DatabaseManager;
import android.database.Cursor;
import android.test.AndroidTestCase;

public class DatabaseManagerTest extends AndroidTestCase {
	private DatabaseManager dbm;
	
	public DatabaseManagerTest(){}

	protected static void setUpBeforeClass() throws Exception {
	}

	protected static void tearDownAfterClass() throws Exception {
	}

	protected void setUp() throws Exception {
        dbm = new DatabaseManager(getContext());
	}

	protected void tearDown() throws Exception {
        dbm.close(); 
        super.tearDown();
	}

	public final void testDatabaseManager() {
		fail("Not yet implemented");
	}

	public final void testOnCreateSQLiteDatabase() {
		fail("Not yet implemented");
	}

	public final void testOnUpgradeSQLiteDatabaseIntInt() {
		fail("Not yet implemented");
	}

	public final void testResetDatabase() {
		fail("Not yet implemented");
	}

	public final void testAddEvent() {
		dbm.addEvent("Event1", "Party", "Graz", "47.059523", "15.45517", 2013, 06, 12, 18, 00, 2013, 06, 13, 03, 59,"great party!", "website");
		Cursor result = dbm.getAllEvents();
		int numEvents = result.getCount();
		assertEquals("check if the event was inserted", 1, numEvents);
	}

	public final void testGetAllEvents() {
		dbm.addEvent("Event1", "Party", "Graz", "47.059523", "15.45517", 2013, 06, 12, 18, 00, 2013, 06, 13, 03, 59,"great party!", "website");
		dbm.addEvent("Event2", "Party", "Graz", "47.059523", "15.45517", 2013, 06, 12, 18, 00, 2013, 06, 13, 03, 59,"great party!", "website");
		
		Cursor result = dbm.getAllEvents();
		int numEvents = result.getCount();
		assertEquals("check if the two events are returned", 2, numEvents);
	}

	public final void testRemoveWebsiteEvents() {
		dbm.addEvent("Event1", "Party", "Graz", "47.059523", "15.45517", 2013, 06, 12, 18, 00, 2013, 06, 13, 03, 59,"great party!", "website");
		dbm.addEvent("Event2", "Party", "Graz", "47.059523", "15.45517", 2013, 06, 12, 18, 00, 2013, 06, 13, 03, 59,"great party!", "website");
		dbm.addEvent("Event3", "Party", "Graz", "47.059523", "15.45517", 2013, 06, 12, 18, 00, 2013, 06, 13, 03, 59,"great party!", "local");
		dbm.addEvent("Event4", "Party", "Graz", "47.059523", "15.45517", 2013, 06, 12, 18, 00, 2013, 06, 13, 03, 59,"great party!", "local");
		
		Cursor result = dbm.getAllEvents();
		int numEvents = result.getCount();
		assertEquals("check if the all 4 events were inserted", 4, numEvents);
		
		Cursor resultAfterRemoval = dbm.getAllEvents();
		int numEventsAfterRemoval = resultAfterRemoval.getCount();
		assertEquals("check if there are only two events in the database", 2, numEventsAfterRemoval);
	}

	public final void testGetLastId() {
		dbm.addEvent("Event1", "Party", "Graz", "47.059523", "15.45517", 2013, 06, 12, 18, 00, 2013, 06, 13, 03, 59,"great party!", "website");
		dbm.addEvent("Event2", "Party", "Graz", "47.059523", "15.45517", 2013, 06, 12, 18, 00, 2013, 06, 13, 03, 59,"great party!", "website");
		dbm.addEvent("Event3", "Party", "Graz", "47.059523", "15.45517", 2013, 06, 12, 18, 00, 2013, 06, 13, 03, 59,"great party!", "local");
		dbm.addEvent("Event4", "Party", "Graz", "47.059523", "15.45517", 2013, 06, 12, 18, 00, 2013, 06, 13, 03, 59,"great party!", "local");
				
		int lastId = Integer.parseInt(dbm.getLastId());
		assertEquals("check if there are only two events in the database", 3, lastId);
	}

}
