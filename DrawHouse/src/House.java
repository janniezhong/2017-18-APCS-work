import processing.core.PApplet;
import processing.event.MouseEvent;

public class House {

	private int x, y;
	private float houseSize;

	public House () {
		x = 250;
		y = 250;
		houseSize = 1;
	}

	public void setX (int xLocofHouse) {
		x = xLocofHouse;
	}
	public void setY (int yLocofHouse) {
		y = yLocofHouse;
	}

	public void draw(PApplet drawer) {

		//roof and chimney
		drawer.pushMatrix();

		drawer.translate(x, y);

		drawer.scale(houseSize, houseSize);

		drawer.translate(-250, -250);

		drawer.fill(89, 6, 6); 

		drawer.rect(300, 50, 50, 100);
		drawer.triangle (250, 50, 100, 200, 400, 200);

		//body
		drawer.fill(134, 157, 239);  
		drawer.rect(100, 200, 300, 200);

		//window
		drawer.fill(219, 220, 224);

		drawer.rect(150, 250, 50, 50);
		drawer.rect(300, 250, 50, 50);

		//door
	
		drawer.fill(89, 6, 6); 

		drawer.rect(225, 300, 50, 100);

		drawer.popMatrix();
	}

	public void changeLocation(int xLoc, int yLoc) {
		x = xLoc;
		y = yLoc;
	}

	public void increaseSize(double amt)
	{
		houseSize +=amt;
	}


}