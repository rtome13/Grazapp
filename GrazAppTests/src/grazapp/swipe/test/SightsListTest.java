package grazapp.swipe.test;

import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;
import grazapp.main.MainActivity;
import grazapp.sights.Sight;
import grazapp.swipe.SightsList;

public class SightsListTest extends ActivityInstrumentationTestCase2<MainActivity> {
	private Solo solo;
	
	public SightsListTest() {
		super(MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testFindPamukkale() {
		solo = new Solo(getInstrumentation(), getActivity());
		assertTrue("Find Pamukkale", solo.searchText("Pamukkale Kebap"));
	}
	
	public final void testPopulateSights() {
	    SightsList sightlist = new SightsList(); 
	    Sight[] sights = sightlist.getSights();
		assertEquals("Is sightlist length 10", 10, sights.length); 
		Sight sight1 = sights[0];
		Sight sight2 = sights[5];
		assertEquals("Is sight 1 the clocktower of graz", "The Clock tower of Graz", sight1.getName()); 
		assertEquals("Is sight 6 pamukkale", "Pamukkale Kebap", sight2.getName()); 
	}

}
