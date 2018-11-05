import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Statistics s = new Statistics (100000);
		s.readData("numbers3.txt");
		s.printData();
		
		System.out.println("");
		s.compact(0);
		s.printData();
		
		
		double avg = s.average();
		System.out.println("average: " + avg);
		
		double std = s.standardDeviation();
		System.out.println("standard deviation: " + std);
		
		int[] modes = s.mode();
		System.out.print("modes: ");
		for (int i = 0; i < modes.length; i++) {
			if (i != 0 && modes[i] == 0 && modes[i+1] == 0) {
				break;
			} else {
			System.out.print(modes[i] + " ");		
		
			}
		}

	}

}
