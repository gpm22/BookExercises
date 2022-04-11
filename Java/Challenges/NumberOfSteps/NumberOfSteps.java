import java.math.BigInteger;

/*
Given an binary number, return the number 
of steps to reduce its decimal form to zero,
using the following steps:

1 - If the current number is even, 
you have to divide it by 2;

2 - Is the current number is odd, you 
have to subtract 1 from it.

*/

public class NumberOfSteps {

	public static void main(String[] args) {
		String[] tests = { "0 0", "1 1", "10 2", "11 3", "100 3", "101 4",
				"110 4", "111 5", "1000 4", "1001 5", "1110 6", "11100 7",
				"1010000 8", "100110 8", "101110 9", "1111011 12",
				"1111111111111111111111111111111 61",
				"111101000010001111111111111111100101001111100011111 85",
				new String(new char[400000]).replace("\0", "1") + " 799999",
				"1" + new String(new char[400000]).replace("\0", "0") + " 400001" };

		for (String test : tests) {
			String[] inputs = test.split(" ");
			printTest(inputs[0], inputs[1]);
		}
	}

	public static void printTest(String binaryNumber, String expectedResult) {

		Integer numberOfSteps = numberOfSteps(binaryNumber);
		System.out.println("binary number: " +
				(binaryNumber.length() > 31
						? binaryNumber.length() + " digits"
						: binaryNumber)
				+
				" - Decimal Number: "
				+ (binaryNumber.length() > 31
						? (new BigInteger(binaryNumber, 2)).toString().length() + " digits"
						: Integer.parseInt(binaryNumber, 2))
				+ " - Number of Steps: "
				+ numberOfSteps
				+ (numberOfSteps.toString().equals(expectedResult)
						? " - PASSED!"
						: " - REPROVED! - Expected Result: " + expectedResult));
	}

	public static int numberOfSteps(String binaryNumber) {

		int numberOfDivisionsNeeded = binaryNumber.length() - 1;

		// It is the quantity of 1's in the string
		int numberOfSubtractionsNeeded = (int) binaryNumber
				.chars()
				.parallel()
				.filter(ch -> ch == '1')
				.count();

		return numberOfDivisionsNeeded + numberOfSubtractionsNeeded;
	}

}
