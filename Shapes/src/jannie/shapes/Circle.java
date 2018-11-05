package jannie.shapes;

import java.awt.Color;
import java.awt.geom.Point2D;

import processing.core.PApplet;

/**
 * 
 * @author jzhong672
 * @version 2
 * 
 * A class that represents a circle
 *
 */
public class Circle extends Shape{
	/**
	 * Represents the number pi from the Math class
	 */
	public final static double pi = Math.PI;
	private double r;


	/**
	 * Creates a circle with the center at (0, 0) and radius 0, filled in with black and with a black stroke
	 */
	public Circle() {
		this(0, 0, 0, 0, Color.BLACK, true, Color.BLACK, true);
	}

	/**
	 * Creates a circle with the center at (x, y) and specified radius and stroke weight
	 * @param x the x coordinate of the center
	 * @param y the y coordinate of the center
	 * @param radius the radius of the circle
	 * @param strokeWeight the weight of the stroke of the circle
	 * @param c color of the fill of the shape
	 * @param hasFill whether the shape should have a fill
	 * @param s color of the stroke of the shape
	 * @param hasStroke whether the shape should have a stroke
	 */
	public Circle(double x, double y, double radius, int strokeWeight, Color c, boolean hasFill, Color s, boolean hasStroke) {
		super(x, y, strokeWeight, c, hasFill, s, hasStroke);
		r = radius;


	}

	/**
	 * Gets the perimeter of the circle
	 * @return the perimeter of the circle
	 */
	// Calculates and returns the perimeter of the circle
	public double getPerimeter() {
		double perimeter = 2*pi*Math.abs(r);
		return perimeter;
	}

	/**
	 * Gets the area of the circle
	 * @return the area of the circle
	 */
	// Calculates and returns the area of the circle
	public double getArea() {
		double area = pi* Math.pow(r, 2);
		return area;

	}

	/**
	 * Tests to see whether a point at (x, y) is inside the specified circle
	 * @param x x-coordinate of the point being tested
	 * @param y y-coordinate of the point being tested
	 * @return whether the point is inside the circle or not
	 */
	@Override
	// Determines whether the point x,y is contained inside this circle
	public boolean isPointInside(double x, double y) {

		double sqrDistance = Math.pow(Math.abs(this.x-(x)), 2) + Math.pow(Math.abs(this.y-(y)), 2);
		double distance = Math.sqrt(sqrDistance);

		boolean isPointInside;
		if (distance <= r) {
			isPointInside = true;
		} else {
			isPointInside = false;
		}

		return isPointInside;
	}
	/**
	 * Draws the circle with the center at (x, y) and with the specified radius and stroke weight
	 * @param marker A PApplet object used to draw the circle
	 * @pre marker settings (beside ellipseMode and strokeWeight) that are applied beforehand will affect this circle
	 * @post marker will have all the previously set settings except ellipseMode and strokeWeight: ellipseMode will be in center mode and strokeWeight will be the specified value
	 */
	// Draws a new instance of a Circle object with the top left corner of the circumscribed
	// rectangle at (x, y), and with radius r
	public void draw(PApplet marker) {
		marker.pushMatrix();
		super.draw(marker);
	//	marker.strokeWeight(strokeWeight);
		marker.ellipseMode(marker.CENTER);
		marker.ellipse((float)(x),  (float)(y), (float)r * 2, (float)r * 2);
		marker.popMatrix();

	}
	
	/**
	 * Gets the center point of the circle
	 * @return the center point of the circle
	 * 
	 */
	// Returns the center point of the circle
	public Point2D.Double getCenter() {
		return new Point2D.Double(x+r,y+r);
	}
	/**
	 * Sets the bottom right corner of the circumscribed rectangle to the coordinate (0, 0)
	 * @param x the x-coordinate of the bottom right corner of the circumscribed square
	 * @post changes the radius of the circle to the new value
	 */
	public void setSize(double x) {
		r = (x-this.x);
		//r = (y-this.y)/2.0;

	}
	
	public void setColor (Color c) {
		super.setColor(c);
	}
	
	public void setStrokeWeight(int x) {
		super.setStrokeWeight(x);
	}
	
	public void setStrokeColor(Color c) {
		super.setStrokeColor(c);
	}
	
	public void hasStroke(boolean b) {
		super.hasStroke(b);
	}
	
	public void hasFill(boolean b) {
		super.isFilled(b);
	}
	
	public void move(int x, int y) {
		super.move(x, y);
	}
	
	public void moveBy(int x, int y) {
		super.moveBy(x, y);
	}
	
	public double getX() {
		return super.getX();
	}
	
	public double getY() {
		return super.getY();
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return r*2;
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return r*2;
	}
	





}
