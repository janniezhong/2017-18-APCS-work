package alonkatz.shapesDemo;

import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.math.MathContext;

import jannie.shapes.*;
import jannie.shapes.Rectangle;
import processing.core.PApplet;

//Take out the return type method

public class PhysicsShape {

	private Shape boundingShape;
	private Rectangle boundries;
	private double xVelocity;
	private double yVelocity;
	private double lastPartOfXVelocity;
	private double lastPartOfYVelocity;
	private double originalXV;
	private double originalYV;
	private double originalX;
	private double originalY;
	private boolean runVelocity;
	private double point1;
	private double point2;
	private double point3;
	private double point4;
	private double xMultiplier;
	private double yMultiplier;
	private boolean dragging;
	private boolean collide;
	private double xc;
	private double yc;
	private int counter1;
	private boolean willCollide;
	private double v2x;
	private double v2y;

	public PhysicsShape(Shape shape) {
		boundingShape = shape;

		boundries = new Rectangle(20, 20, 360, 260, 1, Color.BLACK, false, Color.black, true);
		xVelocity = 0;
		yVelocity = 0;
		originalXV = 0;
		originalYV = 0;
		originalX = boundingShape.getX();
		originalY = boundingShape.getY();
		counter1 = 0;
		xMultiplier = 0;
		yMultiplier = 0;
		// lastPartOfXVelocity = 0;
		// lastPartOfYVelocity = 0;
		point1 = boundingShape.getX();
		point2 = boundingShape.getY();
		point3 = boundingShape.getX();
		point4 = boundingShape.getY();
		dragging = false;
	}

	public void draw(PApplet drawer) {
		// drawer.pushMatrix();

		boundingShape.draw(drawer);
		if (collide) {

			drawer.fill(Color.red.getRGB());
			drawer.ellipse((float) xc, (float) yc, (float) 3, (float) 3);

		}

		boundries.draw(drawer);
		// drawer.popMatrix();
	}

	public Shape getBoundingShape() {
		return boundingShape;
	}

