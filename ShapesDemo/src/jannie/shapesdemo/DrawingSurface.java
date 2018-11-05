package jannie.shapesdemo;

import poist.shapes.*;
import java.awt.Color;
import processing.core.PApplet;

/**
 * 
 * @author jzhong672
 * @version 2
 *
 */

public class DrawingSurface extends PApplet {
	private PhysicsShape player1;
	private int playerX, playerY;
	private int puckX, puckY;
	private int initX, initY, finalX, finalY;

	private PhysicsShape hockeyPuck;
	private PhysicsShape borderUp, borderLeft, borderRight, borderDown, semiCir1, goal;

	public DrawingSurface() {

		playerX = 500;
		playerY = 275;
		puckX = 300;
		puckY = 275;

		player1 = new PhysicsShape(new Circle (playerX, playerY, 75, Color.BLUE, Color.BLUE, 0));
		hockeyPuck = new PhysicsShape(new Circle(puckX, puckY, 55, Color.GREEN, Color.GREEN, 0));

		borderUp = new PhysicsShape(new Line(0, 50, 650, 50, Color.BLACK, Color.BLACK, 10));
		borderLeft = new PhysicsShape(new Line(1, 50, 0, 500, Color.BLACK, Color.BLACK, 10));
		borderRight = new PhysicsShape(new Line(650, 50, 650, 500, Color.BLACK, Color.BLACK, 10));
		borderDown = new PhysicsShape(new Line(0, 500, 650, 500, Color.BLACK, Color.BLACK, 10));
		semiCir1 = new PhysicsShape (new Circle(0, 275, 50, Color.BLACK, Color.BLACK, 200));
		goal = new PhysicsShape(new Rectangle(600, 225, 50, 100, Color.BLACK, Color.WHITE, 10));

	}

	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {

	}

	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() { 

		background(255);   // Clear the screen with a white background
		borderUp.draw(this);
		borderLeft.draw(this);
		borderDown.draw(this);
		borderRight.draw(this);
		semiCir1.draw(this);
		goal.draw(this);

		hockeyPuck.draw(this);
		player1.draw(this);


		
		
		if (mousePressed) {
			mouseDragged();
		} else {
			
			mouseReleased();

		}


		player1.act();
		
		if (hockeyPuck.isPointInside(player1.getX(), player1.getY())) {
			double xVel = hockeyPuck.getX() - player1.getX();
			double yVel = hockeyPuck.getY() - player1.getY();
			hockeyPuck.setVelocity(xVel/2, yVel/2);
		}
		
		if (hockeyPuck.getY() < 95 || hockeyPuck.getY() > 460) {

			hockeyPuck.bounce(0);
		}
		if (hockeyPuck.getX() < 30 || hockeyPuck.getX() > 610) {

			hockeyPuck.bounce(1);
		}
		
		hockeyPuck.act();

	}

	public void mousePressed() {
//		if (player1.isPointInside(mouseX, mouseY)) {
//			initX = mouseX;
//			initY = mouseY;
//		}
	}

	public void mouseDragged() { //can't move too fast

		int x = mouseX;
		int y = mouseY;

		if (player1.isPointInside(x, y)) {
			if (y <= 100) {
				if (x <= 50) {
					player1.move(50, 100);
				} else if (x >= 600) {
	
					player1.move(600, 100);
				} else {

					player1.move(x, 100);
				}
			} else if (y >= 450) {
				if (x <= 50) {

					player1.move(50, 450);
				} else if (x >= 600) {

					player1.move(600, 450);
				} else {

					player1.move(x, 450);
				}
			} else if (x <= 50) {

				player1.move(50, y);
			} else if (x >= 600) {
	
				player1.move(600, y);
			} else {
	
				player1.move(x, y);
			}
		}

	}

	public void mouseReleased() {

		

	}



}










