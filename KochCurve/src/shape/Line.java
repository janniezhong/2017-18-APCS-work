package shape;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import processing.core.PApplet;

/**
 * 
 * @author janniezhong
 * @version 1
 * A class that represents a line
 * 
 */
public class Line extends Shape{

	private float x2, y2;
	private float slope;
	private float angle;
	private float length;


	/**
	 * Draws a line with the endpoints at (x1, y1) and (x2, y2)
	 * @param x1 the x-coordinate of the first endpoint
	 * @param y1 the y-coordinate of the first endpoint
	 * @param x2 the x-coordinate of the second endpoint
	 * @param y2 the y-coordinate of the second endpoint
	 * @param strokeWeight the stroke weight of the line
	 * @param c color of the fill of the shape
	 * @param hasFill whether the shape should have a fill
	 * @param s color of the stroke of the shape
	 * @param hasStroke whether the shape should have a stroke
	 */
	public Line (double x1, double y1, int x2, int y2, int strokeWeight, Color c, boolean hasFill, Color s, boolean hasStroke) {
		super(x1, y1, strokeWeight, c, hasFill, s, hasStroke);

		this.x2 = (float) x2;
		this.y2 = (float) y2;
		slope = (((float)this.y2-(float)y)/((float)this.x2-(float)this.x));
		
		angle = (float) Math.sin((this.y2 - y1)/length);
		if (this.x2 - x1 >= 0) {
			
		} else {
			angle = 180-angle;
		}
		length = (float) Math.sqrt(Math.pow(this.x2-x1, 2) + Math.pow(this.y2 - y1, 2));
		
	}
	/**
	 * 
	 * @param x1 x-coordinate of the first endpoint
	 * @param y1 y-coordinate of the first endpoint
	 * @param length length of the line
	 * @param angle angle the line is positioned at, measured from the positive x-axis, in radians
	 * @param strokeWeight the stroke weight of the line
	 * @param c color of the fill of the shape
	 * @param hasFill whether the shape should have a fill
	 * @param s color of the stroke of the shape
	 * @param hasStroke whether the shape should have a stroke
	 */
	public Line(double x1, double y1, double length, double angle, int strokeWeight, Color c, boolean hasFill, Color s, boolean hasStroke) {
		super(x1, y1, strokeWeight, c, hasFill, s, hasStroke);
		this.length = (float) length;
		this.angle = (float) angle;
		y2 = (float) (y1 - (Math.sin(this.angle)*this.length));
		x2 = (float) (x1 + (Math.cos(this.angle)*this.length));
		
		slope = (((float)this.y2-(float)y)/((float)this.x2-(float)this.x));

		
	}


	/**
	 * Draws this line at the previously specified points and stroke weight
	 * @param drawer the PApplet object that draws the line
	 * @pre drawer settings that are applied beforehand (except stroke weight) affect this line
	 * @post sets the slope of this line
	 */
	public void draw(PApplet drawer) {
		//drawer.pushMatrix();
		super.draw(drawer);
		
		drawer.line((float)x, (float)y, x2, y2);
		
		//drawer.popMatrix();
	}

	/**
	 * Checks to see if the object and the passed-in line intersect with each other at any point
	 * @param l2 the Line that is being tested for intersection with
	 * @return whether the two lines intersect or not
	 */
	public boolean intersects (Line l2) {

		float crossX = getCrossLocX(l2);
		float crossY = getCrossLocY(l2);
		//System.out.println (x1 + " " + y1 + " " + x2 + " " + y2);
		//System.out.println("(" + crossX + ", " + crossY + ")");

		float leftX = Math.min((float)x, x2);
		float rightX = Math.max((float)x, x2);
		float upY = Math.min((float)y, y2);
		float downY = Math.max((float)y, y2);

		float leftX2 = Math.min((float)l2.x, l2.x2);
		float rightX2 = Math.max((float)l2.x, l2.x2);
		float upY2 = Math.min((float)l2.y, l2.y2);
		float downY2 = Math.max((float)l2.y, l2.y2);


		//equation of the line would be y = slope(x-x1) + y1
		if (Math.abs(Math.abs(this.slope) - Math.abs(l2.slope)) < 0.001) { /////////////////////////// Fix this later, math with doubles
			return false;
		} else { // assume x1 < x2, y1 < y2
			if (crossX >= leftX && crossX <= rightX && crossY >= upY && crossY <= downY) {
				if (crossX >= leftX2 && crossX <= rightX2 && crossY >= upY2 && crossY <= downY2) {
					return true;
				} else {
					return false;
				}

			}

			return false;
		}

	}
	/**
	 * Gets the x-coordinate of the intersect point between the two lines
	 * @param l2 the line that this Line is intersecting with
	 * @return the x-location of the intersection point
	 */
	public float getCrossLocX(Line l2) {
		float crossX = (float) (((x*y2-(float)y*x2)*(l2.x-l2.x2) - (x-x2)*(l2.x*l2.y2 - (float)l2.y*l2.x2))
				/((x-x2)*((float)l2.y-l2.y2) - ((float)y-y2)*(l2.x-l2.x2)));
		return crossX;
	}
	/**
	 * Gets the y-coordinate of the intersect point between the two lines
	 * @param l2 the line that this Line is intersecting with
	 * @return the y-location of the intersection point
	 */
	public float getCrossLocY(Line l2) {
		float crossY = (float) (((x*y2-(float)y*x2)*((float)l2.y-l2.y2) - ((float)y-y2)*(l2.x*l2.y2 - (float)l2.y*l2.x2))
				/((x-x2)*((float)l2.y-l2.y2) - ((float)y-y2)*(l2.x-l2.x2)));
		return crossY;
	}
	/**
	 * Sets the second endpoint of the line to the method-specified (x2, y2)
	 * @param x2 the x-coordinate of the second endpoint of this Line
	 * @param y2 the y-coordinate of the second endpoint of this Line
	 * @post changes the values of the coordinates of the originally specified second point to the new coordinate points
	 */
	public void setPoint2 (double x2, double y2) {
		this.x2 = (float) x2;
		this.y2 = (float) y2;
		slope = (((float)this.y2-(float)y)/((float)this.x2-(float)this.x));
	}
	
	public void setColor (Color c) {
		super.setColor(c);
	}
	
	public void setStrokeWeight(int x) {
		super.setStrokeWeight(x);
	}


	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public double getPerimeter() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Point2D.Double getCenter() {
		// TODO Auto-generated method stub
		return new Point2D.Double((x-x2)/2,((float)y-y2)/2);
	}
	
	public void setStrokeColor(Color c) {
		super.setStrokeColor(c);
	}
	
	public void hasStroke(boolean b) {
		super.hasStroke(b);
	}
	
	public double getX2() {
		return x2;
	}
	
	public double getY2() {
		return y2;
	}
	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean isPointInside(double x, double y) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Sets the first point of the line
	 * @param x the x-coordinate of the new first point
	 * @param y the y-coordinate of the new first point
	 */
	public void setPoint1 (double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getLength() {
		return length;
		
	}
	public double getAngle() {
		return angle;
		
	}

	public void translate(int x, int y) {
		setPoint1(this.x + x, this.y + y);
		setPoint2(x2 + x, y2 + y);
		
	}
}
