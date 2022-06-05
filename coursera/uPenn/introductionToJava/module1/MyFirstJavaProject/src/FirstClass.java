import java.util.Scanner;

/**
 * MY first Java Class.
 * @author lbrandon
 *
 */
public class FirstClass {

	/**
	 * This is the main method, the entry point to any Java program.
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Printing using SOPL: Short for System.out.println()
		System.out.println("Hello world!");
		System.out.println(); //print a blank line
		
		/*
		 * Defining variables
		 */
		
		int x = 5;
		
		double y = 5.0;
		
		boolean flag = true;
		
		System.out.println("x: " + x);
		System.out.println("y: " + y);
		System.out.println("flag: " + flag);
		
		/*
		 * Strings and characters
		 */
		
		String dept = "cit"; //String
		char letter = 'a'; //char
		
		String course = dept + 590; //String with int
		String grade = letter + ""; //char with String
		
		String courseInformation = course + ": " + grade;
		System.out.println(courseInformation);
		
		/*
		 * Math operations
		 */
		
		double d = 2 * x + 10;
		double z = 2 * y + 5;
		
		System.out.println();
		System.out.println("d: " + d);
		System.out.println("z: " + z);
		
		
		//division with ints
		//uses integer division and ignores the remainder
		System.out.println("x / 2: " + (x / 2));
		
		//division with floats
		System.out.println("x / 2.0: " + (x / 2.0));
		
		//power operation
		System.out.println("x pow 4: " + Math.pow(x, 4));
		
		/*
		 * String operations
		 */
		
		//String concatenation
		String fullName = "Brandon" + " " + "Lee" + " " + "Krakowsky";
		
		//String method for converting to upper-case
		String fullNameUpper = fullName.toUpperCase();
		
		System.out.println(fullNameUpper);
		
		/*
		 * Conditionals and loops
		 */
		
		//Conditional checking if x is even, using the modulus or % operator
		System.out.println(); //blank line
		System.out.println("x: " + x);
				
		if (x % 2 == 0) {
			System.out.println(x + " is even");
		} else {
			System.out.println(x + " is odd");
		}
		
		double e = 2.3;
		double f = 2.4;
		double g = 2.5;
		
		//boolean operators
		//&& (and) - true only if both operands are true
		System.out.println(); //blank line
		if (e > 2 && e < f) {
			System.out.println(e + " is between 2 and " + f);
		}
		
		//|| (or) - true if either operand is true
		if (f > e || f > g) {
			System.out.println(f + " is greater than " + e + " or greater than " + g);
		}
		
		//! (not) - reverses the truth value of its one operand
		if (g != 2.6) {
			System.out.println(g + " is not equal to 2.6");
		}
		
		//while loops
		int i = 0;
		
		System.out.println(); //blank line
		while (i < 5) {
			System.out.println("i: " + i);
			
			i++; //same as i = i + 1
		}
		
		//for loops
		System.out.println();
		
		for (int k = 0; k < 10; k++) {
			System.out.println("k: " + k);
		}
		
		/*
		 * Casting
		 */
		
		//Cast int to String
		String intToString = Integer.toString(1);
		
		//Cast double to String
		String doubleToString = Double.toString(1.1);
		
		//Print the values and type of values
		System.out.println(); //blank line
		System.out.println(intToString + " " + intToString.getClass());
		System.out.println(doubleToString + " " + doubleToString.getClass());
		
		//Cast String to int
		int stringToInt = Integer.parseInt("1");
		
		//Cast String to double
		double stringToDouble = Double.parseDouble("1.1");
		
		//Print values and type of values
		System.out.println(stringToInt);
		//cast to Object then call getClass()
		System.out.println(((Object)stringToInt).getClass());
		
		System.out.println(stringToDouble);
		//cast to Object then call getClass()
		System.out.println(((Object)stringToDouble).getClass());
		
		/*
		 * User input
		 */
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println(); //blank line
		System.out.println("Enter a number: ");
		int myInt = scan.nextInt(); //get next input as int
		System.out.println("Your number is: " + myInt);
		
		//print the multiplication table up to 10 for myInt
		for (int t = 1; t < 11; t++) {
			//print t * myInt
			System.out.println(t + " x " + myInt + ": " + (t * myInt));
		}
		
		System.out.println(); //blank line
		System.out.println("Enter a String: ");
		String myStr = scan.next(); //get next input value as String
		
		//print each char in myStr separately
		for (int u = 0; u < myStr.length(); u++) {
			//print char at index u
			System.out.println(myStr.charAt(u));
		}
		
		scan.close();
				

	}

}
