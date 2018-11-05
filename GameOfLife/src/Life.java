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

public class Life {

	private boolean[][] grid;
	private int[][] neighbor;



	// Constructs an empty grid
	public Life() {

		grid = new boolean[20][20];
		neighbor = new int[20][20];
	}

	// Constructs the grid defined in the file specified
	public Life(String filename) {
		grid = new boolean[20][20];
		neighbor = new int[20][20];

		readData(filename, grid);

	}

	// Runs a single turn of the Game Of Life
	public void step() {

		//act on number counted

		countNeighbors();
		
		for (int i = 0; i < neighbor[0].length; i++) {
			for (int j = 0; j < neighbor.length; j++) {
				
				if (grid[i][j]) { //cell is originally alive
					
					if (neighbor[i][j] <= 1 || neighbor[i][j] >= 4) {
						grid[i][j] = false;
					}
					
				} else { //cell was dead
					
					if (neighbor[i][j] == 3) {
						grid[i][j] = true;
					}
					
				}
				
			}

		}


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

				if(grid[j][i]) {
					response = response + "* ";
				} else {
					response = response + "- ";
				}
			}
			response = response + "\n ";

		}


		return response;
	}

	public void countNeighbors() {
		//count neighbors
		int count = 0;
		for (int i = 0; i < grid[0].length; i++){
			
			for (int j = 0; j < grid.length; j++) {
				
				count = 0;
				
//				if (i != 0) { //if not in first array
//
//					if (j != 0) { // if not in first spot
//						if (grid[i-1][j-1]) { //cool to check left and up
//							count++;
//						}
//					} else {
//						if (grid[i-1][j]) { //cool to check left (not up)
//							count++;
//						}
//					}
//					
//				}
//				if (i != grid[0].length) { //if not in last array
//					
//					if (j != 0){ //if not in first spot
//						
//					}
//				}
//			
//					if (grid[i-1][j]) {
//						count++;
//					}
//					
//					if (j != 0) {
//						if (grid[i-1][j-1]) {
//							count++;
//						}
//					}
//				
				

				

				if (i == 0) { //if in first array
					if (j == 0) { // if in first spot
						if (grid[0][1]) {
							count++;
						}
						if (grid[1][0]) {
							count++;
						}
						if (grid[1][1]) {
							count++;
						}

					} else if (j == grid.length - 1) { //if in last spot
						if (grid[0][j-1]) {
							count++;
						}
						if (grid[1][j]) {
							count++;
						}
						if (grid[1][j-1]) {
							count++;
						}


					} else { // if in the middle somewhere

						if (grid[0][j-1]) {
							count++;
						}
						if (grid[0][j+1]) {
							count++;
						}
						if (grid[1][j-1]) {
							count++;
						}
						if (grid[1][j]) {
							count++;
						}
						if (grid[1][j+1]) {
							count++;
						}

					}

				} else if (i == grid[0].length - 1) { //last array
					if (j == 0) { // if in first spot
						if (grid[i-1][0]) {
							count++;
						}
						if (grid[i][1]) {
							count++;
						}
						if (grid[i-1][1]) {
							count++;
						}



					} else if (j == grid.length - 1) { //if in last spot
						
						if (grid[i][j-1]) {
							count++;
						}
						if (grid[i-1][j-1]) {
							count++;
						}
						if (grid[i-1][j]) {
							count++;
						}



					} else { // if in the middle somewhere

						if (grid[i][j-1]) {
							count++;
						}
						if (grid[i][j+1]) {
							count++;
						}
						if (grid[i-1][j-1]) {
							count++;
						}
						if (grid[i-1][j]) {
							count++;
						}
						if (grid[i-1][j+1]) {
							count++;
						}



					}
				} else if (j == 0) {

					if (grid[i-1][j]) {
						count++;
					}
					if (grid[i+1][j]) {
						count++;
					}
					if (grid[i-1][j+1]) {
						count++;
					}
					if (grid[i][j+1]) {
						count++;
					}
					if (grid[i+1][j+1]) {
						count++;
					}




				} else if (j == grid.length - 1) {

					if (grid[i-1][j]) {
						count++;
					}
					if (grid[i+1][j]) {
						count++;
					}
					if (grid[i-1][j-1]) {
						count++;
					}
					if (grid[i][j-1]) {
						count++;
					}
					if (grid[i+1][j-1]) {
						count++;
					}




				} else { //it's a normal spot 

					if (grid[i-1][j-1]) { //top left
						count++;
					}
					if (grid[i-1][j]) { //top mid
						count++;
					}
					if (grid[i-1][j+1]) { //top right
						count++;
					}
					if (grid[i][j-1]) { //mid left
						count++;
					}
					if (grid[i][j+1]) { // mid right
						count++;
					}
					if (grid[i+1][j-1]) { //bot left
						count++;
					}
					if (grid[i+1][j]) { //bot mid
						count++;
					}
					if (grid[i+1][j+1]) { //bot right
						count++;
					}

				}

				neighbor[i][j] = count;

			}

		}

	}
	
	public int numberLiveCellsRow (int r) {
		
		int count = 0;
		for (int i = 0; i < grid[0].length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (j == r-1 && grid[i][j]) {
					count++;
				}
			}
		}	
		
		return count;
		
	}
	public int numberLiveCellsColumn (int c) {
		
		int count = 0;
		for (int i = 0; i < grid[0].length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (i == c-1 && grid[i][j]) {
					count++;
				}
			}
		}	
		
		return count;
	}
	public int numberLiveCells () {
		
		int count = 0;
		for (int i = 0; i < grid[0].length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (grid[i][j]) {
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
		
		for (int i = 0; i < grid[0].length; i++) {
			for (int j = 0; j < grid.length; j++) {

				if(grid[j][i]) {
					marker.fill(0);
					
				} else {
					marker.fill(255);
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
	public void toggleCell(int i, int j) {
		
		grid[i][j] = !grid[i][j];
	}



}