	public void setVelocity(double xv, double yv, PhysicsShape physicsShape) {
		// resets some of the values and sets some of the values

		v2x = 0;
		v2y = 0;
		xMultiplier = 0;
		yMultiplier = 0;
		runVelocity = false;
		lastPartOfXVelocity = 0;
		lastPartOfYVelocity = 0;

		counter = 0;
		counter1 = 0;

		xVelocity = xv;
		yVelocity = yv;

		if (xVelocity > 10) {
			xVelocity = 10;
		} else if (xVelocity < -10) {
			xVelocity = -10;
		}

		if (yVelocity > 10) {
			yVelocity = 10;
		} else if (yVelocity < -10) {
			yVelocity = -10;
		}

		originalXV = xVelocity;
		originalYV = yVelocity;

		// Checks if the shape is going to collide before it collides with the shape

		if (physicsShape != null) {
			getMultiplierValues();

			double x = boundingShape.getX();
			double y = boundingShape.getY();
			double angle;
			if (originalXV == 0) {
				angle = Math.PI / 2;

			} else {
				angle = Math.atan(Math.abs(originalYV / originalXV));
			}

			double yvv = 0;

			while (Math.abs(xVelocity) > 0 || Math.abs(yVelocity) > 0) {

				double x2 = x;
				double y2 = y;

				yvv = yVelocity;
				x += xVelocity;
				y += yVelocity;

				double distance = Math.sqrt(Math.abs((physicsShape.getX() - x) * (physicsShape.getX() - x)
						+ (physicsShape.getY() - y) * (physicsShape.getY() - y)));

				xVelocity -= .1 * Math.abs(Math.cos(angle)) * xMultiplier;
				yVelocity -= .1 * Math.abs(Math.sin(angle)) * yMultiplier;

				if (originalXV > 0) {
					if (xVelocity <= .001) {
						xVelocity = 0;
						yVelocity = 0;
					}
				} else if (originalXV < 0) {
					if (xVelocity >= -0.001) {
						xVelocity = 0;
						yVelocity = 0;
					}
				}

				if (originalYV > 0) {
					if (yVelocity <= .001) {
						xVelocity = 0;
						yVelocity = 0;
					}
				} else if (originalYV < 0) {
					if (yVelocity >= -0.001) {
						xVelocity = 0;
						yVelocity = 0;
					}
				}

				counter1++;
				if (distance <= this.getWidth() / 2 + physicsShape.getWidth() / 2) {
					willCollide = true;

					int e = 0;
					double xadd = 0;
					double yadd = 0;
					while (e == 0) {

						x2 += .001 * Math.cos(angle) * xMultiplier;
						y2 += .001 * Math.sin(angle) * yMultiplier;
						xadd += .001 * Math.cos(angle) * xMultiplier;
						yadd += .001 * Math.sin(angle) * yMultiplier;

						double distance2 = Math.sqrt(Math.abs((physicsShape.getX() - x2) * (physicsShape.getX() - x2)
								+ (physicsShape.getY() - y2) * (physicsShape.getY() - y2)));

						if (distance2 <= this.getWidth() / 2 + physicsShape.getWidth() / 2) {
							lastPartOfXVelocity = xadd;
							lastPartOfYVelocity = yadd;
							e = 1;

							System.out.println(" Hello " + x2);
							System.out.println(boundingShape.getX() + lastPartOfXVelocity);

							v2x = x2 - physicsShape.getX();
							v2y = y2 - physicsShape.getY();

							// set multiplier values for "this"
							if (v2x > 0) {
								physicsShape.xMultiplier = -1;
							} else if (v2x < 0) {
								physicsShape.xMultiplier = 1;
							} else {
								physicsShape.xMultiplier = 0;
							}

							if (v2y > 0) {
								physicsShape.yMultiplier = -1;
							} else if (v2y < 0) {
								physicsShape.yMultiplier = 1;
							} else {
								physicsShape.yMultiplier = 0;
							}

						}
					}

					xVelocity = 0;
					yVelocity = 0;

					counter1--;
				} else {
					willCollide = false;
				}

			}
			// Makes sure that xVelocity and yVelocity have initial values

			xVelocity = xv;
			yVelocity = yv;

			if (xVelocity > 10) {
				xVelocity = 10;
			} else if (xVelocity < -10) {
				xVelocity = -10;
			}

			if (yVelocity > 10) {
				yVelocity = 10;
			} else if (yVelocity < -10) {
				yVelocity = -10;
			}
		}
		runVelocity = true;

		if (xVelocity == 0 && yVelocity == 0) {
			// System.out.println("Cancel Velocity");
			runVelocity = false;
		}

	}

	// sets the dragging to true
	public void setDragging(boolean dragging) {
		this.dragging = dragging;
	}

	// drags the shapes around the screen
	public void drag(double mouseX, double mouseY, double width, double height) {
		// System.out.println("Running drag");
		double left = boundingShape.getX() - boundingShape.getWidth() / 2;
		double right = boundingShape.getX() + boundingShape.getWidth() / 2;
		double top = boundingShape.getY() - boundingShape.getWidth() / 2;
		double bottom = boundingShape.getY() + boundingShape.getWidth() / 2;
		Double center = boundingShape.getCenter();

		double boundriesTop = boundries.getY();
		double boundriesLeft = boundries.getX();
		double boundriesRight = boundries.getX() + boundries.getWidth();
		double boundriesBottom = boundries.getY() + boundries.getHeight();

		if (left <= boundriesLeft) {
			if (mouseX < boundriesLeft) {
				mouseX = left;
			}
		} else if (right >= boundriesRight) {
			if (mouseX > boundriesRight) {
				mouseX = boundriesRight;
			}
		}
		if (top <= boundriesTop) {
			if (mouseY < boundriesTop) {
				mouseY = boundriesTop;
			}
		} else if (bottom >= boundriesBottom) {
			if (mouseY > boundriesBottom)
				mouseY = boundriesBottom;
		}
		boundingShape.move(mouseX, mouseY);

	}

	// resets the value of velocity before dragging

	public void setupCalculateVelocity(double mouseX, double mouseY) {
		// System.out.println("Running Setup Calculations");
		point1 = mouseX;
		point2 = mouseY;

		point3 = mouseX;
		point4 = mouseY;
		xVelocity = 0;
		yVelocity = 0;
	}

