import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
import processing.event.MouseEvent;

public class DrawingSurface extends PApplet {

	private House house;
	private Sun sun;
	private Person person;
	private int xSun;
	private Person2 person2;
	//private int xPerson, yPerson, xHouse, yHouse;
	private float xRatio,yRatio;



	public DrawingSurface() {

		xSun = 0;
		house = new House();
		sun = new Sun();
		person = new Person();
		person2 = new Person2();

	}

	public void setup (){

	}

	public void draw() {		

		background(2, 191, 216);

		xRatio = (float)width/(float)Main.startWidth;
		yRatio = (float)height/(float)Main.startHeight;

		scale(xRatio, yRatio);


		//ground
		noStroke();
		fill(6, 112, 22);
		rect(-10, 350, width*3, height);


		sun.draw(this, xSun);
		house.draw(this);
		person.draw(this);
		person2.draw(this);

		xSun++;

		if (xSun == width + width*1/10) {
			xSun = 0 - width*1/10;
		}

		if (keyPressed) {
			keyPressed();

		} else {
			keyReleased();
		}
		//scale(House.houseSize);
		if (mousePressed) {
			resetMatrix();
			mouseDragged();

		}

		//System.out.println(keyPressed);


		Line personLeft = person.getBodyLineLeft();
		Line personMid = person.getBodyLineMid();
		Line personRight = person.getBodyLineRight();

		Line lightsaber = person2.getLightsaber();

		if (personLeft.intersects(lightsaber) || personRight.intersects(lightsaber) || personMid.intersects(lightsaber)) {
			person.killPerson();
		} else {
			person.revivePerson();
			
		}

		//		Line person2Up = person2.getBodyLineUp();
		//		Line person2Bottom = person2.getBodyLineBottom();
		//
		//		if (personLeft.intersects(person2Up) | personLeft.intersects(person2Bottom) | personMid.intersects(person2Up) | personMid.intersects(person2Bottom) | personRight.intersects(person2Up)| personRight.intersects(person2Bottom)) {
		//			person.canPersonMove(false);
		//			person2.canPersonMove(false);
		//		} else {
		//			person.canPersonMove(true);
		//			person2.canPersonMove(true);
		//		}
	}



	public float getXR() {
		return xRatio;
	}
	public float getYR() {
		return yRatio;
	}
	public void mouseDragged() {

		int locX = mouseX;
		int locY = mouseY;

		house.changeLocation((int) (locX/getXR()), (int) (locY/getYR()));


	}


	public void keyPressed() {

		//SCALING THE HOUSE\\

		if (key == '1') {
			house.increaseSize(-0.01);
		} else if (key == '2') { 
			house.increaseSize(+0.01);

		} else {
			
		}
		//drawer.scale(houseSize);

		//MOVING THE PERSON\\

		//conditions

		if (keyCode == UP) {
			person.moveUp();
		} else if (keyCode == DOWN) {
			person.moveDown();

		} else if (keyCode == LEFT) {
			person.moveLeft();

		} else if (keyCode == RIGHT) {
			person.moveRight();

		} else if (key == 'w') {
			person2.moveUp();
		} else if (key == 's') {
			person2.moveDown();
		} else if (key == 'a') {
			person2.moveLeft();
		} else if (key == 'd') {
			person2.moveRight();
		}

		if (key == ' ') {
			person.moveArmUp();
		}

	}

	public void keyReleased () {
		if (key == ' ') {
			person.moveArmDown();
		}

	}
}