import java.util.Scanner;

public class CheckMail {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("What is the weight of your package?");

		Scanner keyBoard = new Scanner(System.in);

		double weight = keyBoard.nextDouble();

		System.out.println("What is the first dimension of your package?");

		double dim1 = keyBoard.nextDouble();
		
		System.out.println("What is the second dimension of your package?");

		double dim2 = keyBoard.nextDouble();
		System.out.println("What is the third dimension of your package?");

		double dim3 = keyBoard.nextDouble();
		
		Package box = new Package (dim1, dim2, dim3, weight);
		
		
		boolean isTooHeavy = box.isTooHeavy();
		boolean isTooLarge = box.isTooLarge();
		
		if (!isTooHeavy && !isTooLarge) {
			System.out.println("Your package is acceptable.");
		} else if (isTooHeavy && isTooLarge){
			System.out.println("Your package is too heavy and too large.");
		} else if (isTooHeavy) {
			System.out.println("Your package is too heavy.");
		} else if (isTooLarge) {
			System.out.println("Your package is too large.");
		}

		//Take in 4 numbers from the user
		//Create a Package object
		//Call checkStatus method
		//print result
		
		
		String a = "Rob";
		String b = "Robert";
		
		if (a == b.substring(0, 2)) {
			System.out.println("Yes");
		}
	}

}
