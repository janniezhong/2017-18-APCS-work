package shape;
import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;

import processing.core.PApplet;


/**
  @(#)KochCurve.java


  @author Jannie Z
  @version 2/9/17
 */
public class GosperCurve {

	// TO DO
	private int level;
	private int x, y;
	private double angle, length;


	public GosperCurve(int x, int y, int level, double length, double angle) {
		this.level = level;
		this.length = length;
		this.angle = angle;
		this.x = x;
		this.y = y;
	}

	public void draw(PApplet drawer) {
		drawGosperCurve(x, y, drawer, level, length, angle, true);
	}

	private Point2D.Double drawGosperCurve(int x, int y, PApplet drawer, int level, double length, double angle, boolean version1) {

		// 	int adjustment = length/21;
		// TO DO
		if (level < 1) {

			//angle = angle + Math.PI/2;
			if (version1) {
				Line l1 = new Line((double)x, (double)y, (double) length, (double) angle, 2, Color.BLACK, true, Color.BLACK, true);
				l1.draw(drawer);

				angle = angle - Math.PI/3;

				Line l2 = new Line(l1.getX2(), l1.getY2(), (double) length, (double) angle, 2, Color.BLACK, true, Color.BLACK, true);
				l2.draw(drawer);

				angle = angle - Math.PI/3;
				angle = angle - Math.PI/3;

				Line l3 = new Line(l2.getX2(), l2.getY2(), (double) length, (double) angle, 2, Color.BLACK, true, Color.BLACK, true);
				l3.draw(drawer);

				angle = angle + Math.PI/3;

				Line l4 = new Line(l3.getX2(), l3.getY2(), (double) length, (double) angle, 2, Color.BLACK, true, Color.BLACK, true);
				l4.draw(drawer);

				angle = angle + Math.PI/3;
				angle = angle + Math.PI/3;

				Line l5 = new Line(l4.getX2(), l4.getY2(), (double) length, (double) angle, 2, Color.BLACK, true, Color.BLACK, true);
				l5.draw(drawer);
				Line l6 = new Line(l5.getX2(), l5.getY2(), (double) length, (double) angle, 2, Color.BLACK, true, Color.BLACK, true);
				l6.draw(drawer);

				angle = angle + Math.PI/3;

				Line l7 = new Line(l6.getX2(), l6.getY2(), (double) length, (double) angle, 2, Color.BLACK, true, Color.BLACK, true);
				l7.draw(drawer);

				Point2D.Double p = new Point2D.Double(l7.getX2(), l7.getY2());

				angle = angle - Math.PI/3;

				return p;

			} 
			else {

				angle = angle + Math.PI/3;

				Line l1 = new Line((double)x, (double)y, (double) length, (double) angle, 2, Color.BLACK, true, Color.BLACK, true);
				l1.draw(drawer);

				angle = angle - Math.PI/3;

				Line l2 = new Line(l1.getX2(), l1.getY2(), (double) length, (double) angle, 2, Color.BLACK, true, Color.BLACK, true);
				l2.draw(drawer);
				Line l3 = new Line(l2.getX2(), l2.getY2(), (double) length, (double) angle, 2, Color.BLACK, true, Color.BLACK, true);
				l3.draw(drawer);

				angle = angle - Math.PI/3;
				angle = angle - Math.PI/3;

				Line l4 = new Line(l3.getX2(), l3.getY2(), (double) length, (double) angle, 2, Color.BLACK, true, Color.BLACK, true);
				l4.draw(drawer);

				angle = angle - Math.PI/3;

				Line l5 = new Line(l4.getX2(), l4.getY2(), (double) length, (double) angle, 2, Color.BLACK, true, Color.BLACK, true);
				l5.draw(drawer);

				angle = angle + Math.PI/3;
				angle = angle + Math.PI/3;


				Line l6 = new Line(l5.getX2(), l5.getY2(), (double) length, (double) angle, 2, Color.BLACK, true, Color.BLACK, true);
				l6.draw(drawer);

				angle = angle + Math.PI/3;

				Line l7 = new Line(l6.getX2(), l6.getY2(), (double) length, (double) angle, 2, Color.BLACK, true, Color.BLACK, true);
				l7.draw(drawer);

				Point2D.Double p = new Point2D.Double(l7.getX2(), l7.getY2());

				return p;
			}

		} else {
			
			length = (length/2);

			if (version1) { //A

				Point2D.Double p1 = drawGosperCurve(x,y,drawer,level-1, length, 0+angle, true);    		

				angle = angle - Math.PI/3;

				Point2D.Double p2 = drawGosperCurve((int)p1.getX(),(int)p1.getY(),drawer,level-1, length, 0+angle, false);    		

				angle = angle - Math.PI/3;
				angle = angle - Math.PI/3;

				Point2D.Double p3 = drawGosperCurve((int)p2.getX(),(int)p2.getY(),drawer,level-1, length, 0+angle, false);    		

				angle = angle + Math.PI/3;

				Point2D.Double p4 = drawGosperCurve((int)p3.getX(),(int)p3.getY(),drawer,level-1, length, 0+angle, true);    		

				angle = angle + Math.PI/3;
				angle = angle + Math.PI/3;

				Point2D.Double p5 = drawGosperCurve((int)p4.getX(),(int)p4.getY(),drawer,level-1, length, 0+angle, true);    		

				Point2D.Double p6 = drawGosperCurve((int)p5.getX(),(int)p5.getY(),drawer,level-1, length, 0+angle, true);    		

				angle = angle + Math.PI/3;

				Point2D.Double p7 = drawGosperCurve((int)p6.getX(),(int)p6.getY(),drawer,level-1, length, 0+angle, false);    		

				angle = angle - Math.PI/3;

				return p7;


			} else { //B

				angle = angle + Math.PI/3;

				Point2D.Double p1 = drawGosperCurve(x,y,drawer,level-1, length, 0+angle, true);    		

				angle = angle - Math.PI/3;

				Point2D.Double p2 = drawGosperCurve((int)p1.getX(),(int)p1.getY(),drawer,level-1, length, 0+angle, false);    		
				Point2D.Double p3 = drawGosperCurve((int)p2.getX(),(int)p2.getY(),drawer,level-1, length, 0+angle, false);    		

				angle = angle - Math.PI/3;
				angle = angle - Math.PI/3;

				Point2D.Double p4 = drawGosperCurve((int)p3.getX(),(int)p3.getY(),drawer,level-1, length, 0+angle, false);    		

				angle = angle - Math.PI/3;

				Point2D.Double p5 = drawGosperCurve((int)p4.getX(),(int)p4.getY(),drawer,level-1, length, 0+angle, true);    		

				angle = angle + Math.PI/3;
				angle = angle + Math.PI/3;

				Point2D.Double p6 = drawGosperCurve((int)p5.getX(),(int)p5.getY(),drawer,level-1, length, 0+angle, true);    		

				angle = angle + Math.PI/3;

				Point2D.Double p7 = drawGosperCurve((int)p6.getX(),(int)p6.getY(),drawer,level-1, length, 0+angle, false);    		

				return p7;
			}

		}



	}

}
