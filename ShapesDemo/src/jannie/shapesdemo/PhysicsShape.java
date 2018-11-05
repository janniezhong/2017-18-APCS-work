package jannie.shapesdemo;

import poist.shapes.*;
import processing.core.PApplet;

public class PhysicsShape {
	private Shapes boundingShape;
	private double xVel;
	private double yVel;
	
	
	public PhysicsShape (Shapes s) {
		xVel = 0;
		yVel = 0;
		boundingShape = s;
	}

	public void draw (PApplet drawer) {
		boundingShape.draw(drawer);
	}
	
	public Shapes getBoundingShape() {
		return boundingShape;
	}
	
	public void setVelocity(double x, double y) {
		xVel = x;
		yVel = y;
		
		if (xVel > 50) {
			xVel = 50;
		}
		if (yVel > 50) {
			yVel = 50;
		}
	}
	
	public void act() {
		//boundingShape.move(boundingShape.getX()+xVel, boundingShape.getY()+yVel);
		boundingShape.moveBy(xVel, yVel);

		if (Math.abs(xVel) >= 0.001) {
			xVel = xVel*0.95;
		}

		if (Math.abs(yVel) >= 0.001) {
			yVel = yVel*0.95;
		}

	}

	public void slowDown() {

//		if (Math.abs(xVel) >= 0.01) {
//				xVel = xVel*0.5;
//		}
//		
//		if (Math.abs(yVel) >= 0.01) {
//			yVel = yVel*0.5;
//		}
	}
	
	public void bounce (int x) {
		if (x == 0) { //bounce horizontal boundaries
			yVel = -yVel;
			
		} else if (x == 1) { //vertical boundaries
			xVel = -xVel;
		} else {
			
		}
	}
	
	public void move(double x, double y) {
		boundingShape.move(x, y);
	}
	
	public boolean isPointInside(double x, double y) {
		
		//ewwwwww bad code
		boolean isPointInside = ((Circle)boundingShape).isPointInside(x, y);
		boundingShape = (Shapes)boundingShape; //uhhhhh
		return isPointInside;
	}
	
	public double getX () {
		return boundingShape.getX();
	}
	public double getY () {
		return boundingShape.getY();
	}
	
	
}
