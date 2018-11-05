package shape;
import java.awt.Color;

import processing.core.PApplet;


/**
  @(#)KochCurve.java


  @author Jannie Z
  @version 2/9/17
*/
public class CircleFractal {

	// TO DO
	private int level;
	private int length, x, y;
	

    public CircleFractal(int x, int y, int level, int length) {
    	this.level = level;
    	this.length = length*4;
    	this.x = x;
    	this.y = y;
    }
    
    public void draw(PApplet drawer) {
    	drawCircleFractal(x, y, drawer, level, length);
    }

    private void drawCircleFractal(int x, int y, PApplet drawer, int level, int length) {
    	
    	drawer.ellipseMode(drawer.RADIUS);
    	drawer.ellipseMode(drawer.CENTER);

    	
   // 	int adjustment = length/21;
    	// TO DO
    	if (level < 1) {

    		drawer.noFill();
    		drawer.ellipse(x, y, length, length);
    		
    	} else {
    		drawer.noFill();

    		drawer.ellipse(x, y, length, length);

    		
    		drawCircleFractal(x-length/2,y,drawer,level-1, length/2);    		
    		    		
    		//l1.draw(drawer);
    		
    		drawCircleFractal(x+length/2, y, drawer, level-1, length/2);    		
    		
    		
    		drawCircleFractal(x, y-length/2, drawer, level-1, length/2);    		

    		
    		drawCircleFractal(x, y+length/2, drawer, level-1, length/2);    		

    		
    	}
    	
    	
    }

}
