package com.fromdev.automation.feed.model;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		Feed feed = new Feed("Test", "http://mytesturl.com",
				"http://myfeedurl.com/feed");

		for (int i = 0; i < 10; i++) {
			ShareableItem item = new ShareableItem();
			item.setDescription("test desc " + i);
			item.setUrl("http://www.fromdev.com/item/" + i);
			;
			feed.addItem(item);

		}
		assertNotNull(feed.toRss());
		System.out.println(feed.toRss());

	}
}
