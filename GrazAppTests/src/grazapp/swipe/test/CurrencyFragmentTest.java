package grazapp.swipe.test;

import java.util.ArrayList;

import grazapp.swipe.CurrencyFragment;

import junit.framework.TestCase;

public class CurrencyFragmentTest extends TestCase {
	//private Solo solo;
	private CurrencyFragment cv;
	private String idents[];

	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testInitCurrencyCalculator() {
		fail("Not yet implemented"); // TODO
	}

	public final void testRetrieveRates() {
		fail("Not yet implemented"); // TODO
	}

	public final void testGetCurr() {
		fail("Not yet implemented"); // TODO
	}

	public final void testDoubleRates() {
		fail("Not yet implemented"); // TODO
	}

	public final void testGetCurrencyId() {
		fail("Not yet implemented"); // TODO
	}

	public final void testConvert() {
		fail("Not yet implemented"); // TODO
	}

	public final void testIdentsAsList() {
		String idents2[] = {"EUR","USD","GBP"};
		idents = idents2;
		assertEquals("Is EUR the first item in idents array'", "EUR", idents[0]);
		assertEquals("Is GBP the third item in idents array'", "GBP", idents[2]);
		ArrayList<String> identsAsAList = cv.identsAsList();
		assertEquals("Is EUR the first item in idents ArrayList'", "EUR", identsAsAList.get(0));
		assertEquals("Is GBP the third item in idents ArrayList'", "GBP", identsAsAList.get(2));
	}

	public final void testAddItemsOnFromSpinner() {
		fail("Not yet implemented"); // TODO
	}

	public final void testAddItemsOnToSpinner() {
		fail("Not yet implemented"); // TODO
	}

}