	// calculates the velocity of the shape as it is being dragged

	public void calculateVelocity(double mouseX, double mouseY) {

		if (dragging) {

			point1 = point3;
			point2 = point4;
			point3 = mouseX;
			point4 = mouseY;

			xVelocity = (point3 - point1) / 2;
			yVelocity = (point4 - point2) / 2;

		}
	}

	public boolean checkIfPointIsInside(double mouseX, double mouseY) {
		return boundingShape.isPointInside(mouseX, mouseY);
	}

	public Shape getShape() {
		return boundingShape;
	}

	// Use For loop
	public boolean checkCollision(PhysicsShape physicsShape) {
		Shape shape = physicsShape.getShape();
		double width1 = this.boundingShape.getWidth();
		double width2 = shape.getWidth();
		double getX1 = this.boundingShape.getX();
		double getX2 = shape.getX();
		double getY1 = this.boundingShape.getY();
		double getY2 = shape.getY();
		
		

			double distance = Math.sqrt((getX1 - getX2) * (getX1 - getX2) + (getY1 - getY2) * (getY1 - getY2));

			if (distance <= width1 / 2 + width2 / 2) {
				collide = true;
				// Checks if one circle is above the other
				boolean slopeVertical;

				double yChange;
				double yCollisionPoint;

				double xChange;
				double xCollisionPoint;

				if (getX1 - getX2 == 0) {
					xChange = 0;
					if (getY1 > getY2) {
						yChange = -width1 / 2;
					} else {
						yChange = width1 / 2;
					}
				} else {
					double slope = (getY1 - getY2) / (getX1 - getX2);

					double angle;
					int multiplier;
					if (slope < 0) {
						slope *= -1;
						multiplier = -1;
					} else {
						multiplier = 1;
					}
					angle = Math.atan(slope);
					yChange = multiplier * Math.sin(angle) * width1 / 2;
					xChange = Math.cos(angle) * width1 / 2;
				}

				yCollisionPoint = getY1 + yChange;
				xCollisionPoint = getX1 + xChange;

				double distanceFromOtherShape = Math.sqrt((xCollisionPoint - getX2) * (xCollisionPoint - getX2)
						+ (yCollisionPoint - getY2) * (yCollisionPoint - getY2));

				xc = xCollisionPoint;
				yc = yCollisionPoint;

				if (!(distance > distanceFromOtherShape)) {

					xCollisionPoint = getX1 - xChange;
					yCollisionPoint = getY1 - yChange;
					xc = xCollisionPoint;
					yc = yCollisionPoint;
				}
				return true;
			} else {

			}

	//	}
		return false;
	}

	public double getXPointOfCollision1() {
		return xc;
	}

	


	private int counter = 0;

	
	

	public double getWidth() {
		return boundingShape.getWidth();
	}

	public double getX() {
		return boundingShape.getX();
	}

	public double getY() {
		return boundingShape.getY();
	}

	


	//gets the xVelocity of the shape
	public double getXVelocity() {
		return xVelocity;
	}

	//gets the yVelocity of the shape
	public double getYVelocity() {
		return yVelocity;
	}

	//moves the shape to (x,y)
	public void move(double x, double y) {
		boundingShape.move(x, y);
	}

