import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import processing.core.PApplet;

/*

	Represents a Game Of Life grid.

	Coded by: Jannie Zhong
	Modified on: 1/12/18

 */

public class CandyCrush {

	private int[][] grid;
	//private int[][] neighbor;
	private int width, height;
	private boolean[][] chains;




	// Constructs a random start grid
	public CandyCrush() {
		//		width = w;
		//		height = h;
		grid = new int[10][10];
		//neighbor = new int[10][10];
		for (int i = 0; i < grid[0].length; i++) {
			for (int j = 0; j < grid.length; j++) {
				grid[j][i] = 1+(int)(Math.random()*6);

				if (j>1 && i>1) {
					if (grid[j-1][i] == grid[j][i] && grid[j-2][i] == grid[j][i] || grid[j][i-1] == grid[j][i] && grid[j][i-2] == grid[j][i]) {
						j--;
					}

				}
			}
		}

		chains = new boolean[10][10];

	}

	// Runs a single turn of the Game Of Life
	public int step() {


		findChains();

		for (int i = 0; i < chains[0].length; i++) { //replace all spots that need to disappear
			for (int j = 0; j < chains.length; j++) {
				if (chains[j][i]) {
					grid[j][i] = 0;
				}

			}

		}


		for (int j = 0; j < grid.length; j++) { // move everything down (if needed)
//			for (int i = grid[0].length-1; i >= 1; i--) {
//				if (grid[j][i] == 0) {
//
//					if (grid[j][i-1] != 0) {
//					
//						grid[j][i] = grid[j][i-1];
//						grid[j][i-1] = 0;
//					
//					} else {
//						
//					}
//					
//					//System.out.println(grid);
//
//				}
//
//			}
			
			for (int i = 1; i < grid[0].length; i++) {
				if (grid[j][i] == 0) {

						grid[j][i] = grid[j][i-1];
						grid[j][i-1] = 0;
						
						i--;
					
					//System.out.println(grid);

				}

			}
			
			
			

		}

		for (int i = 0; i < grid[0].length; i++) { //replace all spots that need to disappear
			for (int j = 0; j < grid.length; j++) {
				if (grid[j][i] == 0) {

					grid[j][i] = 1+(int)(Math.random()*6);

//					while (hasMatch()) {
//						step();
//					}

				}

			}

		}


		int count = numberToDestroy();

		chains = new boolean[grid.length][grid[0].length];

		return count;

	}

	// Runs n turns of the Game Of Life
	public void step(int n) {

		int count = 0;

		while (count < n) {
			step();
			count++;
		}


	}

	// Formats this Life grid as a String to be printed (one call to this method returns the whole multi-line grid)
	public String toString() {

		String response = " ";


		for (int i = 0; i < grid[0].length; i++) {
			for (int j = 0; j < grid.length; j++) {

				response += grid[i][j];
			}
			response = response + "\n ";

		}


		return response;
	}



	public void switchCandies (Point one, Point two) {

		int temp = grid[one.x][one.y];
		grid[one.x][one.y] = grid[two.x][two.y];
		grid[two.x][two.y] = temp;


	}

	public boolean canMatch (Point one, Point two) {

		switchCandies(one, two);

		findChains();

		if (chains[one.x][one.y]||chains[two.x][two.y]) {
			switchCandies(one,two);
			chains = new boolean[grid.length][grid[0].length];
			return true;
		}

		switchCandies(one,two);
		chains = new boolean[grid.length][grid[0].length];

		if (hasMatch()) {
			return true;
		}

		return false;
	}

	public boolean hasMatch() {

		findChains();	

		for (int i = 0; i < chains[0].length; i++) {
			for (int j = 0; j < chains.length; j++) {

				if (chains[j][i]) {
					chains = new boolean[grid.length][grid[0].length];
					return true;
				}

			}


		}

		chains = new boolean[grid.length][grid[0].length];


		return false;
	}


	public void findChains() {

		//int currType = grid[0][0];

		for (int i = 0; i < grid[0].length; i++) {
			for (int j = 0; j < grid.length - 2; j++) {

				if (grid[j][i] == grid[j+1][i]&&grid[j][i] == grid[j+2][i]) {

					chains[j][i] = true;
					chains[j+1][i] = true;
					chains[j+2][i] = true;;

					j += 2;

					while (checkRight(new Point (j, i))){

						j++;
						chains[j][i] = true;

					}

				}
			}

		}


		for (int j = 0; j < grid.length; j++) {
			for (int i = 0; i < grid[0].length-2; i++) {

				if (grid[j][i] == grid[j][i+1]&& grid[j][i] == grid[j][i+2]) {

					chains[j][i] = true;
					chains[j][i+1] = true;
					chains[j][i+2] = true;

					i += 2;

					while (checkDown(new Point (j, i))){

						i++;
						chains[j][i] = true;

					}

				}
			}

		}

	}


