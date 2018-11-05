package jannie.illone;
import java.awt.Color;

import jannie.shapes.*;
import processing.core.PApplet;

/**
 * 
 * @author jzhong672
 * @version 2
 *
 */

public class DrawingSurface extends PApplet {
	
	Circle c1;
	Rectangle r1;
	
	public DrawingSurface() {
		c1 = new Circle();
		r1 = new Rectangle();
	}
	
	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {

	}
	
	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() { 
		
		background(255);   // Clear the screen with a white background
		
		
		for (int i = 0; i <= 32; i++) {
			if (i%2 == 0) {
				
			} else {
				stroke(0, 0, 0);
				c1 = new Circle (300, 300, 275 - 7.25*i, 8, Color.BLACK, false, Color.BLACK, true);
				c1.draw(this);
			}
		}
		r1 = new Rectangle (80, 80, 450, 450, 8, Color.BLACK, false,  new Color(105, 109, 114), true);
		r1.draw(this);
		
		
		
	}
	
	
	
}










