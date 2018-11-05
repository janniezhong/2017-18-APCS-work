import java.util.Arrays;

public class Statistics {

	private int[] data;
	private int realLength;


	public Statistics(int maxLength) {
		// TODO Auto-generated constructor stub
		data = new int[maxLength];

	}

	public void readData (String filename) {
		ArrayReader r = new ArrayReader (filename);
		r.fillArray(data);
		realLength = r.fillArray(data);

	}

	public void printData() {
		for (int i = 0; i < realLength; i++) {
			System.out.println(data[i]);
		}
	}

	public double average() {
		long sum = 0;

		for (int e: data) {
			sum += e;
		}

		double average = sum/ (double)(realLength);

		return average;
	}

	public int[] mode() {
		
		Arrays.sort(data, 0, realLength);
		int count = 0;
		int maxCount = 0;
		int[] modes = new int[realLength];
		int modeNum = 0;
		
		for (int i = 0; i < realLength; i++) {
			
			if (data[i] == data[i+1]) {
				count++;
				
			} else {
				
				if (maxCount < count) {
					maxCount = count;
					modes = new int[realLength];
					modes[0] = data[i];
					modeNum = 1;
					
				
					
				} else if (maxCount == count) {
					modes[modeNum] = data[i];
					modeNum++;
					
				} else {
					
				}
				count = 1;
			}
		}
		
		return modes;
		
		
	
//		int[] modes = new int[realLength];
//		int modeNum = 0;
//		int maxCount = 0;
//		int maxValue = data[0];
//
//		for (int i = 0; i < realLength; i++) 
//		{
//			int count = 0;
//
//			for (int j = 0; j < realLength; j++) 
//			{
//				if (data[i] == data[j]) 
//				{
//					count++;
//				}
//			}
//
//			if (maxCount < count) 
//			{
//				maxCount = count;
//				maxValue = data[i];
//				modes = new int[data.length];
//				modes[0] = maxValue;
//				modeNum = 1;
//
//			} 
//			else if (maxCount == count) 
//			{
//				modes[modeNum] = data[i];
//
//				modeNum++;
//			}
//
//
//
//		}
//
//		int[] actMode = new int[modeNum];
//		for (int i = 0; i < actMode.length; i++) {
//			actMode[i] = data[i];
//		}
//		return actMode;
//

	}

	public double standardDeviation() {

		double avg = average();
		double stdDev = 0;

		for (int i = 0; i < realLength; i++) {
			stdDev += Math.pow((avg - data[i]), 2);
		}

		stdDev = stdDev/(realLength-1);
		stdDev = Math.sqrt(stdDev);

		return stdDev;
	}
	
	public int[] compact (int badNum) {
		
		for (int i = 0; i < realLength; i++) {
			if (data[i] == badNum) {
				for (int j = i; j < realLength-1; j++) {
					data[j]=data[j+1];
					data[realLength - 1] = badNum;
				}
				i--;
				//System.out.print(Arrays.toString(data));
				realLength--;
			} else {
				
			}
		}
		
		return data;
		
	}


}
