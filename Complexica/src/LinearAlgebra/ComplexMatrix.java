package LinearAlgebra;

import ComplexCalculator.ComplexNumber;

public class ComplexMatrix {

	int size;
	ComplexNumber values[][];
	
	public ComplexMatrix(int size) {
		this.size = size;
		values = new ComplexNumber[size][size];
		for (int x=0; x<size; x++) {
			for (int y=0; y<size; y++) {
				values[x][y] = new ComplexNumber();
			}
		}
		
	}
	
	public void setValue(int x, int y, ComplexNumber value) {
		if (x>-1&&x<size&&y>-1&&y<size) {
			values[x][y] = value;
		}
	}
	
	public int getSize() {
		return size;
	}
	
	public ComplexNumber getValue(int x, int y) {
		if (x>-1&&x<size&&y>-1&&y<size) {
			return values[x][y];
		} else {
			return null;
		}
	}
	
	
	
}
