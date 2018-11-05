
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import processing.core.PApplet;

public class House{

	public int x, y;
	private PApplet drawer;
	
	public House (int locX, int locY, PApplet process) {
		x = locX;
		y = locY;
		drawer = process;
	}

	public void draw() {

		//roof and chimney
		drawer.fill(89, 6, 6); 

		drawer.rect(x + 50, y-200, 50, 100);
		drawer.triangle (x, y - 200, x - 150, y - 50, x + 150, y - 50);

		//body
		drawer.fill(134, 157, 239);  
		drawer.rect(x-150, y-50, 300, 200);

		//window
		drawer.fill(219, 220, 224);

		drawer.rect(x-100, y, 50, 50);
		drawer.rect(x+50, y, 50, 50);

		//door
		drawer.fill(89, 6, 6); 

		drawer.rect(x-25, y+50, 50, 100);
		

		/*
		//ground
		drawer.fill(6, 112, 22);
		drawer.rect(-10, drawer.height *7/10, drawer.width + 20, drawer.height);

		//roof and chimney
		drawer.fill(89, 6, 6); 

		drawer.rect(drawer.width*6/10, drawer.height*1/10, drawer.width*1/10, drawer.height*1/5);
		drawer.triangle (drawer.width*1/2, drawer.height*1/10, drawer.width*1/5, drawer.height*2/5, drawer.width*4/5, drawer.height*2/5);

		//body
		drawer.fill(134, 157, 239);  

		drawer.rect(drawer.width*1/5, drawer.height*2/5, drawer.width*3/5, drawer.height*2/5);

		//window
		drawer.fill(219, 220, 224);

		drawer.rect(drawer.width*3/10, drawer.height*1/2, drawer.width*1/10, drawer.height*1/10);
		drawer.rect(drawer.width*3/5, drawer.height*1/2, drawer.width*1/10, drawer.height*1/10);

		//door
		drawer.fill(89, 6, 6); 

		drawer.rect(drawer.width*9/20, drawer.height*6/10, drawer.width*1/10, drawer.height*1/5);

		 */


	}


}
