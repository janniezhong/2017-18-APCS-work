import java.util.Arrays;

public class ResizableArray {

	private int[] data;
	private int size;
	private final int DEFAULT_LENGTH = (1000);

	public ResizableArray() {
		
		data = new int[DEFAULT_LENGTH];
		size = 0;


	}
	
	public ResizableArray(int[] values) {
		data = new int[DEFAULT_LENGTH];
		
		for (int i = 0; i < values.length; i++) {
			data[i] = values[i];
		}
		
		size = values.length;
	}
	
	public ResizableArray (int length) {
		data = new int[length];
		size = length;
	}
	
	public ResizableArray (ResizableArray other) {
		data = new int[other.size()];
		for (int i = 0; i < other.size(); i++) {
			data[i] = other.get(i);
		}
		size = other.size();
	}

	public void add (int value) {

		if (size == data.length) {
			resize(10);
		}
		data[size] = value;
		size++;

	}
	
	public void add(ResizableArray values) {

		if (size == data.length) {
			resize(values.size());
		}
		
		for (int i = size; i < size + values.size(); i++) {
			data[i] = values.get(i-size);
		}
		
		
		size =+ values.size();
		
	}
	
	public void add(int[] values) {
	
		if (size == data.length) {
			resize(values.length);
		}
		
		for (int i = size; i < size + values.length; i++) {
			data[i] = values[i-size];
		}
		
		
		size =+ values.length;
		
	}
	
	public void removeAll (int value) {

		int newSize = size;

		for (int i = 0; i < newSize; i++) {
			if (data[i] == value) {
				for (int  j = i; j < newSize-1; j++) {
					data[j]=data[j+1];
					data[newSize - 1] = value;
				}
				i--;
				newSize--;
			} else {
			}

		}

		//void clearAll, String toString, int size(), boolean contains, void insert, void swap, void crop, int get(int index), void set(int index)
		//int indexOf(int value), void sort, int remove (int index), void replaceAll (int oldValue, int newValue), 

	}
	
	public void replaceAll(int oldVal, int newVal) {
		
		for (int i = 0; i < size; i++) {
			if (data[i] == oldVal) {
				data[i] = newVal;
			} else {
			}

		}
	}

	public String toString() {
		String partialArray = "";
		for (int i = 0; i < size - 1; i++) {
			partialArray += data[i] + ", ";
		}

		partialArray += data[size-1];

		return partialArray;
	}

	public int remove (int index) {
		
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException (index + " is not a valid index; must be between 0 and " + size);
		}
		
		int removed = data[index];
		for (int i = index; i < size - 1; i++) {

			data[i]=data[i+1];

		}
		size--;

		return removed;

	}
	
	public int size() {
		return size;
	}
	
	public void insert(int index, int value) {
		
		if (index < 0 || index > size) {
			throw new IllegalArgumentException (index + " is not a valid index; must be between 0 and " + size);
		}
		
		if (size == data.length) {
			resize(100);
		}
		
		for (int i = size - 1; i >= index; i--) {

			data[i+1]=data[i];

		}
		
		data[index] = value;
		size++;

	}
	
	public int get (int index) {
		

		if (index < 0 || index >= size) {
			throw new IllegalArgumentException (index + " is not a valid index; must be between 0 and " + size);
		}
		
		int x = data[index];
		return x;
	}
	 public void set (int index, int value) {

			if (index < 0 || index >= size) {
				throw new IllegalArgumentException (index + " is not a valid index; must be between 0 and " + size);
			}
		 
		 data[index] = value;
		 
	 }
	 
	 public void sort() {
		 Arrays.sort(data, 0, size);
	 }
	 
	 public int indexOf(int value) {
		 int x = -1;
		 for (int i = 0; i < size; i++) {
			 if (data[i] == value) {
				 x = i;
				 break;
			 } else {
			 }
		 }
		 return x;
	 }
	 
	 public boolean equals (Object other) 
	 {
		 boolean result = false;
		 if (other instanceof ResizableArray) 
		 {
			 ResizableArray other2 = (ResizableArray)other;
			 if (this.size() == other2.size()) 
			 {
				result = true;
				 for (int i = 0; i < size; i++) 
				 {
					 if (data[i] != other2.get(i)) 
					 {
						 result = false;
						 break;
					 }
				 }
			 } 
		 }
		 return result;
	 }
	 
	 public int[] toArray() {
		 int[] realArray = new int[size];
		 for (int i = 0; i < size ; i++) {
			 realArray[i] = data[i];
		 }
		 
		 return realArray;
	 }
	 private void resize(int resize) {
		 int[] temp = new int[size+resize];
		 for (int i = 0; i < size; i++) {
			 temp[i] = data[i];
		 }
		 data = temp;
	 }
	 
	 public int[] reverse() {
		 for (int i = 0; i < size/2; i++) {
			 int temp = data[i];
			 data[i] = data[size - 1 - i];
			 data[size-1-i] = temp;
		 }
		 return data;
		 
	 }

	

}