	//Makes sure that the shape stays inside the rectangle
	public boolean keepShapeInBounds(double x, double y, boolean move, PhysicsShape physicsShape) {
		double left = x - boundingShape.getWidth() / 2;
		double right = x + boundingShape.getWidth() / 2;
		double top = y - boundingShape.getWidth() / 2;
		double bottom = y + boundingShape.getWidth() / 2;

		double boundriesLeft = boundries.getX();
		double boundriesTop = boundries.getY();
		double boundriesRight = boundries.getX() + boundries.getWidth();
		double boundriesBottom = boundries.getY() + boundries.getHeight();

		boolean result = true;

		if (left <= boundriesLeft) {
			
			result = false;
			if (move) {
				if (!runVelocity) {
					boundingShape.move(boundriesLeft + boundingShape.getWidth() / 2, boundingShape.getY());
				} else {
					boundingShape.move(boundriesLeft + boundingShape.getWidth() / 2 + 1, boundingShape.getY());
				}
				xMultiplier *= -1;

				setVelocity(-xVelocity, yVelocity, physicsShape);
				
			}

		}

		else if (right >= boundriesRight) {
			result = false;
			if (move) {
				if (!runVelocity) {
					boundingShape.move(boundriesRight - boundingShape.getWidth() / 2, boundingShape.getY());
				} else {
					boundingShape.move(boundriesRight - boundingShape.getWidth() / 2 - 1, boundingShape.getY());
				}
				xMultiplier *= -1;
				setVelocity(-xVelocity, yVelocity, physicsShape);
				
			}
		}

		if (top <= boundriesTop) {
			result = false;
			if (move) {
				if (!runVelocity) {
					boundingShape.move(boundingShape.getX(), boundriesTop + boundingShape.getWidth() / 2);
				} else {
					boundingShape.move(boundingShape.getX(), boundriesTop + boundingShape.getWidth() / 2 + 1);
				}
				yMultiplier *= -1;
				setVelocity(xVelocity, -yVelocity, physicsShape);
			
			}
		} else if (bottom >= boundriesBottom) {
			result = false;
			if (move) {
				if (!runVelocity) {
					boundingShape.move(boundingShape.getX(), boundriesBottom - boundingShape.getWidth() / 2);
				} else {
					boundingShape.move(boundingShape.getX(), boundriesBottom - boundingShape.getWidth() / 2 - 1);
				}

				yMultiplier *= -1;
				setVelocity(xVelocity, -yVelocity, physicsShape);
				
			}
		}

		return result;

	}

	
	//gets the direction of the x and y velocities
	public void getMultiplierValues() {
		if (originalXV > 0) {
			xMultiplier = 1;

		} else if (originalXV < 0) {
			xMultiplier = -1;

		}

		if (originalYV > 0) {
			yMultiplier = 1;

		} else if (originalYV < 0) {
			yMultiplier = -1;

		}
	}

	//returns the totalVelocity of a shape
	public double totalVelocity() {
		return Math.sqrt(xVelocity * xVelocity + yVelocity * yVelocity);
	}

	public void resetIfGetsStuck() {
		xVelocity = 0;
		yVelocity = 0;
		originalXV = 0;
		originalYV = 0;
		counter1 = 0;
		xMultiplier = 0;
		yMultiplier = 0;
		boundingShape.move(originalX, originalY);
		runVelocity = false;
	}
	
