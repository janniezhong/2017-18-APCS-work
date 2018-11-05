import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.*;
import java.util.Arrays;

/**
 * 
 * This class encrypts and decrypts text files using one of 3 algorithms:
 * 		Random monoalphabet, Vigenere, or Playfair
 * 
 * 
 * @author
 * @version
 * 
 */
public class Crypt {


	/**
	 * 
	 * An integer representing the algorithm chosen.
	 * Set to:
	 * 1 for random monoalphabet
	 * 2 for Vigenere
	 * 3 for Playfair
	 * 
	 */
	public static final int algorithm = 1;

	public static final String fileSeparator = System.getProperty("file.separator");
	public static final String lineSeparator = System.getProperty("line.separator");

	//public String keywordArrayString;


	/**
	 * Reads from the file specified, and writes out an encrypted version of the file. If the output file already
	 * exists, overwrite it.
	 * 
	 * @param inputFilename The path of the file to be encrypted.
	 * @param outputFilename The path of the encrypted file to be saved.
	 * @param keyword The keyword to be used in the encryption algorithm.
	 * 
	 */
	public void encrypt(String inputFilename, String outputFilename, String keyword) 
	{

		char[] encrypted;

		StringBuffer s = new StringBuffer(keyword+"zyxwvutsrqponmlkjihgfedcba");


		for (int i = 0; i < keyword.length(); i++) {

			s.deleteCharAt(s.lastIndexOf(keyword.charAt(i) +""));

		}

		//keywordArrayString = s.toString();
		char[] keywordArray = (s.toString()).toCharArray();

		FileWriter writer = null;
		BufferedWriter w = null;

		try {	

			FileReader reader = new FileReader(inputFilename);
			BufferedReader b = new BufferedReader(reader);
			writer = new FileWriter(outputFilename);
			w = new BufferedWriter(writer);
			boolean finished = false;

			//			byte[] buffer = inputFilename.getBytes();
			//			int number_of_lines = 5000;
			//
			//			FileChannel rwChannel = new RandomAccessFile(inputFilename, "rw").getChannel();
			//
			//			ByteBuffer wrBuf = rwChannel.map(FileChannel.MapMode.READ_WRITE, 0, buffer.length * number_of_lines);
			//			for (int i = 0; i < number_of_lines; i++)
			//			{
			//				wrBuf.put(buffer);
			//			}
			//			rwChannel.close();



			//			RandomAccessFile file = new RandomAccessFile(inputFilename, "rw");
			//
			//			FileChannel channel = file.getChannel();
			//
			//			ByteBuffer wrBuf = ByteBuffer.allocate((int) channel.size());
			//
			//			channel.read(wrBuf);
			//
			//			wrBuf.flip();//Restore buffer to position 0 to read it
			//
			//
			//			for (int i = 0; i < channel.size(); i++) {
			//				//System.out.print((char) wrBuf.get());
			//			}
			//
			//			channel.close();
			//			file.close();



			while(!finished) {

				String data = b.readLine();
				if (data == null) {


					finished = true;
				} else {

					//String text = Files.toString(new File(args[2]), Charsets.UTF_8);
					encrypted = new char[data.length()];

					//ENCRYPT

					char[] input = data.toCharArray();

					for (int i = 0; i < input.length; i++) {

						int letter = input[i];

						if (letter < 91 && letter > 64) {
							encrypted[i] = Character.toUpperCase(keywordArray[letter-65]);
						} else if (letter < 123 && letter > 96)
						{
							encrypted[i] = keywordArray[letter-97];
						} else {
							encrypted[i] = (char)letter;
						}

					}


					writer.write(encrypted);
					writer.write(lineSeparator);


				}
			}

		} catch(IOException e) {
			e.printStackTrace();

		} finally {

			if (w!= null) {
				try {
					w.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}





	}


	/**
	 * Reads from the (previously encrypted) file specified, and writes out a decrypted version of the file. 
	 * If the output file already exists, overwrite it.
	 * 
	 * @param inputFilename The path of the encrypted file.
	 * @param outputFilename The path of the decrypted file to be saved.
	 * @param keyword The keyword to be used in the decryption algorithm.
	 * 
	 */
	public void decrypt(String inputFilename, String outputFilename, String keyword) 
	{


		char[] dencrypted;
		char[] code = new char[26];
		StringBuffer alphabet = new StringBuffer("abcdefghijklmnopqrstuvwxyz");


		//char[] keywordArray;		

		StringBuffer s = new StringBuffer(keyword+"zyxwvutsrqponmlkjihgfedcba");


		for (int i = 0; i < keyword.length(); i++) {

			s.deleteCharAt(s.lastIndexOf(keyword.charAt(i) +""));

		}


		String deencryptionCode = s.toString();


		FileWriter writer = null;
		BufferedWriter w = null;
		try {	

			FileReader reader = new FileReader(inputFilename);
			BufferedReader b = new BufferedReader(reader);
			writer = new FileWriter(outputFilename);
			w = new BufferedWriter(writer);
			boolean finished = false;


			while(!finished) {

				String data = b.readLine();

				if (data == null) {
					finished = true;
				} else {
					//ENCRYPT
					dencrypted = new char[data.length()];
					char[] input = data.toCharArray();

					for (int i = 0; i < input.length; i++) {


						int letter = input[i];

						if (letter < 91 && letter > 64) {
							dencrypted[i] = Character.toUpperCase((char)(65+deencryptionCode.indexOf(Character.toLowerCase(input[i]))));
						} else if (letter < 123 && letter > 96)
						{
							dencrypted[i] = (char)(97+deencryptionCode.indexOf(input[i]));
						} else {
							dencrypted[i] = input[i];
						}

					}


					writer.write(dencrypted);
					writer.write(lineSeparator);


				}
			}
		} catch(IOException e) {
			e.printStackTrace();

		} finally {

			if (w!= null) {
				try {
					w.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}


	}
}