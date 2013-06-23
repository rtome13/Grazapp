package grazapp.swipe.test;

import android.test.AndroidTestCase;
import com.jayway.android.robotium.solo.Solo;


public class UniInfoFragmentTest extends AndroidTestCase {
	private Solo solo;


	public UniInfoFragmentTest() {
		super();
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(null);
	    //getInstrumentation().waitForIdleSync();
	    assertTrue("Trying to find the UniGraz Motto", solo.searchText("Education. Research. Opportunities."));
	    assertTrue("Trying to find the UniGraz Text", solo.searchText("The University of Graz, which was founded in 1585, is Austria’s second oldest university and one of the largest in the country. Many excellent scientists, amongst them six Nobel laureates, have taught and researched here. With some 31,500 students and 3,900 employees the University of Graz contributes significantly to the vibrating life of the Styrian capital. Its location in Europe encourages a lively scientific, economic and cultural exchange with South-East Europe, from which not only the city benefits, but also its educational institutions."));
	
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
