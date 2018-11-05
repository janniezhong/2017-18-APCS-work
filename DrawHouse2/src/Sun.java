import processing.core.PApplet;

public class Sun {

	public void draw (PApplet drawer, int location) {
		drawer.noStroke();
		drawer.fill(244, 231, 134);
		
		//drawer.ellipse(location, drawer.height * 1/5, drawer.width * 1/10, drawer.width * 1/10);
		
		drawer.ellipse(location, 100, 50, 50);
		
	}
	
}
