


import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

import processing.core.PApplet;



public class DrawingSurface extends PApplet {

	private EraseObject board;
	private int runCount;
	private int speed;
	private Point prevToggle;
	
	private final int MAX_SPEED = 480, MIN_SPEED = 15;
	
	
	public DrawingSurface() {
		board = new EraseObject("digital2.txt");
		runCount = -1;
		speed = 120;
		prevToggle = null;
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
		textSize(12);
		
		
		if (board != null) {
			board.draw(this, 0, 0, height, height);
		}
		
	}
	
	
	public void mousePressed() {
		if (mouseButton == LEFT) {
			Point click = new Point(mouseX,mouseY);
			float dimension = height;
			Point cellCoord = board.clickToIndex(click,0,0,dimension,dimension);
			if (cellCoord != null) {
				board.clickErase(cellCoord.x, cellCoord.y);
				prevToggle = cellCoord;
			}
		} 
	}
	
//	
//	public void mouseDragged() {
//		if (mouseButton == LEFT) {
//			Point click = new Point(mouseX,mouseY);
//			float dimension = height;
//			Point cellCoord = board.clickToIndex(click,0,0,dimension,dimension);
//			if (cellCoord != null && !cellCoord.equals(prevToggle)) {
//				//board.toggleCell(cellCoord.x, cellCoord.y);
//				prevToggle = cellCoord;
//			}
//		} 
//	}
//	
//	
//	public void keyPressed() {
//		if (keyCode == KeyEvent.VK_SPACE) {
//			if (runCount >= 0)
//				runCount = -1;
//			else
//				runCount = 0;
//		} else if (keyCode == KeyEvent.VK_DOWN) {
//			speed = Math.min(MAX_SPEED, speed*2);
//		} else if (keyCode == KeyEvent.VK_UP) {
//			speed = Math.max(MIN_SPEED, speed/2);
//			runCount = Math.min(runCount, speed);
//		} else if (keyCode == KeyEvent.VK_ENTER) {
//			//board.step();
//		}
//	}

	
}










