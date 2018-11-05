import processing.core.PApplet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;

import javax.swing.*;
public class Person2{

	private int x, y;
	private Line lightsaber;
	private Line top, bottom;


	public Person2 () {
		x = 415;
		y = 255;

	}

	public void draw (PApplet drawer) {

		drawer.noFill();

		drawer.stroke(0);
		//head

		drawer.ellipse(x, y, 50, 50);

		//body

		drawer.line(x, y + 25, x, y + 100);

		//arms

		drawer.line(x, y + 50, x + 25, y + 75);
		drawer.line(x, y + 50, x - 25, y + 75);
		
		//legs

		drawer.line(x, y + 100, x - 25, y + 150);
		drawer.line(x, y + 100, x + 25, y + 150);

		//lightsaber
		
		drawer.stroke(255, 0, 255);
		lightsaber = new Line(x-25, y + 75, x - 45, y);
		
		lightsaber.draw(drawer);
		
		//hit detection
		top = new Line(x-30, y-25, x+30, y-25);
		bottom = new Line(x-30, y+150, x+30, y+150);
		
//		top.draw(drawer);
//		bottom.draw(drawer);


	}

	public void moveLeft () {

		if (x >= 0 && x <= 500) {
			x -= 5;
		} else if (x < 0){
			x = 0;
		} else if (x > 500) {
			x = 500;
		}
	}

	public void moveRight() {
		if (x >= 0 && x <= 500) {
			x += 5;
		} else if (x < 0){
			x = 0;
		} else if (x > 500) {
			x = 500;
		}
	}

	public void moveUp() {

		if (y >= 0 && y <= 500) {
			y -= 5;
			
		} else if (y < 0){
			y = 0;

		} else if (y > 500) {
			y = 500;

		}
	}
	public void moveDown() {
		if (y >= 0 && y <= 500) {
			y += 5;

		} else if (y < 0){
			y = 0;

		} else if (y > 500) {
			y = 500;

		}
	}
	
	public Line getLightsaber() {
		return lightsaber;
	}
//	public Line getBodyLineUp() {
//		return top;
//	}
//	
//	public Line getBodyLineBottom() {
//		return bottom;
//	}

}
