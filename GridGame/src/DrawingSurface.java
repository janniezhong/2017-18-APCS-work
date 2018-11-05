


import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

import processing.core.PApplet;



public class DrawingSurface extends PApplet {

	private CandyCrush board;
	private int runCount;
	private int speed;
	private Point mouseP, mouseR;
	private int score, turnsLeft;
	private int gameStatus;

	private final int MAX_SPEED = 480, MIN_SPEED = 15;


	public DrawingSurface() {
		board = new CandyCrush();
		runCount = -1;
		speed = 120;
		mouseP = null;
		mouseR = null;
		score = 0;
		turnsLeft = 10;
		gameStatus = 0; //0 is still playing, 1 is lost, 2 is win
		
	}

	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {
		//size(0,0,PApplet.P3D);
	}

	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() { 
		background(255);   // Clear the screen with a white background
		fill(0);
		textAlign(LEFT);
		textSize(15);

		text("Welcome to discount \n Candy Crush!", height+10, 30);
		text ("Turns left:" + turnsLeft, height + 10, 100);
		text("Score:" + score, height + 10, 150);

//		if (runCount == 0) {
//			board.step();
//			runCount = speed;
//		} else if (runCount > 0) {
//			runCount--;
//		}
		
		
		int destroyed = board.step();
		
		int prevScore = score;
		
		score = score + destroyed*100;
		
		if (prevScore < score) {
			turnsLeft--;
		}
		
		if (turnsLeft == 0 && score <= 3600) {
			gameStatus = 1;
		} else if (turnsLeft == 0 && score > 3600) {
			gameStatus = 2;
		}
//		
//		if (mousePressed) {
//			if (gameStatus == 0) {
//				mousePressed();
//				turnsLeft--;
//			} else {
//				if (gameStatus == 1) {
//					text("YOU LOST", height + 10, 200);
//				}
//				if (gameStatus == 2) {
//					text("YOU WON", height + 10, 200);
//				}
//			}
//		}
//		
		
		
		if (board != null) {
			board.draw(this, 0, 0, height, height);
		}
		
		board.step();
		
	}


	//	public void mousePressed() {
	//		if (mouseButton == LEFT) {
	//			Point click = new Point(mouseX,mouseY);
	//			float dimension = height;
	//			Point cellCoord = board.clickToIndex(click,0,0,dimension,dimension);
	//			if (cellCoord != null) {
	//				//board.toggleCell(cellCoord.x, cellCoord.y);
	//				prevToggle = cellCoord;
	//			}
	//		} 
	//	}


	public void mousePressed() {
		if (mouseButton == LEFT) {
			Point click = new Point(mouseX,mouseY);
			float dimension = height;
			Point cellCoord = board.clickToIndex(click,0,0,dimension,dimension);
			if (cellCoord != null) {
				//board.toggleCell(cellCoord.x, cellCoord.y);
				mouseP = cellCoord;
			}
		} 
	}

	public void mouseReleased() {
		if (mouseButton == LEFT) {
			Point rel = new Point(mouseX, mouseY);
			float dimension = height;
			Point newCellCoord = board.clickToIndex(rel, 0, 0, dimension, dimension);
			
			
			if(newCellCoord != null&&board.canMatch(mouseP,newCellCoord)) {
			
			int diffX = newCellCoord.x-mouseP.x;
			int diffY = newCellCoord.y-mouseP.y;


				if (Math.abs(diffX) > Math.abs(diffY)) { //if the x mouse movement was greater
					if (diffX > 0) { // if the move was to the right
						board.switchCandies(mouseP, new Point(mouseP.x+1, mouseP.y));	

					} else { // if the move was to the left
						board.switchCandies(mouseP, new Point(mouseP.x-1, mouseP.y));	

					}
				} else if (Math.abs(diffX) < Math.abs(diffY)) { //if the y mouse movement was greater
					if (diffY > 0) { // if the move was downwards
						board.switchCandies(mouseP, new Point(mouseP.x, mouseP.y+1));	

					} else { // if the move was upwards
						board.switchCandies(mouseP, new Point(mouseP.x, mouseP.y-1));	

					}

				} //if the mouse movement was the same, do nothing cuz the user screwed up

			}




		}
	}


	public void keyPressed() {
		if (keyCode == KeyEvent.VK_SPACE) {
			if (runCount >= 0)
				runCount = -1;
			else
				runCount = 0;
		} else if (keyCode == KeyEvent.VK_DOWN) {
			speed = Math.min(MAX_SPEED, speed*2);
		} else if (keyCode == KeyEvent.VK_UP) {
			speed = Math.max(MIN_SPEED, speed/2);
			runCount = Math.min(runCount, speed);
		} else if (keyCode == KeyEvent.VK_ENTER) {
			board.step();
		}
	}


}










