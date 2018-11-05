package jannie.testers;
import java.awt.Color;
import java.awt.geom.Point2D;

import jannie.shapes.*;
import processing.core.PApplet;

/**
 * 
 * @author jzhong672
 * 
 *
 */

public class DrawingSurface extends PApplet {

	private Rectangle rA;
	private Circle cA;
	private Line lA;
	private RegularPolygon A, B, C, D, E, Z;
	private double iterations;
	
	public DrawingSurface() {
		rA = new Rectangle();
		cA = new Circle();
		lA = new Line(200, 200, 50, Math.PI/2, 10, Color.BLACK, true, Color.BLACK, true);
		A = new RegularPolygon();
		B = new RegularPolygon(5, 100, 500, 500, 1, Color.BLUE, true, Color.BLUE, true);
		C = new RegularPolygon(8, 100, 100, 300, 1, Color.RED, false, Color.RED, true);
		D = new RegularPolygon(19, 5, 200, 500, 1, Color.MAGENTA, true, Color.BLACK, true);
		E = new RegularPolygon(91, 5, 30, 50, 1, Color.BLUE.darker(), false, Color.RED, true);
		
		iterations = 3;

		runSketch();
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
		fill(255);
		textAlign(CENTER);
		
		if (rA != null) {
			stroke(255,0,0);
			fill(255);
			rA.draw(this);
			Point2D.Double center = rA.getCenter();
			fill(0);
			text(rA.getPerimeter()+"\n"+rA.getArea(),(float)center.x,(float)center.y);
		}
		if (cA != null) {
			stroke(0,255,0);
			fill(255);
			cA.draw(this);
			Point2D.Double center = cA.getCenter();
			fill(0);
			text(cA.getPerimeter()+"\n"+cA.getArea(),(float)center.x,(float)center.y);
		}

		fill(0);
		textSize(12);
				
		lA.draw(this);
		
		A.draw(this);
		B.draw(this);
		C.draw(this);
		
		D.draw(this);
		E.draw(this);
		
		
		Z = new RegularPolygon((int)(iterations), 50, 400, 400, 3, Color.BLACK, true, Color.BLACK, true);
		Z.draw(this);
		iterations = iterations + 0.05;
		if(iterations > 13) {
			iterations = 0;
		}
		A.drawBoundingCircles(this);
		Z.drawBoundingCircles(this);
		
		
	}
	
	
	public void mousePressed() {
		if (mouseButton == LEFT) {
			rA = new Rectangle(mouseX, mouseY, 0, 0, 1, Color.WHITE, false, Color.GREEN, true);
		} else if (mouseButton == RIGHT)
			cA = new Circle(mouseX,mouseY,0,1, Color.WHITE, false, Color.RED, true);
	}
	
	
	public void mouseDragged() {
		if (mouseButton == LEFT) {
			rA.setBottomRight(mouseX,mouseY);
		} else if (mouseButton == RIGHT) {
			cA.setSize(mouseX);
		}
	}
	
	
}










