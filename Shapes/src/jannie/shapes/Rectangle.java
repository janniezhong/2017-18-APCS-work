package jannie.shapes;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * @author jzhong672
 * @version 2
 * 
 */

import processing.core.PApplet;

/**
 * 
 * A class that represents a circle
 * @author janniezhong
 * @version 1
 *
 */
public class Rectangle extends Shape {

	private double width, height;



	//Constructors
	// Creates a default instance of a Rectangle object with all dimensions
	//   set to zero.

	/**
	 * Creates a rectangle with the upper left corner at (0,0) with width 0 and height 0, stroke 0, in black and with a black outline (default values)
	 */
	public Rectangle() {

		this(0, 0, 0, 0, 0, Color.BLACK, true, Color.BLACK, true);
		
	}

	// Creates a new instance of a Rectangle object with the left and right
	// edges of the rectangle at x and x + width. The top and bottom edges
	// are at y and y + height.

	/**
	 * Creates a rectangle with the upper left corner at (x, y) with the specified width and height
	 * @param x The x-coordinate of the upper left corner
	 * @param y The y-coordinate of the upper left corner
	 * @param width The width of the rectangle
	 * @param height The height of the rectangle
	 * @param strokeWeight The thickness of the line that draws the rectangle
	 * @param c color of the fill of the shape
	 * @param hasFill whether the shape should have a fill
	 * @param s color of the stroke of the shape
	 * @param hasStroke whether the shape should have a stroke
	 */
	public Rectangle(double x, double y, double width, double height, int strokeWeight, Color c, boolean hasFill, Color s, boolean hasStroke) {
		
		super (x, y, strokeWeight, c, hasFill, s, hasStroke);
	
		this.width = width;
		this.height = height;

		

	}

	//Methods

	/**
	 * Gets the perimeter of the rectangle
	 * @return the perimeter of the rectangle
	 */
	public double getPerimeter() {
		double perimeter = (Math.abs(width) + Math.abs(height))*2;
		return perimeter;

	}

	/**
	 * Gets the area of the rectangle
	 * @return the area of the rectangle
	 * 
	 */
	public double getArea() {
		double area = Math.abs(width) * Math.abs(height);
		return area;

	}
	/**
	 * Checks to see whether the specified point is within the rectangle
	 * @param x x-coordinate of the point being tested
	 * @param y y-coordinate of the point being tested
	 * @return whether the point is inside the rectangle or not
	 */
	// Determines whether the point x,y is contained inside this rectangle
	public boolean isPointInside(double x, double y) {
		boolean isPointInside;
		if (width < 0 || height < 0) {
			if (x <= this.x && x >= (this.x + width) && y <= this.y && y >= (this.y + height)) {
				isPointInside = true;
			} else {
				isPointInside = false;
			}
		} else if (x >= this.x && x <= (this.x + width) && y >= this.y && y <= (this.y + height)) {
			isPointInside = true;
		} else {
			isPointInside = false;
		}

		return isPointInside;

	}

	/**
	 * Draws a rectangle with the upper left corner at the previously specified x and y-coordinates, and with the previously specified width, height. and stroke weight
	 * @param marker A PApplet object used to draw the circle
	 */
	// Draws a new instance of a Rectangle object with the left and right
	// edges of the rectangle at x and x + width. The top and bottom edges
	// are at y and y + height.
	public void draw(PApplet marker) {
		//marker.pushMatrix();
		super.draw(marker);
		marker.rect((float)x, (float)y, (float)width, (float)height);
		//marker.popMatrix();
	}

	/**
	 * 
	 * Gets the point of the center of the rectangle
	 * @return the center point of the rectangle
	 * 
	 */
	// Returns the center point of the rectangle
	public Point2D.Double getCenter() {
		return new Point2D.Double(x+width/2,y+height/2);
	}
	/**
	 * Sets the bottom right corner of the rectangle to the specified (x, y) coordinate point
	 * @param x the x-coordinate of the bottom right corner of the rectangle
	 * @param y the y-coordinate of the bottom right corner of the rectangle
	 * @post changes the width and height of the rectangle to the new values
	 */
	public void setBottomRight(double x, double y) {
		width = x-this.x;
		height = y-this.y;
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
		return width;
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return height;
	}


}
