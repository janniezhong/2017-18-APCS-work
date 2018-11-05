
/*
 * I think you should rework your class to be representative of a real world object.
 * 
 * Also you should add fields and constructors after you change it to represents a real world object.
 * 
 * In your main class you initialize the field calculator but you create a new taxCalcs called calculator in the main method 
 * look at lines 6 and 8.
 * 
 * a. 3
 * b. 3
 * c. 7
 * d. 4
 * 
 */

public class TaxPayer {
	
	private int maritalStatus;
	private double taxableIncome;
	private double tax;
	
	
	public TaxPayer(int maritalStatus, double income) {
		this.maritalStatus = maritalStatus;
		taxableIncome = income;
		tax = 0;
		
	}
	
	
	public double calcTax() {
		
		if (maritalStatus == 1) {
			
			if (taxableIncome > 297350) {
				tax = 93374 + (taxableIncome - 297350)*0.391;
				
			} else if (taxableIncome > 136750) {
				tax = 36361 + (taxableIncome - 136750)*0.355;

			} else if (taxableIncome > 65550) {
				tax = 14645 + (taxableIncome - 65550)*0.305;

			} else if (taxableIncome > 27050) {
				tax = 4057.5 + (taxableIncome - 27050)*0.275;

			} else if (taxableIncome > 0) {
				tax = (taxableIncome)*0.15;

			} else {
				
			}
			
			
		} else if (maritalStatus == 2) {
			
			if (taxableIncome > 297350) {
				tax = 88306 + (taxableIncome - 297350)*0.391;
				
			} else if (taxableIncome > 166500) {
				tax = 41855 + (taxableIncome - 166500)*0.355;

			} else if (taxableIncome > 109250) {
				tax = 24393.75 + (taxableIncome - 109250)*0.305;

			} else if (taxableIncome > 45200) {
				tax = 6780 + (taxableIncome - 45200)*0.275;

			} else if (taxableIncome > 0) {
				tax = (taxableIncome)*0.15;

			} else {
				
			}
		} else {
			
		}
		
		tax *= 100;
		tax = Math.round(tax);
		tax /= 100;
		
		return tax;
	}
}
