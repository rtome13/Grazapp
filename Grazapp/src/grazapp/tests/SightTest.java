package grazapp.tests;

import grazapp.sights.Sight;
import junit.framework.TestCase;

public class SightTest extends TestCase {

	public SightTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}



	public void testSetAll() {
		Sight sight = new Sight("sight");
		sight.setAll(sight.getName(), "type", "location", "locationLatitude", "locationLongitude", "openingHours", "closingHours", "description");
		assertEquals("Is name 'sight'", "sight", sight.getName());
		sight.setName("newName");
		assertEquals("Is new name 'newName'", "newName", sight.getName());
		sight.setType("newType");
		assertEquals("Is new type 'newType'", "newType", sight.getType());
		sight.setLocation("newLocation");
		assertEquals("Is new location 'newLocation'", "newLocation", sight.getLocation());
		sight.setLocationLatitude("newLatCoord");
		assertEquals("Is new lat 'newLatCoord'", "newLatCoord", sight.getLocationLatitude());
		sight.setLocationLongitude("newLongCoord");
		assertEquals("Is new long 'newLongCoord'", "newLongCoord", sight.getLocationLongitude());
		sight.setOpeningHours("newOpen");
		assertEquals("Is new open hours 'newOpen'", "newOpen", sight.getOpeningHours());
		sight.setClosingHours("newClose");
		assertEquals("Is new close hours 'newClose'", "newClose", sight.getClosingHours());
		sight.setDescription("newDescription");
		assertEquals("Is new description 'newDescription'", "newDescription", sight.getDescription());
//		fail("Not yet implemented");
	}

}
