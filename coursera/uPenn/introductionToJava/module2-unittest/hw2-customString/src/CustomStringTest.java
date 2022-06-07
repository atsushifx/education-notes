

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomStringTest {

	//declare custom string for testing
	CustomString myCustomString;

	@BeforeEach
	public void setUp() throws Exception {
		//initialize custom string for testingi
		this.myCustomString = new CustomString();
	}

	@Test
	void testGetString() {

		//string should be null to start, before setting it
		assertNull(this.myCustomString.getString());

		this.myCustomString.setString("hello");
		assertEquals("hello", this.myCustomString.getString());

		// TODO write at least 3 additional test cases
		this.myCustomString.setString(null);
		assertNull(this.myCustomString.getString(), "set null in myCustomString");

		this.myCustomString.setString("hello, it's nice day!!");
		assertNotEquals("hello", this.myCustomString.getString());

		this.myCustomString.setString("good bye");
		 assertEquals("good bye", this.myCustomString.getString());


	}

	@Test
	void testSetString() {

		//string should be null to start, before setting it
		assertNull(this.myCustomString.getString());

		this.myCustomString.setString("Good-bye!");
		assertEquals("Good-bye!", this.myCustomString.getString());
		assertTrue(this.myCustomString.isSet, "set string");

		// TODO write at least 3 additional test cases
		this.myCustomString.setString(null);
		assertNull(this.myCustomString.getString(), "set string to null");
		assertFalse(this.myCustomString.isSet, "strinf not set");

		this.myCustomString.setString("good day");
		assertNotEquals("good", this.myCustomString.getString());
	}

	@Test
	void testRemove() {
		assertEquals("", this.myCustomString.remove(""));

		this.myCustomString.setString(null);
		assertEquals("", this.myCustomString.remove(""));

		this.myCustomString.setString("my lucky numbers are 6, 8, and 19.");
		assertEquals("my lucky numbes e 6, 8, nd 19.", this.myCustomString.remove("ra6"));

		// TODO write at least 3 additional test cases
		this.myCustomString.setString("");
		assertEquals("", this.myCustomString.remove(""));

		this.myCustomString.setString("my lucky numbers are 6, 8, and 19.");
		assertEquals("my lucky numbers are 6, 8, and 19.", this.myCustomString.remove(""));
		assertEquals("my lucky numbers are 6, 8, and 19.", this.myCustomString.remove("6,."));
	}

	@Test
	void testReverse() {
		assertEquals("", this.myCustomString.reverse(""));

		this.myCustomString.setString(null);
		assertEquals("", this.myCustomString.reverse(""));

		this.myCustomString.setString("abc, XYZ; 123.");
		assertEquals("aBC, xyz; 123.", this.myCustomString.reverse("bcdxyz@3210."));

		// TODO write at least 3 additional test cases
		assertEquals("abc, XYZ; 123.", this.myCustomString.reverse(null));
		assertEquals("abc, XYZ; 123.", this.myCustomString.reverse(""));
		assertEquals("ABC, XYZ; 123.", this.myCustomString.reverse("ABC"));


	}

	@Test
	void testFilterLetters() {
		assertEquals("", this.myCustomString.filterLetters('E', false));

		this.myCustomString.setString(null);
		assertEquals("", this.myCustomString.filterLetters('E', false));

		this.myCustomString.setString("");
		assertEquals("", this.myCustomString.filterLetters('E', false));

		// filter
		this.myCustomString.setString("Abcdefgh");
		assertEquals("", this.myCustomString.filterLetters('/', false));

		assertEquals("defgh", this.myCustomString.filterLetters('C', false));
		assertEquals("Ab", this.myCustomString.filterLetters('C', true));
	}
}
