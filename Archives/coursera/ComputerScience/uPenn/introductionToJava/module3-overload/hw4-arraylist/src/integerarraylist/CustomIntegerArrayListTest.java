// package integerarraylist;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class CustomIntegerArrayListTest {

	@Test
	void testGetArrayList() {
		
		//create a new empty CustomIntegerArrayList
		CustomIntegerArrayList arr1 = new CustomIntegerArrayList();
		arr1.add(2);
		arr1.add(0, 5);
		arr1.add(1, 5);
		
		ArrayList<Integer> lst1 = new ArrayList<Integer>();
		lst1.add(2);
		lst1.add(0, 5);
		lst1.add(1, 5);
		
		
		assertEquals(lst1, arr1.getArrayList());
		
		
		ArrayList<Integer> arr4Elements = new ArrayList<Integer>();
		arr4Elements.add(100);
		arr4Elements.add(200);
		arr4Elements.add(500);
		
		//create a new CustomIntegerArrayList with the elements in the given ArrayList
		CustomIntegerArrayList arr4 = new CustomIntegerArrayList(arr4Elements);

		ArrayList<Integer> lst4 = new ArrayList<Integer>();
		lst4.add(100);
		lst4.add(200);
		lst4.add(500);

		assertEquals(lst4, arr4.getArrayList());
		
		// user to do impl
		
		CustomIntegerArrayList arrNull= new CustomIntegerArrayList();
		ArrayList<Integer> lstNull = new ArrayList<Integer>();
		assertEquals(lstNull, arrNull.getArrayList());
		
		
		// 初期値付き
		CustomIntegerArrayList arr2 = new CustomIntegerArrayList(new ArrayList<>(Arrays.asList(1)));
		ArrayList<Integer> lst2 = new ArrayList<>(Arrays.asList(6));
		assertNotEquals(lst2, arr2.getArrayList());
		
		
		CustomIntegerArrayList arr3 = new CustomIntegerArrayList(new ArrayList<>(Arrays.asList(78, 8)));
		ArrayList<Integer> lst3 = new ArrayList<>(Arrays.asList(78, 8));
		assertEquals(lst3, arr3.getArrayList());
	}

	@Test
	void testGet() {
		CustomIntegerArrayList arr1 = new CustomIntegerArrayList();
		arr1.add(2);
		arr1.add(0, 5);
		arr1.add(1, 5);
		
		ArrayList<Integer> lst1 = new ArrayList<Integer>();
		lst1.add(2);
		lst1.add(0, 5);
		lst1.add(1, 5);

		assertEquals((int)lst1.get(0), arr1.get(0));
		assertEquals((int)lst1.get(1), arr1.get(1));
		assertEquals((int)lst1.get(2), arr1.get(2));

		// user implementsassertThrows(Exception.class, () -> arrNull.get(0));
		
		CustomIntegerArrayList arrNull= new CustomIntegerArrayList();
		assertThrows(IndexOutOfBoundsException.class, () -> arrNull.get(0));
		assertThrows(IndexOutOfBoundsException.class, () -> arrNull.get(-1));
		
		
		CustomIntegerArrayList arr2 = new CustomIntegerArrayList(new ArrayList<>(Arrays.asList(23)));
		assertEquals(23, arr2.get(0));
		assertThrows(IndexOutOfBoundsException.class, () -> arrNull.get(8));
	}

	@Test
	void testAddInt() {
		
		CustomIntegerArrayList arr1 = new CustomIntegerArrayList();
		arr1.add(2);
		arr1.add(3);
		arr1.add(4);
		
		ArrayList<Integer> lst1 = new ArrayList<Integer>();
		lst1.add(2);
		lst1.add(3);
		lst1.add(4);

		assertEquals((int)lst1.get(0), arr1.get(0));
		assertEquals((int)lst1.get(1), arr1.get(1));
		assertEquals((int)lst1.get(2), arr1.get(2));

		// user implement 
		CustomIntegerArrayList arr2 = new CustomIntegerArrayList(new ArrayList<>(Arrays.asList(8, 9)));
		arr2.add(-8);
		arr2.add(-6);
		
		ArrayList<Integer> lst2 = new ArrayList<Integer>(Arrays.asList(22, 8));
		lst2.add(-8);
		lst2.add(-6);
		
		assertEquals((int)lst2.get(2), arr2.get(2));
		assertEquals((int)lst2.get(3), arr2.get(3));
		
		CustomIntegerArrayList arr3 = new CustomIntegerArrayList();
		arr3.add(0, 100);
		arr3.add(1, 200);
		arr3.add(1, 500);
		
		ArrayList<Integer> lst3 = new ArrayList<Integer>();
		lst3.add(0, 100);
		lst3.add(1, 200);
		lst3.add(1, 500);

		assertEquals((int)lst3.get(1), arr3.get(1));
		assertEquals((int)lst3.get(2), arr3.get(2));
	}

	@Test
	void testAddIntInt() {
		
		CustomIntegerArrayList arr1 = new CustomIntegerArrayList();
		arr1.add(0, 2);
		arr1.add(0, 3);
		arr1.add(0, 4);
		
		ArrayList<Integer> lst1 = new ArrayList<Integer>();
		lst1.add(0, 2);
		lst1.add(0, 3);
		lst1.add(0, 4);

		assertEquals((int)lst1.get(0), arr1.get(0));
		assertEquals((int)lst1.get(1), arr1.get(1));
		assertEquals((int)lst1.get(2), arr1.get(2));
		
		
		// user implement
		CustomIntegerArrayList arr2 = new CustomIntegerArrayList(new ArrayList<>(Arrays.asList(8, 9)));
		arr2.add(0, -8);
		arr2.add(1, -6);
		arr2.add(1, -4);
		
		ArrayList<Integer> lst2 = new ArrayList<Integer>(Arrays.asList(22, 8));
		lst2.add(0, -8);
		lst2.add(1, -6);
		lst2.add(1, -4);
		
		assertEquals((int)lst2.get(0), arr2.get(0));
		assertEquals((int)lst2.get(1), arr2.get(1));
		assertEquals((int)lst2.get(2), arr2.get(2));		
	}

	@Test
	void testRemoveInt() {

		CustomIntegerArrayList arr1 = new CustomIntegerArrayList();
		arr1.add(0, 2);
		arr1.add(0, 3);
		arr1.add(0, 4);
		arr1.remove(0);
		arr1.remove(1);
		
		ArrayList<Integer> lst1 = new ArrayList<Integer>();
		lst1.add(0, 2);
		lst1.add(0, 3);
		lst1.add(0, 4);
		lst1.remove(0);
		lst1.remove(1);
		
		assertEquals((int)lst1.get(0), arr1.get(0));

		// user implement test case 
		CustomIntegerArrayList arrNull = new CustomIntegerArrayList();
		assertThrows(IndexOutOfBoundsException.class, () -> arrNull.remove(0));
	
		CustomIntegerArrayList arr2 = new CustomIntegerArrayList(new ArrayList<>(Arrays.asList(100, 500, 200)));
		assertEquals(100, arr2.remove(0));
		assertEquals(200, arr2.remove(1));
		
		assertThrows(IndexOutOfBoundsException.class, () -> arr2.remove(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> arr2.remove(8));

		
	}

	@Test
	void testRemoveIntInt() {

		CustomIntegerArrayList arr1 = new CustomIntegerArrayList();
		arr1.add(0, 2);
		arr1.add(0, 3);
		arr1.add(0, 3);
		arr1.add(0, 3);
		arr1.add(0, 3);
		arr1.add(0, 4);
		arr1.remove(3, 3);
		
		ArrayList<Integer> lst1 = new ArrayList<Integer>();
		lst1.add(0, 2);
		lst1.add(0, 3);
		lst1.add(0, 4);
		
		assertEquals(lst1, arr1.getArrayList());

		// user implement test case
		// 全削除
		CustomIntegerArrayList arr2 = new CustomIntegerArrayList(new ArrayList<>(Arrays.asList(100, 100, 100)));
		arr2.remove(4, 100);
		ArrayList<Integer> lst2 = new ArrayList<Integer>();
		assertEquals(lst2, arr2.getArrayList());
		
		// 0回削除
		CustomIntegerArrayList arr3 = new CustomIntegerArrayList(new ArrayList<>(Arrays.asList(4, 4, 4, 4, 4)));
		arr3.remove(0, 4);
		ArrayList<Integer> lst3 = new ArrayList<Integer>(Arrays.asList(4, 4, 4, 4, 4));
		assertEquals(lst3, arr3.getArrayList());
		
		// no match
		CustomIntegerArrayList arr4 = new CustomIntegerArrayList(new ArrayList<>(Arrays.asList(3, 9, 12, 7, 0)));
		arr3.remove(1, -8);
		ArrayList<Integer> lst4 = new ArrayList<Integer>(Arrays.asList(3, 9, 12, 7, 0));
		assertEquals(lst4, arr4.getArrayList());
			
	}

	@Test
	void testSpliceIntInt() {

		CustomIntegerArrayList arr1 = new CustomIntegerArrayList();
		arr1.add(2);
		arr1.add(3);
		arr1.add(4);
		arr1.add(5);
		arr1.add(6);
		arr1.splice(3, 2);
		
		ArrayList<Integer> lst1 = new ArrayList<Integer>();
		lst1.add(2);
		lst1.add(3);
		lst1.add(4);

		assertEquals((int)lst1.get(0), arr1.get(0));
		assertEquals((int)lst1.get(1), arr1.get(1));
		assertEquals((int)lst1.get(2), arr1.get(2));

		// user implement yest case
		CustomIntegerArrayList arr2 = new CustomIntegerArrayList(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5)));
		ArrayList<Integer> act2 = arr2.splice(1, 2);
		ArrayList<Integer> lst2 = new ArrayList<Integer>(Arrays.asList(1, 4, 5));
				
		assertEquals(lst2, act2);
		
		CustomIntegerArrayList arr3 = new CustomIntegerArrayList(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5)));
		ArrayList<Integer> lstNull = new ArrayList<Integer>();
		
		assertEquals(lstNull, arr3.splice(-1, 1));
		assertEquals(lstNull, arr3.splice(11, 1));
		assertEquals(lstNull, arr3.splice(0, 0));
	
		
		ArrayList<Integer> act3 = arr3.splice(3,  4);
		ArrayList<Integer> lst3 = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
		assertEquals(lst3,  act3);
		
	}

	@Test
	void testSpliceIntIntIntArray() {
		CustomIntegerArrayList arr1 = new CustomIntegerArrayList();
		arr1.add(2);
		arr1.add(3);
		arr1.add(4);
		arr1.add(5);
		arr1.add(6);
		arr1.splice(3, 1, new int[] { 4, 5 });
		
		
		ArrayList<Integer> lst1 = new ArrayList<Integer>();
		lst1.add(2);
		lst1.add(3);
		lst1.add(4);
		lst1.add(4);
		lst1.add(5);
		lst1.add(6);
		
		assertEquals(lst1, arr1.getArrayList());

		// user implement yest case
		ArrayList<Integer> lstNull = new ArrayList<Integer>();
		
		// invalid param
		assertEquals(lstNull, arr1.splice(-1, 1, new int[]{ 4, 5}));
		assertEquals(lstNull, arr1.splice(99, 4, new int[] {4, 5}));
		assertEquals(lstNull, arr1.splice( 0, 0, new int[] {4, 5}));
		
		
		CustomIntegerArrayList arr3 = new CustomIntegerArrayList(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5)));
		ArrayList<Integer> act3 = arr3.splice(1,  2, new int[] {6, 7});
		ArrayList<Integer> exp3 = new ArrayList<>(Arrays.asList(1, 6, 7, 4, 5));
		assertEquals(exp3, act3);
		
	}
}
