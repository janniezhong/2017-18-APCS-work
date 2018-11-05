

import java.awt.Color;
import java.util.ArrayList;

import jannie.shapes.Line;
import jannie.shapes.Rectangle;
import processing.core.PApplet;

/**
 * 
 * @author jzhong672
 * @version 2
 *
 */

// new Color(24, 98, 219)
public class DrawingSurface extends PApplet {
	
	ArrayList<Line> lines;
	Rectangle r1, r2;
	
	public DrawingSurface() {
		r1 = new Rectangle(200, 200, 15, 200, 1, Color.BLUE, true, Color.BLUE, false);
		r2 = new Rectangle(400, 200, 15, 200, 1, Color.BLUE, true, Color.BLUE, false);
		
		lines = new ArrayList<>();
		
		
	}
	
	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {

		for (int i = 0; i <= 10; i++) {
			lines.add(new Line(-200, 300, 600, -50 + 65*(i), 5, new Color(105, 109, 120), true, new Color(105, 109, 114), true));
		}
		
	}
	
	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() { 
		
		background(255);   // Clear the screen with a white background
		
		
		for (int i = 0; i <= 10; i++) {
	
				lines.get(i).draw(this);
			}
		

		r1.draw(this);
		r2.draw(this);
		
		
		
	}
	
	
	
}










