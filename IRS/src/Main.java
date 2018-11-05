import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("What is your marital status? (1 for single, 2 for married):");

		Scanner keyBoard = new Scanner(System.in);

		int maritalStatus = keyBoard.nextInt();
		if (maritalStatus != 1 && maritalStatus != 2) {
			System.out.print(maritalStatus + " is not a valid input.");
			System.exit(1);
		}

		System.out.println("What is your taxable income?");

		double taxableIncome = keyBoard.nextDouble();
		
		if (taxableIncome < 0) {
			System.out.print(taxableIncome + " is not a valid input.");
			System.exit(1);
		}
		TaxPayer calculator = new TaxPayer(maritalStatus, taxableIncome);
		
		double tax = calculator.calcTax();
		System.out.println("Your federal tax is: $" + tax);
		



	}








}

