import java.util.Scanner;


public class RecursiveStringTools {

	// Example
	public static int length(String in) {

		if (in.equals("")) { //BASE
			return 0;
		} else { //RECURSIVE
			return length(in.substring(1))+1;
		}

	}



	// Example
	public static boolean equals(String in1, String in2) {

		if (in1.length() != in2.length()) {
			return false;
		} else if (in1.length() == 0 && in2.length() == 0) {
			return true;
		} else {
			boolean eq = in1.charAt(0) == in2.charAt(0);
			String str1 = in1.substring(1);
			String str2 = in2.substring(1);
			return eq && equals(str1,str2);
		}
	}



	// Exercise #1
	public static boolean matches(String in1, String in2) {
		if (in1.length() != in2.length()) {
			return false;

		}else if (in1.length() == 0 && in2.length() == 0) {
			return true;		
		}
		else {

			boolean eq = (in1.charAt(0) == '?' || in2.charAt(0) == '?') || in1.charAt(0) == in2.charAt(0);
			String str1 = in1.substring(1);
			String str2 = in2.substring(1);
			return eq && matches(str1,str2);

		}
	}



	// Exercise #2
	public static boolean isPalindrome(String in) {
		in = in.replace(" ", "");
		in = in.replaceAll("\\p{Punct}|\\d", "");
		in = in.toLowerCase();
		if (in.equals("") || in.length() == 1) {
			return true;
		} else {
			boolean eq = (in.charAt(0) == in.charAt(in.length()-1));
			return eq && isPalindrome(in.substring(1, in.length()-1));
		}

	}



	// Exercise #3
	public static void printPermutations(String in) {

		//permute(in, 0, in.length()-1);
		printPermutations(in, "");

	}

	//private method for printPermutations

	private static void permute(String str, int l, int r)
	{
		if (l == r)
			System.out.println(str);
		else
		{
			for (int i = l; i <= r; i++)
			{
				str = swap(str,l,i);
				permute(str, l+1, r);
				str = swap(str,l,i);
			}
		}
	}

	//does not work
	private static String swap(String s, int l, int i) {

		int j = Math.min(l, i);
		int k = Math.max(l, i);

		if (s.length() <= 1) {
			return s;
		} else {
			s = s.substring(0, j) + s.charAt(j) + s.charAt(i) + s.substring(j+1, k) + s.charAt(k) + s.substring(k+1, s.length()-1);  
			return s;
		}


	}




	private static void printPermutations(String in, String prefix) {

		int n = in.length();
		if (n <= 1) {
			System.out.println(prefix+in);
		} else {
			oneSetPermutations(0, in.length(), in, prefix);
		}

		//If there's only 1 char, print the 1 char (with all removed chars at the beginning
		//For each character inside the string:
		//remove this
		//print all permutations of the remaining characters (with the removed char at the beginning)


		//		for (int i = 0; i< n; i++) {
		//			
		//		}
		//	
		//	

	}

	public static void oneSetPermutations(int i, int n, String in, String prefix) {

		if (i >= n) {
			return;
		} else {

			printPermutations(in.substring(0, i) + in.substring(i+1), prefix + in.charAt(i));
			i++;
			oneSetPermutations(i, n, in, prefix);

		}

	}




	public static String piglatinate(String in) {
		return "";
	}


	public static void main(String[] args) {
		//		Scanner kboard = new Scanner(System.in);
		//		System.out.println("Please enter a string:");
		//		String s = kboard.nextLine();
		//	
		//		String out = RecursiveStringTools.length(s) + "";
		//		System.out.print("\n\nThe result is --> " + out + "\n\n");


		String s = "A man, a plan, a canal: Panama";
		String t = "luck";
		System.out.println(isPalindrome(s));
		printPermutations(t);

	}
}
