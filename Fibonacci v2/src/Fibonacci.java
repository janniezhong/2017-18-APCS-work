/**
 * @(#)Fibonacci.java
 *
 *
 */

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Fibonacci {

	//20th Fibonacci number is 6765
	public static long computeFibonacci(int x) {

		if (x <= 1) {

			if (x < 0) {
				IllegalArgumentException e = new IllegalArgumentException();
				throw e;
			}else {

				return x;
			}
		} else {
			long answer = computeFibonacci(x-2) + computeFibonacci(x-1);
			return answer;
		}

	}

	public static void main (String[] args) {

		while(true) {
			Scanner kboard = new Scanner (System.in);
			System.out.println("Which fibonacci number would you like to find? --> ");

			try {
				int x = kboard.nextInt();
				long answer = computeFibonacci(x);
//				if (x<0) {
//					System.out.println ("You have entered a negative number. Please try again.");
//				}else {
					System.out.println ("The " + x + " fibonacci number is " + answer + ".");
				
			} catch(InputMismatchException exception) {
				System.out.println ("You have entered an incorrect input. Please try again.");
			} catch(StackOverflowError exception) {
				System.out.println ("You have entered a input that is too large. Please try again.");
			} catch(IllegalArgumentException exception) {
				System.out.println ("Negative numbers are not valid inputs. Please try again");
			}

		}
	}

}



