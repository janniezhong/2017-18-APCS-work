
public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Crypt c = new Crypt();
		c.encrypt("testFile.txt", "encryptedTest.txt", "crypt");
		c.decrypt("encryptedTest.txt", "testFile.txt", "crypt");
		
		
	}

}
