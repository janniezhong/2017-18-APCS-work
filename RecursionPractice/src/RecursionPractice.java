/*

The 1st test took 1 iterations.

The 2nd test took 3 iterations.

The 3rd test took 7 iterations.

The 4th test took 15 iterations.

The 5th test took 31 iterations.

The 6th test took 63 iterations.

The 7th test took 127 iterations.

The 8th test took 255 iterations.

The 9th test took 511 iterations.

The 10th test took 1023 iterations.

equation ----> method executions = 2^(testNum)-1

 */


public class RecursionPractice {
	
	private static int iterations, iterations2;


	public static int triangleNumber(int n)
	{
		if (n == 1)
			return 1;
		else
			return n + triangleNumber(n-1);
	}


	public static int squareNumber(int n) {

		if (n == 1) {
			return 1;
		} else {
			return squareNumber(n-1) + 2*n -1;
		}
	}


	public static int logBase2(int n) {

		if (n==1) {
			return 0;
		} else {
			return 1 + logBase2(n);
		}

	}


	public static int pow(int n) {

		if (n == 0) {
			return 1;
		} else {
			return pow(n-1)*2;
		}

	}


	public static int pentagonalNumber(int n) {

		if (n==1) {
			return 1;
		} else if (n==2) {
			
			return 5;

		} else {

			return pentagonalNumber(n-1)+(3*(n-1))+1;

		}

	}
	
	public static long fibonacciRecursion(int n) {
		
		iterations++;
		
		if (n==0) {
			return 0;
		} else if (n==1) {
			return 1;
		} else if (n < 0){
			return -1;
		} else {

			return fibonacciRecursion(n-1) + fibonacciRecursion(n-2);

		}
		
	}
	
	public static long fibonacciLoop(int n) {
		
		if (n == 0) {
			return 0;
		}
		
		long prevInt = 0;
		long nowInt = 1;
		
		for (int i = 0; i < n-3; i++) {
			long temp = nowInt;
			nowInt += prevInt;
			prevInt = temp;
		}
		
		return prevInt + nowInt;
		
		
	}


	private static void printHanoiSolution(int numberOfDisks, int x, int y) {
		iterations2++;
		
		if (numberOfDisks==1) {
			System.out.println("Move from " + (x+1) + " to " + (y+1));
		} else {
			printHanoiSolution(numberOfDisks-1, x, 3 - (x+y));
			System.out.println("Move from " + (x+1) + " to " + (y+1));
			printHanoiSolution(numberOfDisks-1, 3 - (x+y), y);

		}
	}
	

	public static void printHanoiSolution(int numberOfDisks) {
		printHanoiSolution(numberOfDisks, 0, 2);
	}
	



	public static void main(String[] args) {
		
		for (int i = 1; i < 11; i++) {
		
		iterations2 = 0;
		printHanoiSolution(i, 0, 2);
		System.out.println("The test took " + iterations2 + " iterations.");
		}
	}

}