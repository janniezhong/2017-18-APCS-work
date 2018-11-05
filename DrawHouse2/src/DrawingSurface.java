import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

public class DrawingSurface extends PApplet {

	private House house;
	private Sun sun;
	private Person person;
	private int xSun;
	private int xPerson, yPerson, xHouse, yHouse;

	
	public DrawingSurface() {
		xSun = 0;
		xPerson = 85;
		yPerson = 255;
		xHouse = 250;
		yHouse = 250;
		
		house = new House(xHouse, yHouse, this);
		sun = new Sun();
		person = new Person(xPerson, yPerson);

	}
	
	public void draw() {
		
		
		background(2, 191, 216);
		
		//ground
		fill(6, 112, 22);
		rect(-10, 350, 520, 500);
		
		float xRatio = (float)width/(float)Main.startWidth;
		float yRatio = (float)height/(float)Main.startHeight;
		scale(xRatio, yRatio);
		
		sun.draw(this, xSun);
		house.draw();
		person.draw(this);
		
		xSun++;
		
		
		if (xSun == width + width*1/10) {
			xSun = 0 - width*1/10;
		}

	}
	

}
