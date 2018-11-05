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

public class Maze {

	private char[][] grid;
	private int[][] neighbor;



	// Constructs an empty grid
	public Maze() {

		grid = new char[20][20];
		neighbor = new int[20][20];
	}

	// Constructs the grid defined in the file specified
	public Maze(String filename) {
		grid = new char[20][20];
		neighbor = new int[20][20];

		readData(filename, grid);

	}

	// Formats this Life grid as a String to be printed (one call to this method returns the whole multi-line grid)
	public String toString() {

		String response = " ";


		for (int i = 0; i < grid[0].length; i++) {
			for (int j = 0; j < grid.length; j++) {
				response += grid[j][i];
			}
			response = response + "\n ";

		}


		return response;
	}

	public boolean solveMaze() {
		for (int i = 0; i < grid.length;i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 'C') {
					return solveMaze(i,j);

				}
			}
		}

		return false;

	}



	private boolean solveMaze(int x, int y) {


		if (x < 0 || x > grid.length-1 || y < 0 || y > grid[0].length-1) {
			return false;
		} else if (grid[x][y] == '#') {
			return false;
		} else if (grid[x][y] == 'X') {
			return true;
		} else if (grid[x][y] == '!') {
			return false;	
		} else {

			grid[x][y] = '!';

			if (solveMaze(x+1, y)) {
				return true;
			} else if (solveMaze(x-1, y)) {
				return true;
			} else if (solveMaze(x, y-1)) {
				return true;
			} else if (solveMaze(x, y+1)) {
				return true;
			} else {
				//grid[x][y] = '.';

				return false;
			}


		}
	}


	// Reads in array data from a simple text file containing asterisks (*)
	public void readData (String filename, char[][] gameData) {
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
						if (i < gameData.length && count < gameData[i].length)
							gameData[i][count] = line.charAt(i);

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

				if(grid[j][i] == '*') {
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

	public void clickErase(int x, int y) {
		solveMaze(x, y);
	}

}