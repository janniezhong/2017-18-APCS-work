package shape;
import java.awt.Color;

import processing.core.PApplet;


/**
  @(#)KochCurve.java


  @author Jannie Z
  @version 2/9/17
*/
public class BoxFractal {

	// TO DO
	private int level;
	private int length, x, y;
	

    public BoxFractal(int x, int y, int level, int length) {
    	this.level = level;
    	this.length = length*4;
    	this.x = x;
    	this.y = y;
    }
    
    public void draw(PApplet drawer) {
    	drawBoxFractal(x, y, drawer, level, length);
    }

    private void drawBoxFractal(int x, int y, PApplet drawer, int level, double length) {
    	
   // 	int adjustment = length/21;
    	// TO DO
    	if (level < 1) {
    		drawer.fill(0, 0, 0);
    		drawer.rect((float)x, (float)y, (float)length, (float)length);
    	} else {
    		drawer.fill(0, 0, 0);

    		drawBoxFractal(x+0,y+0,drawer,level-1, (length)/3);    //top left		
  
    		
    		drawBoxFractal(x+(int)(length*2.0/3),y+0,drawer,level-1, (length)/3);    		//top right
    		
    		
    		drawBoxFractal(x+(int)(length*1.0/3),y+(int)(length*1.0/3),drawer,level-1, (length)/3);    	//middle	

    		
    		
    		drawBoxFractal(x,y+(int)(length*2.0/3),drawer,level-1, (length)/3);    	//bottom left	

    		drawBoxFractal(x+(int)(length*2.0/3),y+(int)(length*2.0/3),drawer,level-1, (length)/3);   //bottom right 		

    		
    	}
    	
    	
    }

}
