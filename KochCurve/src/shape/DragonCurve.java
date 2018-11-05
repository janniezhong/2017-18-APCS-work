package shape;
import java.awt.Color;

import processing.core.PApplet;


/**
  @(#)DragonCurve.java


  @author Jannie Z
  @version 2/9/17
*/
public class DragonCurve {

	// TO DO
	private int level;
	private int x, y;
	private double length, angle;
	

    public DragonCurve(int x, int y, int level, double length, double angle) {
    	this.level = level;
    	this.length = length*4;
    	this.angle = angle;
    	this.x = x;
    	this.y = y;
    }
    
    public void draw(PApplet drawer) {
    	drawDragonCurve(x, y, drawer, level, length, angle, false);
    }

    private void drawDragonCurve(int x, int y, PApplet drawer, int level, double length, double angle, boolean popLeft) {
    	
   // 	int adjustment = length/21;
    	// TO DO
    	if (level < 1) {
    		Line l = new Line((double)x, (double)y, (double) length, (double) angle+0, 2, Color.BLACK, true, Color.BLACK, true);
    		l.draw(drawer);
    	} else {
    		
    		if (popLeft) {
    			
    			Line l1 = new Line((double)x, (double)y, (double) length/Math.sqrt(2), (double) angle+Math.PI/4, 2, Color.BLACK, true, Color.BLACK, false);
        	
    			//l1.draw(drawer);
    			
    			drawDragonCurve(x,y,drawer,level-1, (length)/Math.sqrt(2), Math.PI/4+angle, false);    		

    			drawDragonCurve((int)l1.getX2(), (int)l1.getY2(), drawer, level -1, length/Math.sqrt(2), -Math.PI/4 + angle, true);
    			
    		} else {
    			
    			Line l1 = new Line((double)x, (double)y, (double) length/Math.sqrt(2), (double) angle-Math.PI/4, 2, Color.BLACK, true, Color.BLACK, false);
            	
    			//l1.draw(drawer);
    			
    			drawDragonCurve(x,y,drawer,level-1, (length)/Math.sqrt(2), -Math.PI/4+angle, false);    		

    			drawDragonCurve((int)l1.getX2(), (int)l1.getY2(), drawer, level -1, length/Math.sqrt(2), Math.PI/4 + angle, true);
    			
    		}
    		
    		
    		
//    		
//    		drawDragonCurve(x,y,drawer,level-1, (length)/3, 0+angle);    		
//    		
//    		
//    		Line l1 = new Line((double)x, (double)y, ((double) length)/3.0 /* + adjustment*/, 0.0+angle, 2, Color.BLACK, true, Color.BLACK, true);
//    		
//    		//l1.draw(drawer);
//    		
//    		drawDragonCurve((int)l1.getX2(), (int)l1.getY2(), drawer, level-1, (length)/3, Math.PI/3+angle);    		
//    		
//    		
//    		Line l2 = new Line(l1.getX2(), l1.getY2(), ((double) length)/3.0/* + adjustment*/, Math.PI/3+angle, 2, Color.BLACK, true, Color.BLACK, true);
//    		drawDragonCurve((int)l2.getX2(), (int)l2.getY2(), drawer, level-1, (length)/3, -(Math.PI/3)+angle);    		
//
//    		
//    		
//    		Line l3 = new Line(l2.getX2(), l2.getY2(), ((double) length)/3.0 /* + adjustment*/, -(Math.PI/3)+angle, 2, Color.BLACK, true, Color.BLACK, true);
//    		drawDragonCurve((int)l3.getX2(), (int)l3.getY2(), drawer, level-1, (length)/3, 0+angle);    		
//
//    		
    	}
    	
    	
    }

}
