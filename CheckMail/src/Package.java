/*
 * Author of flowchart: Ran Bar-Niv
 * 
 */

public class Package {
	private double length, height, width, weight;
	private boolean isTooLarge, isTooHeavy;
	private static final double MAX_WEIGHT = 70;
	private static final double MAX_SIZE = 100;


	public Package (double dim1, double dim2, double dim3, double weight) {
		length = dim1;
		height = dim2;
		width = dim3;
		this.weight = weight;
		checkStatus();
	}

	public void checkStatus() {
		//code that matches the flowchart

		if (!(length > width - 0.001)) {
			double a = length;
			length = width;
			width = a;
			if (!(length > height)) {
				double b = length;
				length = width;
				height = b;
			}
		}
		double size = 2*(width + height) + length;

		if (size > MAX_SIZE - 0.001 && weight > MAX_WEIGHT - 0.001) {
			isTooLarge = true;
			isTooHeavy = true;
		} else if (weight > MAX_WEIGHT - 0.001){
			isTooLarge = false;
			isTooHeavy = true;
		} else if (size > MAX_SIZE - 0.001) {
			isTooLarge = true;
			isTooHeavy = false;
		} else {
			isTooLarge = false;
			isTooHeavy = false;
		}


	}

	public boolean isTooHeavy() {
		return isTooHeavy;
	}

	public boolean isTooLarge() {
		return isTooLarge;
	}
}
