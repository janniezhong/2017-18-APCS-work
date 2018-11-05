import java.awt.Color;
import java.awt.geom.*; // for Point2D.Double
import java.awt.geom.Point2D.Double;
import java.util.ArrayList; // for ArrayList
//
//import jannie.shapes.Line;
//import jannie.shapes.Shape;
import processing.core.PApplet; // for Processing

/*
 * +: The choice methods are good
 * -: The isPointInside method breaks when you right click when running
 * delta: Try a lot more test cases to help facilitate the process in finding bugs in the isPointInside method. Also dont have empty else statements - bad style(eg line 40)
 */

public class IrregularPolygon extends Shape {
	private ArrayList<Point2D.Double> myPolygon;
	private ArrayList<Line> myLPolygon;
	private boolean canEdit;
	private int editCounter;

	// constructors
	public IrregularPolygon() {
		super(0, 0, 1, Color.BLACK, true, Color.RED, true);
		myPolygon = new ArrayList<Point2D.Double>();
		myLPolygon = new ArrayList<Line>();
		canEdit = true;
		editCounter = 0;
	}

	// public methods
	public void add(Point2D.Double aPoint) {
		if (canEdit) {
			myPolygon.add(aPoint);

			if (myPolygon.size() > 1) {
				myLPolygon.add(new Line(myPolygon.get(myPolygon.size() - 2).getX(),
						myPolygon.get(myPolygon.size() - 2).getY(), (int) myPolygon.get(myPolygon.size() - 1).getX(),
						(int) myPolygon.get(myPolygon.size() - 1).getY(), 1, Color.RED, false, Color.RED, true));
			}

		} else if (!canEdit && editCounter == 1) {
			 myLPolygon.add(new Line(myPolygon.get(myPolygon.size()-1).getX(),
			 myPolygon.get(myPolygon.size()-1).getY(), (int)myPolygon.get(0).getX(),
			 (int)myPolygon.get(0).getY(), 1, Color.RED, false, Color.RED, true));
		}
	}

	public void draw(PApplet marker) {
		super.draw(marker);

		//
		// for (int i = 0; i < myLPolygon.size(); i++) {
		// myLPolygon.get(i).draw(marker);
		// }
		//

		for (int i = 0; i < myPolygon.size() - 1; i++) {

			Line l = new Line(myPolygon.get(i).getX(), myPolygon.get(i).getY(), (int) (myPolygon.get(i + 1).getX()),
					(int) myPolygon.get(i + 1).getY(), 1, Color.RED, false, Color.RED, true);
			l.draw(marker);

			// marker.line((float)(myPolygon.get(i).getX()), (float)myPolygon.get(i).getY(),
			// (float)myPolygon.get(i+1).getX(), (float)myPolygon.get(i+1).getY());

			if (i == myPolygon.size() - 2 && !canEdit) {

				Line m = new Line((myPolygon.get(i + 1).getX()), (float) myPolygon.get(i + 1).getY(),
						(int) myPolygon.get(0).getX(), (int) myPolygon.get(0).getY(), 1, Color.RED, false, Color.RED,
						true);
				m.draw(marker);

				// marker.line((float)(myPolygon.get(i+1).getX()),
				// (float)myPolygon.get(i+1).getY(), (float)myPolygon.get(0).getX(),
				// (float)myPolygon.get(0).getY());
			}

		}

	}

	@Override
	public double getArea() {

		double total = 0;
		if (!canEdit) {

			total = 0;

			for (int i = 0; i < myPolygon.size() - 1; i++) {
				total = total + myPolygon.get(i).getX() * myPolygon.get(i + 1).getY()
						- myPolygon.get(i).getY() * myPolygon.get(i + 1).getX();
			}
			total = total + myPolygon.get(myPolygon.size() - 1).getX() * myPolygon.get(0).getY()
					- myPolygon.get(myPolygon.size() - 1).getY() * myPolygon.get(0).getX();

		}

		return Math.abs(total / 2.0);

	}

	@Override
	public double getPerimeter() {

		double total = 0;
		// for(int i = 0; i < myLPolygon.size()-1; i++) {
		// total = total + myLPolygon.get(i).getLength();
		// }

		for (int i = 0; i < myPolygon.size() - 1; i++) {
			double ls = Math.pow(myPolygon.get(i).getX() - myPolygon.get(i + 1).getX(), 2)
					+ Math.pow(myPolygon.get(i).getY() - myPolygon.get(i + 1).getY(), 2);
			total = total + Math.sqrt(ls);

		}

		if (!canEdit) {
			double ls = Math.pow(myPolygon.get(0).getX() - myPolygon.get(myPolygon.size() - 1).getX(), 2)
					+ Math.pow(myPolygon.get(0).getY() - myPolygon.get(myPolygon.size() - 1).getY(), 2);
			total = total + Math.sqrt(ls);
		}

		return total;
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Double getCenter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPointInside(double x, double y) {
		// TODO Auto-generated method stub
		Line testLine = new Line(x, y, -4, -5, 1, Color.GREEN, true, Color.GREEN, true);
		int intersectTimes = 0;

		for (int i = 0; i < myLPolygon.size(); i++) {
			if (myLPolygon.get(i).intersects(testLine)) {
				intersectTimes++;
			} else {

			}
		}

		if (intersectTimes % 2 == 0) {
			return false;
		} else {
			return true;
		}

	}

	public void stopEdit() {
		canEdit = false;
		editCounter++;
	}

	public void translate(int x, int y) {
		for (int i = 0; i < myPolygon.size(); i++) {
			double prevX = myPolygon.get(i).getX();
			double prevY = myPolygon.get(i).getY();

			myPolygon.get(i).setLocation(prevX + x, prevY + y);

		}

		for (int i = 0; i < myLPolygon.size(); i++) {
			myLPolygon.get(i).translate(x, y);
		}
	}

}
