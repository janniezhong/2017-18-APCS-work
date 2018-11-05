
import java.awt.Color;
import java.awt.geom.Point2D;

import processing.core.PApplet;

/**
 * A class that represents a shape
 * @author janniezhong
 *
 */
public abstract class Shape {

	/**
	 * x-coordinate location of the upper left hand corner of the shape
	 */
	protected double x;
	/**
	 * y-coordinate location of the upper left hand corner of the shape
	 */
	protected double y;
	private Color color, strokeColor;
	private int strokeWeight;
	private boolean hasFill, hasStroke;

	/**
	 * Creates a shape at location (x,y), with the specified strokeWeight and color
	 * 
	 * @param x2 x-coordinate location of the upper left hand corner of the shape
	 * @param y2 y-coordinate location of the upper left hand corner of the shape
	 * @param strokeWeight2 weight of the stroke of the line outlining the shape
	 * @param c color of the fill of the shape
	 * @param fill whether the shape should have a fill
	 * @param s color of the stroke of the shape
	 * @param stroke whether the shape should have a stroke
	 * 
	 * 
	 */
	public Shape(double x2, double y2, int strokeWeight2, Color c, boolean fill, Color s, boolean stroke) {
		// TODO Auto-generated constructor stub
		this.x = x2;
		this.y = y2;
		this.strokeWeight = strokeWeight2;
		color = c;
		hasFill = fill;
		strokeColor = s;
		hasStroke = stroke;
	}

	/**
	 * Gets the area of the shape
	 * @return area of the shape
	 */
	public abstract double getArea();

	/**
	 * Gets the perimeter of the shape
	 * @return perimeter of the shape
	 */
	public abstract double getPerimeter();
	
	/**
	 * Gets the width of the shape
	 * @return width of the shape
	 */
	public abstract double getWidth();
	/**
	 * Gets the height of the shape
	 * @return height of the shape
	 */
	public abstract double getHeight();
	

	/**
	 * Gets the upper left x-coordinate of the shape
	 * @return integer representing x-value of the upper left x-coordinate
	 */
	public double getX() {
		return x;

	}

	/**
	 * Gets the upper left y-coordinate of the shape
	 * @return integer representing y-value of the upper left x-coordinate
	 */
	public double getY() {
		return y;

	}

	/**
	 * Draws a shape at the previously specified color, strokeWeight, and location
	 * @param drawer PApplet object that draws the shape
	 * @pre drawer settings that are applied before affect the shape, except for the thickness of the line and color
	 * @post drawer settings after the method is run are as such: fill is the previously specified Color, and strokeWeight is the set strokeWeight
	 */
	public void draw(PApplet drawer) {
		if (hasFill) {
			drawer.fill(color.getRGB());
		} else {
			drawer.noFill();
			
		//	System.out.println(color);
		}
		
		if(hasStroke) {
			drawer.stroke(strokeColor.getRGB());
		} else {
			drawer.noStroke();
		}
		
		drawer.strokeWeight(strokeWeight);
	}

	/**
	 * Gets the center of the shape
	 * @return a Point2D.Double representing the center of the shape
	 */
	public abstract Point2D.Double getCenter();

	/**
	 * Changes the color of a shape to Color c
	 * @param c color that the shape will be changed to
	 */
	public void setColor(Color c) {
		color = c;
	}

	/**
	 * Moves a shape to the specified (x, y) with the coordinate point at the upper left corner
	 * @param x the x-coordinate to move to
	 * @param y the y-coordinate to move to
	 */
	public void move (double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Moves a shape a specified distance x to the right and y to the left
	 * @param x the horizontal distance to move by
	 * @param y the vertical distance to move by
	 */
	public void moveBy (double x, double y) {
		this.x += x;
		this.y += y;
	}

	/**
	 * Sets the strokeWeight of the shape
	 * @param a the width of the strokeWeight of the shape
	 */
	public void setStrokeWeight(int a) {
		strokeWeight = a;
	}

	/**
	 * Sets whether the shape should be filled in or not
	 * @param fill a boolean representing whether shape should be filled or not
	 */
	public void isFilled(boolean fill) {
		hasFill = fill;
	}
	
	/**
	 * Tests to see whether a specified point is inside
	 * @param x x-coordinate point of the point being tested
	 * @param y y-coordinate point of the point being tested
	 * @return whether the point is inside or not
	 */
	public abstract boolean isPointInside(double x, double y);
	
	/**
	 * Sets the stroke color of the shape
	 * @param c the color to set the shape to
	 */
	public void setStrokeColor(Color c) {
		strokeColor = c;
	}
	
	/**
	 * Sets whether the shape has a stroke or not
	 * @param stroke a boolean representing whether a shape has a stroke
	 */
	public void hasStroke(boolean stroke) {
		hasStroke = stroke;
	}
}