	//moves the shape based on velocity
	public void runningVelocity(PhysicsShape physicsShape) {
		if (runVelocity) {

			if (originalXV > 0) {
				xMultiplier = 1;
				if (xVelocity <= -.001) {
					runVelocity = false;
					xVelocity = 0;
					yVelocity = 0;
				}
			} else if (originalXV < 0) {
				xMultiplier = -1;
				if (xVelocity >= 0.001) {
					xVelocity = 0;
					yVelocity = 0;

					runVelocity = false;
				}
			}

			if (xVelocity == 0 && yVelocity == 0) {
				runVelocity = false;
			}

			if (originalYV > 0) {
				yMultiplier = 1;
				if (yVelocity <= -.001) {
					runVelocity = false;
					xVelocity = 0;
					yVelocity = 0;
				}
			} else if (originalYV < 0) {
				yMultiplier = -1;
				if (yVelocity >= 0.001) {
					runVelocity = false;
					xVelocity = 0;
					yVelocity = 0;
				}
			}
		}

		if (runVelocity) {

			if (counter <= counter1 && willCollide) {

				boundingShape.move(boundingShape.getX() + xVelocity, boundingShape.getY() + yVelocity);
				double value = Math.atan(Math.abs(originalYV / originalXV));
				xVelocity -= .1 * Math.abs(Math.cos(value)) * xMultiplier;
				yVelocity -= .1 * Math.abs(Math.sin(value)) * yMultiplier;

				counter++;

				if (counter > counter1) {
					willCollide = false;
					boundingShape.move(boundingShape.getX() + lastPartOfXVelocity,
							boundingShape.getY() + lastPartOfYVelocity);
					

					double anglePowerMultiplier1 = 0;
					double anglePowerMultiplier2 = 0;

					
					double angle;
					if (v2x != 0) {
						angle = Math.atan(Math.abs(v2y / v2x));
					} else {
						angle = Math.PI / 2;
					}

					if (v2y != 0) {
						angle = Math.atan(Math.abs(v2y / v2x));
					} else {
						angle = 0;
					}

					if (xVelocity != 0 && yVelocity == 0) {
						yMultiplier = -physicsShape.yMultiplier;

						anglePowerMultiplier1 = Math.abs(Math.cos(angle));
						anglePowerMultiplier2 = Math.abs(Math.sin(angle));
					} else if (yVelocity != 0 && xVelocity == 0) {

						xMultiplier = -physicsShape.xMultiplier;
						anglePowerMultiplier1 = Math.abs(Math.sin(angle));
						anglePowerMultiplier2 = Math.abs(Math.cos(angle));
					} else {
						double slope1 = v2y / v2x;
						double slope2 = yVelocity / xVelocity;
						angle = Math.atan(Math.abs((slope1 - slope2) / (1.0 + slope1 * slope2)));

						anglePowerMultiplier1 = Math.abs(Math.cos(angle));
						anglePowerMultiplier2 = Math.abs(Math.sin(angle));

						double v5x = physicsShape.xMultiplier * totalVelocity() * anglePowerMultiplier1
								* Math.cos(angle);
						double v5y = physicsShape.yMultiplier * totalVelocity() * anglePowerMultiplier1
								* Math.sin(angle);

						double v6x = xVelocity - v5x;
						if (v6x > 0) {
							xMultiplier = 1.0;
						} else if (v6x < 0) {
							xMultiplier = -1.0;
						} else {
							xMultiplier = 0;
						}

						double v6y = yVelocity - v5y;
						if (v6y > 0) {
							yMultiplier = 1.0;
						} else if (v6y < 0) {
							yMultiplier = -1.0;
						} else {
							yMultiplier = 0;
						}

						System.out.println(slope1);
						System.out.println(angle);

					}

					System.out.println("X Multiplier2: " + physicsShape.xMultiplier);
					System.out.println("Y Multiplier2: " + physicsShape.yMultiplier);

					double v3x = physicsShape.xMultiplier * totalVelocity() * anglePowerMultiplier1 * Math.cos(angle);
					double v3y = physicsShape.yMultiplier * totalVelocity() * anglePowerMultiplier1 * Math.sin(angle);
					physicsShape.setVelocity(v3x, v3y, null);
					double angle2 = Math.PI / 2 - angle;
					double v4x = this.xMultiplier * totalVelocity() * anglePowerMultiplier2 * Math.cos(angle2);
					double v4y = this.yMultiplier * totalVelocity() * anglePowerMultiplier2 * Math.sin(angle2);

					System.out.println("X Multiplier: " + xMultiplier);
					System.out.println("Y Multiplier: " + yMultiplier);

					setVelocity(v4x, v4y, null);
					physicsShape.xMultiplier = 0;
					physicsShape.yMultiplier = 0;

				}

			} else {
				boundingShape.move(boundingShape.getX() + xVelocity, boundingShape.getY() + yVelocity);
				if (!keepShapeInBounds(boundingShape.getX(), boundingShape.getY(), false, null)) {
					keepShapeInBounds(boundingShape.getX(), boundingShape.getY(), true, physicsShape);
				}

				double value = Math.atan(originalYV / originalXV);
				xVelocity -= .1 * Math.abs(Math.cos(value)) * xMultiplier;
				yVelocity -= .1 * Math.abs(Math.sin(value)) * yMultiplier;
			}

		}

	}

	public void setPower() {
	
	}

	public void act(double width, double height, PhysicsShape physicsShape) {

		// System.out.println(runVelocity);

		keepShapeInBounds(boundingShape.getX(), boundingShape.getY(), true, null);

		runningVelocity(physicsShape);

	}
}
