import processing.core.PApplet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;

import javax.swing.*;
public class Person{
	
	public static int x;
	public static int y;
	
	public Person (int locX, int locY) {
		x = locX;
		y = locY;
		
	}

	public void draw (PApplet drawer) {
		
		drawer.noFill();
		
		drawer.stroke(0);
		//head
		
		drawer.ellipse(x, y, 50, 50);
		
		//body
		
		drawer.line(x, y + 25, x, y+100);
		
		//arms
		
		drawer.line(x, y + 50, x + 25, y + 75);
		drawer.line(x, y + 50, x - 25, y + 75);
		
		//legs
		
		drawer.line(x, y + 100, x - 25, y + 150);
		drawer.line(x, y + 100, x + 25, y + 150);
		
	}

}
