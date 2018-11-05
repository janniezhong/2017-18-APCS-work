import processing.core.PApplet;

public class Line {

	private float x1, y1, x2, y2;
	private float slope;

	public Line (double x1, double y1, double x2, double y2) {
		this.x1 = (float) x1;
		this.y1 = (float) y1;
		this.x2 = (float) x2;
		this.y2 = (float) y2;

	}

	public void draw(PApplet drawer) {
		drawer.line(x1, y1, x2, y2);
		slope = ((this.y2-this.y1)/(this.x2-this.x1));
	}

	public boolean intersects (Line l2) {

		float crossX = getCrossLocX(l2);
		float crossY = getCrossLocY(l2);
		//System.out.println (x1 + " " + y1 + " " + x2 + " " + y2);
		//System.out.println("(" + crossX + ", " + crossY + ")");

		float leftX = Math.min(x1, x2);
		float rightX = Math.max(x1, x2);
		float upY = Math.min(y1, y2);
		float downY = Math.max(y1, y2);

		float leftX2 = Math.min(l2.x1, l2.x2);
		float rightX2 = Math.max(l2.x1, l2.x2);
		float upY2 = Math.min(l2.y1, l2.y2);
		float downY2 = Math.max(l2.y1, l2.y2);


		//equation of the line would be y = slope(x-x1) + y1
		if (Math.abs(Math.abs(this.slope) - Math.abs(l2.slope)) < 0.001) { /////////////////////////// Fix this later, math with doubles
			return false;
		} else { // assume x1 < x2, y1 < y2
			if (crossX >= leftX && crossX <= rightX && crossY >= upY && crossY <= downY) {
				if (crossX >= leftX2 && crossX <= rightX2 && crossY >= upY2 && crossY <= downY2) {
					return true;
				} else {
					return false;
				}

			}

			return false;
		}

	}
	
	public float getCrossLocX(Line l2) {
		float crossX = ((x1*y2-y1*x2)*(l2.x1-l2.x2) - (x1-x2)*(l2.x1*l2.y2 - l2.y1*l2.x2))
				/((x1-x2)*(l2.y1-l2.y2) - (y1-y2)*(l2.x1-l2.x2));
		return crossX;
	}
	
	public float getCrossLocY(Line l2) {
		float crossY = ((x1*y2-y1*x2)*(l2.y1-l2.y2) - (y1-y2)*(l2.x1*l2.y2 - l2.y1*l2.x2))
				/((x1-x2)*(l2.y1-l2.y2) - (y1-y2)*(l2.x1-l2.x2));
		return crossY;
	}

	public void setPoint2 (double x2, double y2) {
		this.x2 = (float) x2;
		this.y2 = (float) y2;
	}

	public float getX1() {
		return x1;
	}
	public float getX2() {
		return x2;
	}
	public float getY1() {
		return y1;
	}
	public float getY2() {
		return y2;
	}
}
