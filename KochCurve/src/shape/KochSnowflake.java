package shape;

import java.awt.Color;

import processing.core.PApplet;

public class KochSnowflake {

	private int level;
	private int length, x, y, angle;



	public KochSnowflake(int x, int y, int level, int length, int angle) {
		this.level = level;
		this.length = length;
		this.angle = angle;
		this.x = x;
		this.y = y;
	}

	public void draw(PApplet drawer) {

		KochCurve k1 = new KochCurve(x, y, level, length, angle);
		k1.draw(drawer);
		Line l1 = new Line((double)x, (double)y, ((double) length*4)/*+length/12*/, 0.0+angle, 2, Color.BLUE, true, Color.BLUE, true);
		
		drawer.fill(100, 100, 0);
		//l1.draw(drawer);
		
		
		drawer.noStroke();
		KochCurve k2 = new KochCurve((int)l1.getX2(), (int)l1.getY2(), level, length, (angle - 2* Math.PI/3));
		k2.draw(drawer);
		
		Line l2 = new Line((int)l1.getX2(), (int)l1.getY2(), ((double) length*4)/*+length/12*/,(angle - 2* Math.PI/3)+ angle, 2, Color.BLUE, true, Color.BLUE, true);
		//l2.draw(drawer);
		
		KochCurve k3 = new KochCurve((int)l2.getX2(), (int)l2.getY2(), level, length, (angle - 4* Math.PI/3));
		k3.draw(drawer);
		
		
		Line l3 = new Line((int)l2.getX2(), (int)l2.getY2(), ((double) length*4)/*+length/12*/,(angle - 4* Math.PI/3)+ angle, 2, Color.BLUE, true, Color.BLUE, true);
		//l3.draw(drawer);
		
	
	}


}
