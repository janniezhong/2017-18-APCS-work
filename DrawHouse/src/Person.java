import processing.core.PApplet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;

import javax.swing.*;
public class Person{

	private int x, y;
	private int armY;
	private Line arm;
	private Line bodyHitLine1, bodyHitLine2, bodyHitLine3;
	private Boolean isPersonDead;

	public Person () {
		x = 85;
		y = 255;
		isPersonDead = false;
		armY = y + 75;

	}

	public void draw (PApplet drawer) {

		drawer.noFill();

		drawer.stroke(0);
		//head

		drawer.ellipse(x, y, 50, 50);

		//body

		drawer.line(x, y + 25, x, y + 100);

		//arms

		drawer.line(x, y + 50, x + 25, armY);


		arm = new Line(x, y + 50, x - 25, y + 75);
		//drawer.line(x, y + 50, x - 25, y + 75);
		arm.draw(drawer);

		//legs

		drawer.line(x, y + 100, x - 25, y + 150);
		drawer.line(x, y + 100, x + 25, y + 150);

		//body hit line for lightsaber

		bodyHitLine1 = new Line (x - 25, y - 30, x - 25, y + 155);
		bodyHitLine2 = new Line (x, y - 30, x, y + 155);
		bodyHitLine3 = new Line (x + 25, y - 30, x + 25, y + 155);
		
//		bodyHitLine1.draw(drawer);
//		bodyHitLine2.draw(drawer);
//		bodyHitLine3.draw(drawer);

		if (isPersonDead) {
			drawer.stroke(255, 0, 0);
			drawer.line(x-15, y-10, x-5, y);
			drawer.line(x-5, y-10, x-15, y);

			drawer.line(x+15, y-10, x+5, y);
			drawer.line(x+5, y-10, x+15, y);

		}

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
			armY -=5;
		} else if (y < 0){
			y = 0;
			armY = y + 75;

		} else if (y > 500) {
			y = 500;
			armY = y + 75;

		}
	}
	public void moveDown() {
		if (y >= 0 && y <= 500) {
			y += 5;
			armY += 5;
		} else if (y < 0){
			y = 0;
			armY = y + 75;
		} else if (y > 500) {
			y = 500;
			armY = y + 75;
		}
	}
	public void moveArmUp() {
		armY = y + 25;

	}
	public void moveArmDown() {
		armY = y + 75;
	}

	public Line getArm() {
		return arm;
	}
	public Line getBodyLineLeft() {
		return bodyHitLine1;
	}
	public Line getBodyLineMid() {
		return bodyHitLine2;
	}
	public Line getBodyLineRight() {
		return bodyHitLine3;
	}
	public void killPerson() {
		isPersonDead = true;
	}
	public void revivePerson() {
		isPersonDead = false;
	}
//
//	public void canPersonMove(Boolean b) {
//		canPersonMove = b;
//	}
//
//	public Boolean getCanPersonMove() {
//		if (isPersonDead) {
//			return !isPersonDead;
//		} else {
//			return canPersonMove;
//		}
//	}



}
