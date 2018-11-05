package jannie.shapes;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import processing.core.PApplet;
import processing.core.PShape;

/**
 * A class that represents any regular polygon with a specified number of sides and side length
 * @author janniezhong
 * @version 10/15/17
 *
 */
public class RegularPolygon extends Shape{

	private int numSides;
	private double sideLength;
	private Circle outCircle, inCircle;
	private Line[] sides;
	private double iAngle;
	private double cAngle;
	private double oRadius, iRadius;
	private PShape s;
	private Color fillShape;
	private boolean isFilled;


	/**
	 * Creates a RegularPolygon with the default settings: 3 sides, a sideLength of 100 pixels, strokeWeight 1, fillColor and strokeColor black (and is filled), located with the center at (200, 200)
	 */
	public RegularPolygon() {
		super(200, 200, 1, Color.BLACK, true, Color.BLACK, true);
		isFilled = true;
		fillShape = Color.BLACK;
		sideLength = 100;
		numSides = 3;
		sides = new Line[3];
		iAngle = calcVertexAngle();
		cAngle = calcCentralAngle();
		calcR();
		calcr();
		double prevX = 200 - (sideLength/2.0);
		double prevY = 200 - iRadius;

		for (int i = 0; i < numSides; i++) {
			Line l = new Line(prevX, prevY, sideLength, (2*Math.PI-i*(Math.PI-iAngle)), 1, Color.BLACK, true, Color.BLACK, true);
			sides[i] = l;
			prevX = l.getX2();
			prevY = l.getY2();


		}
		
		inCircle = new Circle (200, 200, iRadius, 1, Color.GREEN, false, Color.GREEN, true);
		outCircle = new Circle (200, 200, oRadius, 1, Color.RED, false, Color.RED, true);
	}
	
	/**
	 * Creates a RegularPolygon with the specified number of sides, sideLength, strokeWeight, fillColor, strokeColor, located with the center at (x1, y1)
	 * @param numSides number of sides this RegularPolygon has
	 * @param sideLength the side length of one of the sides
	 * @param x1 the x-coordinate of the center of this RegularPolygon
	 * @param y1 the y-coordinate of the center of this RegularPolygon
	 * @param strokeWeight the strokeWeight of this RegularPolygon
	 * @param c the fill color of this RegularPolygon
	 * @param fill whether this RegularPolygon should be filled in or not 
	 * @param s the stroke color of this RegularPolygon
	 * @param stroke whether this RegularPolygon should have a stroke or not
	 */
	public RegularPolygon(int numSides, double sideLength, double x1, double y1, int strokeWeight, Color c, boolean fill, Color s, boolean stroke) {
		super(x1, y1, strokeWeight, c, fill, s, stroke);
		// TODO Auto-generated constructor stub
		isFilled = fill;
		fillShape = c;
		this.numSides = numSides;
		sides = new Line[this.numSides];
		this.sideLength = sideLength;
		iAngle = calcVertexAngle();
		cAngle = calcCentralAngle();
		calcR();
		calcr();

		double prevX = x1-(sideLength/2.0);
		double prevY = y1 - iRadius;

		for (int i = 0; i < numSides; i++) {
			Line l = new Line(prevX, prevY, sideLength, (2*Math.PI-i*(Math.PI-iAngle)), strokeWeight, c, fill, s, stroke);
			sides[i] = l;
			prevX = l.getX2();
			prevY = l.getY2();


		}
		
		inCircle = new Circle (x1, y1, iRadius, 1, Color.GREEN, false, Color.GREEN, true);
		outCircle = new Circle (x1, y1, oRadius, 1, Color.RED, false, Color.RED, true);

	}

	private void calcr() {
		//iRadius = (1.0/2.0* sideLength)/Math.tan(cAngle/2.0);
		
		iRadius = Math.tan(iAngle/2)*0.5*sideLength;
	}

	private void calcR() {
		oRadius = (1.0/2.0* sideLength)/Math.sin(cAngle/2.0);
	}



	//public methods

	/**
	 * Calculates the one of the vertices'/interior angle of the RegularPolygon
	 * @return the radian value of one of the interior angles
	 */
	public double calcVertexAngle() {
		iAngle = ((numSides-2)*Math.PI)/numSides;
		return iAngle;
	}

	/**
	 * Calculates the central angle value of the RegularPolygon
	 * @return the radian value of the central angle of the RegularPolygon
	 */
	public double calcCentralAngle() {
		cAngle = 2*Math.PI/numSides;

		return cAngle;
	}

	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		double area = numSides*(iRadius*0.5*sideLength);

		return area;
	}

	@Override
	public double getPerimeter() {
		// TODO Auto-generated method stub
		return numSides*sideLength;
	}

	/**
	 * Returns the number of sides the RegularPolygon has
	 * @return the number of sides the RegularPolygon has
	 */
	public int getNumSides() {
		return numSides;
	}

	/**
	 * Returns the side length of the RegularPolygon
	 * @return the side length of the RegularPolygon
	 */
	public double getSideLength() {
		return sideLength;
	}
	/**
	 * Returns the radius of the circumscribed circle
	 * @return the radius of the circumscribed circle
	 */
	public double getR() {
		return oRadius;
	}
	
	/**
	 * Returns the radius of the inscribed circle
	 * @return the radius of the inscribed circle
	 */
	public double getr() {
		return iRadius;
	}
	
	public void draw(PApplet marker) {
		super.draw(marker);
		
		s = marker.createShape();
		s.beginShape();
		if(isFilled) {
			s.fill(fillShape.getRGB());
		} else {
			s.noFill();
		}

		for (int i = 0; i < numSides; i++) {
			
			sides[i].draw(marker);
			int x = (int) sides[i].getX();
			int y = (int) sides[i].getY();
			s.vertex(x, y);			
		}
		s.endShape(marker.CLOSE);
		marker.shape(s, 0, 0);

	}

	/**
	 * Draws the green and red inscribed and circumscribed circles (respectively) with stroke weight 1, and no fill.
	 * @param drawer the PApplet object used to draw the circles
	 */
	public void drawBoundingCircles(PApplet drawer) {
		super.draw(drawer);

		inCircle.draw(drawer);
		outCircle.draw(drawer);
	}
	@Override
	public Point2D.Double getCenter() {
		// TODO Auto-generated method stub
		return new Point2D.Double(x, y);
	}
	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return oRadius*2;
	}
	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return oRadius*2;
	}

	@Override
	public boolean isPointInside(double x, double y) {
		// TODO Auto-generated method stub
		return false;
	}

}
