package alonkatz.shapesDemo;

import processing.core.PApplet;

import java.awt.Color;

import jannie.shapes.Circle;
import jannie.shapes.Line;
import jannie.shapes.Rectangle;
import jannie.shapes.Shape;
import processing.awt.PSurfaceAWT;

public class DrawingSurface extends PApplet {

	private PhysicsShape circ1;
	private PhysicsShape circ2;
	private boolean setShapeVelocity;
	private boolean draggingShape;
	private boolean pressedShape;
	private boolean insideShape;
	private int counter;
	private Line powerStick;
	private Line directionLine;
	private boolean stickReleased;

	public DrawingSurface() {
		// circ1 = new PhysicsShape(new Circle(200, 200, 10, 2, Color.RED, true,
		// Color.blue, true));
		width = 400;
		height = 300;

		Circle circ2object = new Circle(175, 50, 10, 1, Color.white, true, Color.white, true);
		Circle circ1object = new Circle(200, 50, 10, 1, Color.red, true, Color.red, true);

		circ2 = new PhysicsShape(circ2object);
		circ1 = new PhysicsShape(circ1object);
		powerStick = new Line(circ2.getX(), circ2.getY(), circ2.getX(), circ2.getY(), 3, Color.black, true, Color.black,
				true);
		directionLine = new Line(circ1.getX(), circ1.getY(), circ1.getX(), circ1.getY(), 3, Color.black, true, Color.black,
				true);

	}

	public void draw() {
		background(Color.green.getRGB()); // Clear the screen with a white background
		

		//

		// circ2.calculateVelocity(mouseX, mouseY);
		if (draggingShape) {
			// circ2.drag(mouseX, mouseY, this.width, this.height);
			if(aiming) {
				directionLine.draw(this);
			}
			powerStick.draw(this);
		}

		if (stickReleased) {
			setUpStick();
			x += power * -xMultiplier * Math.cos(angle);
			y += power * -yMultiplier * Math.sin(angle);

			if (distanceFromCircle < circ2.getWidth() / 2) {
				circ2.setVelocity(power * -xMultiplier * Math.cos(angle), power * -yMultiplier * Math.sin(angle),
						circ1);
				draggingShape = false;
				pressedShape = false;
				stickReleased = false;
			}
		}

		circ1.draw(this);
		circ2.draw(this);
		circ1.act(this.width, this.height, circ2);
		circ2.act(this.width, this.height, circ1);

		

	}

	public void mousePressed() {
		setUpStick();
		setShapeVelocity = true;
		insideShape = circ2.checkIfPointIsInside((double) mouseX, (double) mouseY);
		if (insideShape) {
			circ2.setDragging(true);
			pressedShape = true;
			// circ2.setupCalculateVelocity(mouseX, mouseY);

		}
	}

	private double slope;
	private double angle;
	private double xMultiplier;
	private double yMultiplier;
	private double distanceFromCircle;
	private double powerPercent;
	private double power;
	private double x;
	private double y;
	
	private boolean aiming;

	double smallX;
	double smallY;
	double xmult;
	double ymult;
	double smallSlope;
	
