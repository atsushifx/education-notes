package arrayandarraylist;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArrayAndArrayListTest {

	// declare myArrayAndArrayList which is the object for testing
	ArrayAndArrayList myArrayAndArrayList;
	
	@BeforeEach
	void setUp() throws Exception {
		// initialize myArrayAndArrayList for testing
		this.myArrayAndArrayList = new ArrayAndArrayList();
	}
	
	/**
	 * Test howMany method in ArrayAndArrayList.
	 */
	@Test
	void testHowMany() {
		// element in the array
		int[] array = {1, 3, 5, 7, 9, 1, 2, 3, 4, 5};
		assertEquals(2, this.myArrayAndArrayList.howMany(array, 1));
		 
		assertEquals(0, this.myArrayAndArrayList.howMany(array, -1));
		assertEquals(1, this.myArrayAndArrayList.howMany(array, 9));
		assertEquals(2, this.myArrayAndArrayList.howMany(array, 5));;
		
		
	}
	
	/**
	 * Test findMax method in ArrayAndArrayList.
	 */
	@Test
	void testFindMax() {
		// findMax in an nonEmpty array
		int[] array = {1, 3, 5, 7, 9, 1, 2, 3, 4, 5};
		assertEquals(9, this.myArrayAndArrayList.findMax(array));
		
		int[] array2 = {};
		assertEquals(-1, this.myArrayAndArrayList.findMax(array2));
		
		int[] array3 = {8};
		assertEquals(8, this.myArrayAndArrayList.findMax(array3));
		
		int[] array4 = {2, 4, 8, 12, 12, 4};
		assertEquals(12, this.myArrayAndArrayList.findMax(array4));

	}

	/**
	 * Test maxArray method in ArrayAndArrayList.
	 */
	@Test
	void testMaxArray() {
		// test a valid array. 
		int[] array = {1, 3, 5, 7, 9, 1, 2, 3, 4, 5};
		ArrayList<Integer> testArrayList = new ArrayList<Integer>();
		testArrayList.add(9);
		assertEquals(testArrayList, this.myArrayAndArrayList.maxArray(array));
		
		int[] array2 = {};
		assertNull(this.myArrayAndArrayList.maxArray(array2));
		
		int[] array3 = {6};
		ArrayList<Integer> testArrayList3 = new ArrayList<Integer>();
		testArrayList3.add(6);
		assertEquals(testArrayList3, this.myArrayAndArrayList.maxArray(array3));
		
		int[] array4 = {2, 4, 8, 12, 12, 4};
		ArrayList<Integer> testArrayList4 = new ArrayList<Integer>();
		testArrayList4.add(12);
		testArrayList4.add(12);
		assertEquals(testArrayList4, this.myArrayAndArrayList.maxArray(array4));
		
	}

	/**
	 * Test swapZero method in ArrayAndArrayList.
	 */
	@Test
	void testSwapZero() {
		// test an array containing 0
		int[] array = {0, 1, 0, 2, 0, 3, 0, 5};
		int[] testArray = {1, 2, 3, 5, 0, 0, 0, 0};
		this.myArrayAndArrayList.swapZero(array);
		assertArrayEquals(testArray, array);
		
		
		int[] array2 = {};
		int[] testArray2 = {};
		this.myArrayAndArrayList.swapZero(array2);
		assertArrayEquals(testArray2, array2);
		
		int[] array3 = {0};
		int[] testArray3 = {0};
		this.myArrayAndArrayList.swapZero(array2);
		assertArrayEquals(testArray3, array3);
		
		int[] array4 = {1, 9, 8, 4, 2};
		int[] testArray4 = {1, 9, 8, 4, 2};
		this.myArrayAndArrayList.swapZero(array2);
		assertArrayEquals(testArray4, array4);
	
	}
}
