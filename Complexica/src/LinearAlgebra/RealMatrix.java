package LinearAlgebra;

import ComplexCalculator.CommonComplex;

public class RealMatrix {
	int size;
	double values[][];
	
	public RealMatrix(int size) {
		this.size = size;
		values = new double[size][size];
		
	}
	
	public void setValue(int x, int y, double value) {
		if (x>-1&&x<size&&y>-1&&y<size) {
			values[x][y] = value;
		}
	}
	
	public void setMatrix(RealMatrix m) {
		if (size==m.getSize()) {
			for (int x=0; x<size; x++) {
				for (int y=0; y<size; y++) {
					setValue(x,y,m.getValue(x, y));
				}
			}
		}
	}
	
	
	public int getSize() {
		return size;
	}
	
	public double getValue(int x, int y) {
		if (x>-1&&x<size&&y>-1&&y<size) {
			return values[x][y];
		} else {
			return 0.0;
		}
	}
	
	public ComplexMatrix toComplex() {
		ComplexMatrix cMatrix = new ComplexMatrix(size);
		for (int x=0; x<size; x++) {
			for (int y=0; y<size; y++) {
				cMatrix.setValue(x, y, CommonComplex.complexReal(values[x][y]));
			}
		}
		return cMatrix;
	}
	
	
	
}