	public boolean checkRight(Point p) {

		if (p.x < grid.length-1) {
			if (grid[p.x][p.y] == grid[p.x+1][p.y]) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}
	public boolean checkLeft(Point p) {

		if (p.x > 0) {
			if (grid[p.x][p.y] == grid[p.x-1][p.y]) {
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}

	}
	public boolean checkUp(Point p) {
		if (p.y > 0) {
			if (grid[p.x][p.y] == grid[p.x][p.y+1]) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}
	public boolean checkDown(Point p) {
		if (p.y < grid[0].length-1) {
			if (grid[p.x][p.y] == grid[p.x][p.y+1]) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}
	
	public int numberToDestroy() {
		
		int count = 0;
		
		for (int i = 0; i < chains[0].length; i++) {
			for (int j = 0; j < chains.length; j++) {
				
				if (chains[j][i]) {
					count++;
				}
				
			}
			
			
		}
		
		
		return count;
	}



	// Reads in array data from a simple text file containing asterisks (*)
	public void readData (String filename, boolean[][] gameData) {
		File dataFile = new File(filename);

		if (dataFile.exists()) {
			int count = 0;

			FileReader reader = null;
			Scanner in = null;
			try {
				reader = new FileReader(dataFile);
				in = new Scanner(reader);

				while (in.hasNext()) {
					String line = in.nextLine();
					for(int i = 0; i < line.length(); i++)
						if (i < gameData.length && count < gameData[i].length && line.charAt(i)=='*')
							gameData[i][count] = true;

					count++;
				}
			} catch (IOException ex) {
				throw new IllegalArgumentException("Data file " + filename + " cannot be read.");
			} finally {
				if (in != null)
					in.close();
			}

		} else {
			throw new IllegalArgumentException("Data file " + filename + " does not exist.");
		}
	}







	/**
	 * Optionally, complete this method to draw the grid on a PApplet.
	 * 
	 * @param marker The PApplet used for drawing.
	 * @param x The x pixel coordinate of the upper left corner of the grid drawing. 
	 * @param y The y pixel coordinate of the upper left corner of the grid drawing.
	 * @param width The pixel width of the grid drawing.
	 * @param height The pixel height of the grid drawing.
	 */
	public void draw(PApplet marker, float x, float y, float width, float height) {

		marker.pushStyle();

		float cellWidth = width/grid.length;
		float cellHeight = height/grid[0].length;

		marker.stroke(0);

		marker.colorMode(marker.RGB, 255);

		for (int i = 0; i < grid[0].length; i++) {
			for (int j = 0; j < grid.length; j++) {

				if(grid[j][i] == 1) {
					marker.fill(255, 0, 0);

				} else if(grid[j][i] == 2) {
					marker.fill(255, 150, 0);

				} else if(grid[j][i] == 3) {
					marker.fill(255, 255, 0);

				} else if(grid[j][i] == 4) {
					marker.fill(0, 255, 0);

				} else if(grid[j][i] == 5) {
					marker.fill(0, 255, 255);

				} else if (grid[j][i] == 6){
					marker.fill(170, 0, 255);
				}
				marker.rect(x+j*cellWidth, y+i*cellHeight, cellWidth, cellHeight);

			}

		}

		marker.popStyle();	
	}

	/**
	 * Optionally, complete this method to determine which element of the grid matches with a
	 * particular pixel coordinate.
	 * 
	 * @param p A Point object representing a graphical pixel coordinate.
	 * @param x The x pixel coordinate of the upper left corner of the grid drawing. 
	 * @param y The y pixel coordinate of the upper left corner of the grid drawing.
	 * @param width The pixel width of the grid drawing.
	 * @param height The pixel height of the grid drawing.
	 * @return A Point object representing a coordinate within the game of life grid.
	 */
	public Point clickToIndex(Point p, float x, float y, float width, float height) {

		float cellWidth = width/grid.length;
		float cellHeight = height/grid[0].length;

		int j = (int) ((p.x-x)/cellWidth);
		int i = (int) ((p.y-y)/cellHeight);

		if (j < 0 || j >= grid.length) {
			return null;
		}
		if (i < 0 || i >= grid[0].length) {
			return null;
		}

		Point answer = new Point(j, i);

		return answer;
	}

	/**
	 * Optionally, complete this method to toggle a cell in the game of life grid
	 * between alive and dead.
	 * 
	 * @param i The x coordinate of the cell in the grid.
	 * @param j The y coordinate of the cell in the grid.
	 */
	//	public void toggleCell(int i, int j) {
	//		
	//		grid[i][j] = !grid[i][j];
	//	}



}