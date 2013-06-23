package grazapp.swipe.test;

import grazapp.main.MainActivity;
import android.test.ActivityInstrumentationTestCase2;

import com.jayway.android.robotium.solo.Solo;


public class EventsListTest extends ActivityInstrumentationTestCase2<MainActivity> {
	private Solo solo;
	
	public EventsListTest() {
		super(MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testEventsList() {
		assertEquals("Is there an event called Latin Night", solo.searchText("Latin night")); 
		assertEquals("Is there an event called Kebab marathon", solo.searchText("Kebab marathon"));
	}
}