	public void setUpStick() {
		smallX = circ2.getX();
		smallY = circ2.getY();
		int length = 100;
		distanceFromCircle = 0;
		slope = 0;
		xMultiplier = 0;
		yMultiplier = 0;
		angle = 0;
	
		
		if (Math.sqrt(mouseX * mouseX + mouseY * mouseY) > 50);

		if (mouseX > circ2.getX()) {
			xMultiplier = 1;
		} else if (mouseX < circ2.getX()) {
			xMultiplier = -1;
		} else {
			xMultiplier = 0;
		}

		if (mouseY > circ2.getY()) {
			yMultiplier = 1;
		} else if (mouseY < circ2.getY()) {
			yMultiplier = -1;
		} else {
			yMultiplier = 0;
		}

		slope = (mouseY - circ2.getY()) / (mouseX - circ2.getX());
		if (mouseX - circ2.getX() == 0) {
			angle = Math.PI / 2;
		} else {
			angle = Math.atan(Math.abs(slope));
		}

		if (!stickReleased) {
			distanceFromCircle = Math.sqrt((mouseX - circ2.getX()) * (mouseX - circ2.getX())
					+ (mouseY - circ2.getY()) * (mouseY - circ2.getY()));
			if (distanceFromCircle > 50) {
				x = circ2.getX() + xMultiplier * Math.cos(angle) * 50;
				y = circ2.getY() + yMultiplier * Math.sin(angle) * 50;
			} else {
				x = mouseX;
				y = mouseY;
			}
		} else {
			distanceFromCircle = Math.sqrt((x - circ2.getX()) * (x - circ2.getX())
					+ (y - circ2.getY()) * (y - circ2.getY()));
			
		}

		System.out.println(x);
		double secondPointX = x + xMultiplier * length * Math.cos(angle);
		double secondPointY = y + yMultiplier * length * Math.sin(angle);

		if (distanceFromCircle > circ2.getWidth() / 2) {
			powerStick.setPoint1(x, y);
			powerStick.setPoint2(secondPointX, secondPointY);
			int length2 = 30;
			
			//int e = 0;
			//while (e == 0) {
				smallX += -xMultiplier* Math.cos(angle)*.01;
				smallY += -yMultiplier*Math.sin(angle)*.01;
				
				
				if (Math.sqrt((smallX - circ1.getX()) * (smallX - circ1.getX())
					+ (smallY - circ1.getY()) * (smallY - circ1.getY())) < circ1.getWidth()/2) {
					smallSlope = (smallY - circ1.getY())/(smallX - circ1.getX());
					double angle2 = Math.atan(Math.abs(smallSlope));
					if (smallX > circ1.getX()) {
						xmult = -1;
					} else if (smallX < circ1.getX()) {
						xmult = 1;
					} else {
						xmult = 0;
					}
					
					if (smallY > circ1.getY()) {
						ymult = -1;
					} else if (smallY < circ1.getY()) {
						ymult = 1;
					} else {
						ymult = 0;
					}
					
					double sX = circ1.getX() + xmult * length * Math.cos(angle2);
					double sY = circ1.getY() + ymult *length	* Math.cos(angle2);	
					//directionLine.setPoint1(circ1.getX(), circ1.getY());
					directionLine.setPoint2(sX, sY);
				//	e = 1;
					
					aiming = true;
				//}
			}
			
		} else {
			draggingShape = false;
		}
		
		
		
	}

	//
	public void mouseDragged() {

		if (pressedShape) {
			draggingShape = true;

			setUpStick();

		}
		//
		// counter++;
	}

	public void keyPressed() {
		if (keyCode == UP) {
			circ2.setVelocity(3, -6, circ1);
		}

		if (keyCode == DOWN) {
			circ2.setVelocity(0, 6, circ1);
		}

		if (keyCode == RIGHT) {
			circ2.setVelocity(6, 0, circ1);
		}

		if (keyCode == LEFT) {
			circ2.setVelocity(-6, 0, circ1);
		}
		
		if(key == 'r' || key == 'R') {
			circ2.resetIfGetsStuck();
			circ1.resetIfGetsStuck();
		}
	

	}

	public void stickActionWhenReleased() {
		stickReleased = true;
		
		if (distanceFromCircle > 50) {
			distanceFromCircle = 50;
		}
		powerPercent = distanceFromCircle / 50.0;
		power = powerPercent * 10;

	}

	public void mouseReleased() {
		pressedShape = false;
		if (draggingShape) {
			stickActionWhenReleased();
		}

		// draggingShape = false;
		// circ2.setDragging(false);

		// circ2.setVelocity(0, 0);
		// counter = 0;

	}

}
